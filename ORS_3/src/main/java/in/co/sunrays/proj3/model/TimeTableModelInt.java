package in.co.sunrays.proj3.model;

import java.util.Date;
import java.util.List;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;

/**
 * Data Access Object of Time Table
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */


public interface TimeTableModelInt {
	/**
     * Add a Role
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when Role already exists
	 * @throws RecordNotFoundException 
     */
    public long add(TimeTableDTO dto) throws ApplicationException,
            DuplicateRecordException, RecordNotFoundException;

    /**
     * Update a Role
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     * @throws RecordNotFoundException 
     */
    public void update(TimeTableDTO dto) throws ApplicationException,
            DuplicateRecordException, RecordNotFoundException;

    /**
     * Delete a Role
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(TimeTableDTO dto) throws ApplicationException;

    
    /**
     * Find  Duplicate TimeTable
     * 
     * @param name
     *            : get parameter course_id Semester Exam Date
     * @return dto
     * @throws ApplicationException
     */
    public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Date examdate)
			throws ApplicationException;
    /**
     * Find  Duplicate TimeTable
     * 
     * @param name
     *            : get parameter course_id subject_id Exam Date
     * @return dto
     * @throws ApplicationException
     */
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, Long subjectId, Date examDate)
			throws ApplicationException;
    
	/**
     * Find  Duplicate TimeTable
     * 
     * @param name
     *            : get parameter course_id Semester Subject_id
     * @return dto
     * @throws ApplicationException
     */
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Long subjectId)
			throws ApplicationException;
	/**
     * Check semester
     * 
     * @param name
     *            : get parameter
     * @return dto
     * @throws RecordNotFoundException
     */
    public void semChecker(CourseDTO cb, TimeTableDTO tb) throws RecordNotFoundException;
    /**
     * Find Role by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public TimeTableDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Role with pagination
     * 
     * @return list : List of Role
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(TimeTableDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search TimeTable
     * 
     * @return list : List of Role
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(TimeTableDTO dto) throws ApplicationException;

    /**
     * Gets List of Role
     * 
     * @return list : List of Role
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of Role with pagination
     * 
     * @return list : List of Role
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}
