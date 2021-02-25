package in.co.sunrays.proj3.model;

import java.util.Date;
import java.util.List;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
/**
 * JDBC Implementation of Time Table Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class TimeTableModelJDBCImpl implements TimeTableModelInt {

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#add(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#update(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#delete(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public void delete(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#findTimeTableDuplicacy(java.lang.Long, java.lang.String, java.util.Date)
	 */
	
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Date examdate)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#findTimeTableDuplicacy(java.lang.Long, java.lang.Long, java.util.Date)
	 */
	
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, Long subjectId, Date examDate)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#findTimeTableDuplicacy(java.lang.Long, java.lang.String, java.lang.Long)
	 */
	
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Long subjectId)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#semChecker(in.co.sunrays.proj3.dto.CourseDTO, in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public void semChecker(CourseDTO cb, TimeTableDTO tb) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#findByPK(long)
	 */
	
	public TimeTableDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#search(in.co.sunrays.proj3.dto.TimeTableDTO, int, int)
	 */
	
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#search(in.co.sunrays.proj3.dto.TimeTableDTO)
	 */
	
	public List search(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#list()
	 */
	
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.TimeTableModelInt#list(int, int)
	 */
	
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
