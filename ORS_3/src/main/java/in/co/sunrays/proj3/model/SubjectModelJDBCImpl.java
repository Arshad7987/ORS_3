package in.co.sunrays.proj3.model;

import java.util.List;

import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;

/**
 * JDBC Implementation of Subject Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class SubjectModelJDBCImpl implements SubjectModelInt{

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#add(in.co.sunrays.proj3.dto.SubjectDTO)
	 */
	
	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#update(in.co.sunrays.proj3.dto.SubjectDTO)
	 */
	
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#delete(in.co.sunrays.proj3.dto.SubjectDTO)
	 */
	
	public void delete(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#findByName(java.lang.String)
	 */
	
	public SubjectDTO findByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#findByPK(long)
	 */
	
	public SubjectDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#search(in.co.sunrays.proj3.dto.SubjectDTO, int, int)
	 */
	
	public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#search(in.co.sunrays.proj3.dto.SubjectDTO)
	 */
	
	public List search(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#list()
	 */
	
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.model.SubjectModelInt#list(int, int)
	 */
	
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
