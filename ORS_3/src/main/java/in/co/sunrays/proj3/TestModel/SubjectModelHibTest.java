package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.SubjectModelHibImpl;
import in.co.sunrays.proj3.model.SubjectModelInt;

public class SubjectModelHibTest {

	public static SubjectModelInt subjectModel = new SubjectModelHibImpl();
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByName();
		//testFindByPK();
		//testList();
		//testSearch();
	}
	
	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		SubjectDTO dto = new SubjectDTO();
		dto.setName("def");
		dto.setDescription("Data Structure");
		dto.setCourseId(2);
		dto.setCreatedBy("pratiksolanki64@gmail.com");
		dto.setModifiedBy("pratiksolanki64@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = subjectModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Error");
		}
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		SubjectDTO dto = subjectModel.findByPK(2);
		dto.setDescription("Wireless Network");
		subjectModel.update(dto);
		System.out.println("Updated");
	}
	
	public static void testDelete() throws ApplicationException {
		SubjectDTO dto = subjectModel.findByPK(4);
		subjectModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testFindByName() throws ApplicationException {
		SubjectDTO dto = subjectModel.findByName("CD");
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		System.out.println(dto.getCourseId());
		System.out.println(dto.getCourseName());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testFindByPK() throws ApplicationException {
		SubjectDTO dto = subjectModel.findByPK(3);
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		System.out.println(dto.getCourseId());
		System.out.println(dto.getCourseName());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testList() throws ApplicationException {
		List<SubjectDTO> list = subjectModel.list();
		SubjectDTO dto = new SubjectDTO();
		Iterator<SubjectDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
	
	public static void testSearch() throws ApplicationException {
		SubjectDTO dto = new SubjectDTO();
		dto.setName("DB");
		dto.setCourseName("B");
		dto.setCourseId(1);
		List<SubjectDTO> list = subjectModel.search(dto);
		Iterator<SubjectDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
}
