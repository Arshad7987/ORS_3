package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelHibImpl;
import in.co.sunrays.proj3.model.CourseModelInt;

public class CourseModelHibTest {

	public static CourseModelInt courseModel = new CourseModelHibImpl();
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByName();
		//testFindByPK();
		//testList();
		testSearch();
	}
	
	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		CourseDTO dto = new CourseDTO();
		dto.setName("asd");
		dto.setDescription("asdf");
		dto.setDuration("2 years");
		dto.setCreatedBy("pratiksolanki64@gmail.com");
		dto.setModifiedBy("pratiksolanki64@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = courseModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Error");
		}
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		CourseDTO dto = courseModel.findByPK(3);
		dto.setDuration("3 years");
		courseModel.update(dto);
		System.out.println("Updated");
	}
	
	public static void testDelete() throws ApplicationException {
		CourseDTO dto = courseModel.findByPK(5);
		courseModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testFindByName() throws ApplicationException {
		CourseDTO dto = courseModel.findByName("BE");
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		System.out.println(dto.getDuration());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testFindByPK() throws ApplicationException {
		CourseDTO dto = courseModel.findByPK(2);
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		System.out.println(dto.getDuration());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testList() throws ApplicationException {
		List<CourseDTO> list = courseModel.list();
		CourseDTO dto = new CourseDTO();
		Iterator<CourseDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getDuration());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
	
	public static void testSearch() throws ApplicationException {
		CourseDTO dto = new CourseDTO();
		dto.setName("B");
		List<CourseDTO> list = courseModel.search(dto);
		Iterator<CourseDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getDuration());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
}
