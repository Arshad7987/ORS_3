package in.co.sunrays.proj3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.HibDataSource;
/**
 * Hibernate Implementation of UserModel
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class CourseModelHibImpl implements CourseModelInt {
	private static Logger log = Logger.getLogger(CourseModelHibImpl.class);
	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#add(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");

        CourseDTO dtoExist = findByName(dto.getName());

        if (dtoExist != null) {
            throw new DuplicateRecordException("Course is already exist");
        }

        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
             dto.getId();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Course Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");
        return dto.getId();
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#update(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

        CourseDTO dtoExist = findByName(dto.getName());
       

        // Check if updated LoginId already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("Course is already exist");
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
                throw new ApplicationException("Exception in Course Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");

	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#delete(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public void delete(CourseDTO dto) throws ApplicationException {
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
            throw new ApplicationException("Exception in Course Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#findByName(java.lang.String)
	 */
	public CourseDTO findByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model find By name Started");
        Session session = null;
        CourseDTO dto = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CourseDTO.class);
            criteria.add(Restrictions.eq("name", name));
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (CourseDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting Course by name " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model find By name End");
        return dto;

	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#findByPK(long)
	 */
	public CourseDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model findByPK Started");
        Session session = null;
        CourseDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (CourseDTO) session.get(CourseDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Course by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;

	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#search(in.co.sunrays.proj3.dto.CourseDTO, int, int)
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
    	log.debug("Model search Started");

        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CourseDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                criteria.add(Restrictions.like("name", dto.getName()
                        + "%"));
            }
            if (dto.getDuration() != null && dto.getDuration().length() > 0) {
                criteria.add(Restrictions.like("duration", dto.getDuration()
                        + "%"));
            }
            
         // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            
            throw new ApplicationException("Exception in Course search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;

	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#search(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public List search(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#list()
	 */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#list(int, int)
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CourseDTO.class);

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
                    "Exception : Exception in  Course list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;

	}

}
