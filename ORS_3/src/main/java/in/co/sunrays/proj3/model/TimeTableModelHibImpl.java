package in.co.sunrays.proj3.model;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.RoleDTO;
import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.util.HibDataSource;

/**
 * Hibernate Implementation of Time Table Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class TimeTableModelHibImpl implements TimeTableModelInt {
	private static Logger log = Logger.getLogger(TimeTableModelHibImpl.class);

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#add(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		// TODO Auto-generated method stub
		log.debug("Model add Started");
        long pk = 0;
		CourseModelInt cModel = ModelFactory.getInstance().getCourseModel();
		CourseDTO coursedto = cModel.findByPK(dto.getCourseId());
		dto.setCourseName(coursedto.getName());

		SubjectModelInt sModel = ModelFactory.getInstance().getSubjectModel();
		SubjectDTO subjectdto = sModel.findByPK(dto.getSubjectId());
		if (subjectdto != null) {
			if (subjectdto.getCourseId() != dto.getCourseId()) {

				throw new RecordNotFoundException("This Subject is not Available For This Course");
			}
		}dto.setSubjectName(subjectdto.getName());

		semChecker(coursedto, dto);

        TimeTableDTO duplicate1 = findTimeTableDuplicacy(dto.getCourseId(), dto.getSubjectId(), dto.getExamDate());
        TimeTableDTO duplicate2 = findTimeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getExamDate());
        TimeTableDTO duplicate3 =findTimeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getSubjectId());
        if (duplicate1 != null||duplicate2!=null||duplicate3!=null) {
            throw new DuplicateRecordException("Time table already exists");
        }
        
        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in time table Add "
                    + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        log.debug("Model add End");
        return dto.getId();

	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#update(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		 log.debug("Model update Started");
	        
			CourseModelInt cModel = ModelFactory.getInstance().getCourseModel();
			CourseDTO coursedto = cModel.findByPK(dto.getCourseId());
			dto.setCourseName(coursedto.getName());

			SubjectModelInt sModel = ModelFactory.getInstance().getSubjectModel();
			SubjectDTO subjectdto = sModel.findByPK(dto.getSubjectId());
			if (subjectdto != null) {
				if (subjectdto.getCourseId() != dto.getCourseId()) {

					throw new RecordNotFoundException("This Subject is not Available For This Course");
				}
			}dto.setSubjectName(subjectdto.getName());

			semChecker(coursedto, dto);

		 
		 Session session = null;
	        Transaction transaction = null;
	        
	        TimeTableDTO duplicate1 = findTimeTableDuplicacy(dto.getCourseId(), dto.getSubjectId(), dto.getExamDate());
	        TimeTableDTO duplicate2 = findTimeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getExamDate());
	        TimeTableDTO duplicate3 =findTimeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getSubjectId());
	        if (duplicate1 != null&&duplicate1.getId()!=dto.getId()||duplicate2!=null&&duplicate2.getId()!=dto.getId()||duplicate3!=null&&duplicate3.getId()!=dto.getId()) {
	            throw new DuplicateRecordException("Time Table is already exists");
	        }   
	        
	        try {
	            session = HibDataSource.getSession();
	            transaction = session.beginTransaction();
	            session.update(dto);
	            transaction.commit();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            if (transaction != null) {
	                transaction.rollback();
	                throw new ApplicationException("Exception in Time table Update"
	                        + e.getMessage());
	            }
	        } finally {
	            session.close();
	        }
	        log.debug("Model update End");
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#delete(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public void delete(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in time table Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
	}

	
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Date examdate)
			throws ApplicationException {
		log.debug("Model find time table duplicacy Started");
		TimeTableDTO dto = null;
        Session session = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimeTableDTO.class);
            criteria.add(Restrictions.eq("courseId",courseId)).add(Restrictions.eq("semester", Semester)).add(Restrictions.eq("examDate", examdate));
            
            List list = criteria.list();
            if (list.size() == 1) {
                dto = (TimeTableDTO) list.get(0);
            } else {
                dto = null;
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in getting timetable by findtimetableduplicacy "
                    + e.getMessage());

        } finally {
            session.close();
        }
        log.debug("Model findtimetableduplicacy End");
        return dto;


	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#findTimeTableDuplicacy(java.lang.Long, java.lang.Long, java.util.Date)
	 */
	
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, Long subjectId, Date examDate)
			throws ApplicationException {
		log.debug("Model find time table duplicacy Started");
		TimeTableDTO dto = null;
        Session session = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimeTableDTO.class);
            criteria.add(Restrictions.eq("courseId",courseId)).add(Restrictions.eq("examDate", examDate)).add(Restrictions.eq("subjectId", subjectId));
            
            List list = criteria.list();
            if (list.size() == 1) {
                dto = (TimeTableDTO) list.get(0);
            } else {
                dto = null;
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in getting timetable by findtimetableduplicacy "
                    + e.getMessage());

        } finally {
            session.close();
        }
        log.debug("Model findtimetableduplicacy End");
        return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#findTimeTableDuplicacy(java.lang.Long, java.lang.String, java.lang.Long)
	 */
	
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Long subjectId)
			throws ApplicationException {
		log.debug("Model find time table duplicacy Started");
		TimeTableDTO dto = null;
        Session session = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimeTableDTO.class);
            criteria.add(Restrictions.eq("courseId",courseId)).add(Restrictions.eq("semester", Semester)).add(Restrictions.eq("subjectId", subjectId));
            
            List list = criteria.list();
            if (list.size() == 1) {
                dto = (TimeTableDTO) list.get(0);
            } else {
                dto = null;
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in getting timetable by findtimetableduplicacy "
                    + e.getMessage());

        } finally {
            session.close();
        }
        log.debug("Model findtimetableduplicacy End");
        return dto;
	}

	
	public void semChecker(CourseDTO cdto, TimeTableDTO tdto) throws RecordNotFoundException {
		// TODO Auto-generated method stub
String duration = cdto.getDuration();

		
		if (duration.equalsIgnoreCase("3 years") && (tdto.getSemester().equalsIgnoreCase("VII")
				|| tdto.getSemester().equalsIgnoreCase("VIII"))) {
		
			throw new RecordNotFoundException("This Semester is not available for this course");

		}
		else if (duration.equalsIgnoreCase("2 years") && (tdto.getSemester().equalsIgnoreCase("V")
				|| tdto.getSemester().equalsIgnoreCase("VI") || tdto.getSemester().equalsIgnoreCase("VII")
				|| tdto.getSemester().equalsIgnoreCase("VIII"))) {
			throw new RecordNotFoundException("This Semester is not available for this course");

		}else{}

	}
	
	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#findByPK(long)
	 */
	
	public TimeTableDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model findBypk Started");
		TimeTableDTO dto = null;
        Session session = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimeTableDTO.class);
            criteria.add(Restrictions.eq("id", pk));
            List list = criteria.list();
            if (list.size() == 1) {
                dto = (TimeTableDTO) list.get(0);
            } else {
                dto = null;
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in getting time table by pk "
                    + e.getMessage());

        } finally {
            session.close();
        }
        log.debug("Model findBypk End");
        return dto;

	}

	
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimeTableDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }if (dto.getCourseId() > 0) {
                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
                
            }if (dto.getSubjectId() > 0) {
                criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
            }
            if (dto.getSemester() != null && dto.getSemester().length() > 0) {
                criteria.add(Restrictions.like("semester", dto.getSemester()
                        + "%"));
            }
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in time table search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;

	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#search(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public List search(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#list()
	 */
	
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#list(int, int)
	 */
	
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimeTableDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  Time table list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;

	}

}
