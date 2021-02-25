package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelHibImpl;
import in.co.sunrays.proj3.model.StudentModelHibImpl;
import in.co.sunrays.proj3.model.StudentModelInt;

public class StudentModelHibTest {
	
	public static StudentModelInt studentModel = new StudentModelHibImpl();
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByEmailId();
		//testFindByPK();
		//testList();
		//testSearch();
	}
	
	public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		StudentDTO dto = new StudentDTO();
		CollegeModelHibImpl collegeModel = new CollegeModelHibImpl();
		CollegeDTO dto1 = collegeModel.findByPK(4);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setFname("virat");
		dto.setLname("kohli");
		dto.setDob(sdf.parse("20-05-1990"));
		dto.setMobileNo("8880000000");
		dto.setEmail("virat@kohli.com");
		dto.setCollegeID(4);
		dto.setCollegeName(dto1.getName());
		dto.setCreatedBy("pratiksolanki64@gmail.com");
		dto.setModifiedBy("pratiksolanki64@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = studentModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Error");
		}
	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		StudentDTO dto = studentModel.findByPK(3);
		dto.setFname("aeiou");
		studentModel.update(dto);
		System.out.println("Updated");
	}
	
	public static void testDelete() throws ApplicationException {
		StudentDTO dto = studentModel.findByEmailId("abc@tendulkar.com");
		studentModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testFindByEmailId() throws ApplicationException {
		StudentDTO dto = studentModel.findByEmailId("pratiksolanki64@gmail.com");
		System.out.println(dto.getId());
		System.out.println(dto.getFname());
		System.out.println(dto.getLname());
		System.out.println(dto.getDob());
		System.out.println(dto.getMobileNo());
		System.out.println(dto.getEmail());
		System.out.println(dto.getCollegeID());
		System.out.println(dto.getCollegeName());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testFindByPK() throws ApplicationException {
		StudentDTO dto = studentModel.findByPK(2);
		System.out.println(dto.getId());
		System.out.println(dto.getFname());
		System.out.println(dto.getLname());
		System.out.println(dto.getDob());
		System.out.println(dto.getMobileNo());
		System.out.println(dto.getEmail());
		System.out.println(dto.getCollegeID());
		System.out.println(dto.getCollegeName());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testList() throws ApplicationException {
		List<StudentDTO> list = studentModel.list();
		StudentDTO dto = new StudentDTO();
		Iterator<StudentDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFname());
			System.out.println(dto.getLname());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getEmail());
			System.out.println(dto.getCollegeID());
			System.out.println(dto.getCollegeName());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
	
	public static void testSearch() throws ApplicationException {
		StudentDTO dto = new StudentDTO();
		dto.setFname("pra");
		List<StudentDTO> list = studentModel.search(dto);
		Iterator<StudentDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFname());
			System.out.println(dto.getLname());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getEmail());
			System.out.println(dto.getCollegeID());
			System.out.println(dto.getCollegeName());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
}
