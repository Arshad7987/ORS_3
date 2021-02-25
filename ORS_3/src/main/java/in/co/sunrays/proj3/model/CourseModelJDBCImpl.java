package in.co.sunrays.proj3.model;

import java.util.List;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
/**
 * JDBC Implementation of Course Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class CourseModelJDBCImpl implements CourseModelInt{

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#add(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#update(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#delete(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public void delete(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#findByName(java.lang.String)
	 */
	public CourseDTO findByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#findByPK(long)
	 */
	public CourseDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#search(in.co.sunrays.proj3.dto.CourseDTO, int, int)
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#search(in.co.sunrays.proj3.dto.CourseDTO)
	 */
	public List search(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#list()
	 */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.CourseModelInt#list(int, int)
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
