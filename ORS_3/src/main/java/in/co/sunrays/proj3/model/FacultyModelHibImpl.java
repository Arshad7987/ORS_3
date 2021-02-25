package in.co.sunrays.proj3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.HibDataSource;
/**
 * Hibernate Implementation of FacultyModel
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class FacultyModelHibImpl implements FacultyModelInt{
	 private static Logger log = Logger.getLogger(FacultyModelHibImpl.class);
	 /**
	     * Add a Faculty
	     * 
	     * @param dto
	     * @throws DatabaseException
	     * 
	     */
	 public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {

        log.debug("Model add Started");
        long pk = 0;

        FacultyDTO dtoExist = findByLogin(dto.getEmailId());

        if (dtoExist != null) {
            throw new DuplicateRecordException("Email Id is already exist");
        }
        
       CollegeDTO cdto=ModelFactory.getInstance().getCollegeModel().findByPK(dto.getCollegeId());
       
       SubjectDTO sdto=ModelFactory.getInstance().getSubjectModel().findByPK(dto.getSubjectId());
//CourseDTO crdto=ModelFactory.getInstance().getCourseModel().findByPK(sdto.getCourseId());
dto.setCollegeName(cdto.getName());

dto.setSubjectName(sdto.getName());
dto.setCourseId(sdto.getCourseId());
dto.setCourseName(sdto.getCourseName());

        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
            transaction.commit();
        } catch (HibernateException e) {
        	System.out.println("Error="+e);
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in  Add Faculty "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");
        return dto.getId();

	}
	/**
     * Update a Faculty
     * 
     * @param dto
     * @throws DatabaseException
     */
	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

        FacultyDTO dtoExist = findByLogin(dto.getEmailId());
        //dto.setRoleName(ModelFactory.getInstance().getRoleModel().findByPK(dto.getRoleId()).getName());;

        // Check if updated LoginId already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("LoginId is already exist");
        }
        ModelFactory mfac=ModelFactory.getInstance();
        CollegeDTO cdto=mfac.getCollegeModel().findByPK(dto.getCollegeId());
        SubjectDTO sdto=mfac.getSubjectModel().findByPK(dto.getSubjectId());
        dto.setCollegeName(cdto.getName());
        dto.setSubjectName(sdto.getName());
        dto.setCourseId(sdto.getCourseId());
        dto.setCourseName(sdto.getCourseName());
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in User Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");

	}

	/**
     * Delete a Faculty
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void delete(FacultyDTO dto) throws ApplicationException {
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
            throw new ApplicationException("Exception in Faculty Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }
    /**
     * Find Faculty by Login
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
	public FacultyDTO findByLogin(String login) throws ApplicationException {
		// TODO Auto-generated method stub
        log.debug("Model findByLoginId Started");
        Session session = null;
        FacultyDTO dto = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(FacultyDTO.class);
            criteria.add(Restrictions.eq("emailId", login));
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (FacultyDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting Faculty by Login " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByLoginId End");
        return dto;
	}

    /**
     * Find Faculty by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public FacultyDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        FacultyDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (FacultyDTO) session.get(FacultyDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Faculty by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

    /**
     * Searches Users with pagination
     * 
     * @return list : List of Users
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * 
     * @throws DatabaseException
     */
    public List search(FacultyDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
    	log.debug("Model search Started");
        System.out.println("in method search 1-->" + dto.getFirstName());


        
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(FacultyDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
                criteria.add(Restrictions.like("firstName", dto.getFirstName()+"%"));
            }if (dto.getLastName() != null && dto.getLastName().length() > 0) {
                criteria.add(Restrictions.like("lastName", dto.getLastName()+"%"));
            }if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
                criteria.add(Restrictions.like("emailId", dto.getEmailId() + "%"));
            }if (dto.getGender() != null && dto.getGender().length() > 0) {
                criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
            }if (dto.getDob() != null && dto.getDob().getDate() > 0) {
                criteria.add(Restrictions.eq("dob", dto.getDob()));
            }if (dto.getSubjectId() > 0) {
                criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
            }if (dto.getCourseId() > 0) {
                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
            }
            

            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
        	System.out.println("Error="+e);
            log.error("Database Exception..", e);
            
            throw new ApplicationException("Exception in Faculty search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Gets List of Faculty
     * 
     * @return list : List of Faculty
     * @throws DatabaseException
     */
	public List search(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}
	/**
     * Gets List of Faculty
     * 
     * @return list : List of Faculty
     * @throws DatabaseException
     */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

    /**
     * get List of Faculty with pagination
     * 
     * @return list : List of Faculty
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(FacultyDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            System.out.println("ok2"+e);
            throw new ApplicationException(
                    "Exception : Exception in  Faculty list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }


}
