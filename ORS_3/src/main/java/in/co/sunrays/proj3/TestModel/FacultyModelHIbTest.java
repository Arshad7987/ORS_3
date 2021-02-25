package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.FacultyModelHibImpl;
import in.co.sunrays.proj3.model.FacultyModelInt;

public class FacultyModelHIbTest {

	public static FacultyModelInt facultyModel = new FacultyModelHibImpl();
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByEmail();
		//testFindByPK();
		//testList();
		testSearch();
	}
	
	public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		FacultyDTO dto = new FacultyDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setFirstName("yuvraj");
		dto.setLastName("singh");
		dto.setCollegeId(4);
		dto.setCourseId(1);
		dto.setSubjectId(3);
		dto.setQualification("ME");
		dto.setEmailId("yuvrajsingh@singh.com");
		dto.setDob(sdf.parse("15-07-1994"));
		dto.setMobNo("8889597302");
		dto.setGender("Male");
		dto.setCreatedBy("pratiksolanki64@gmail.com");
		dto.setModifiedBy("pratiksolanki64@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = facultyModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Error");
		}
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		FacultyDTO dto = facultyModel.findByPK(2);
		dto.setCourseId(1);
		dto.setCollegeId(4);
		dto.setFirstName("virat ji");
		facultyModel.update(dto);
		System.out.println("Updated");
	}
	
	public static void testDelete() throws ApplicationException {
		FacultyDTO dto = facultyModel.findByEmailId("virat64@kohli.com");
		facultyModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testFindByEmail() throws ApplicationException {
		FacultyDTO dto = facultyModel.findByEmailId("pratiksolanki64@gmail.com");
		System.out.println(dto.getId());
		System.out.println(dto.getFirstName());
		System.out.println(dto.getLastName());
		System.out.println(dto.getCollegeId());
		System.out.println(dto.getCollegeName());
		System.out.println(dto.getCourseId());
		System.out.println(dto.getCourseName());
		System.out.println(dto.getSubjectId());
		System.out.println(dto.getSubjectName());
		System.out.println(dto.getQualification());
		System.out.println(dto.getEmailId());
		System.out.println(dto.getMobNo());
		System.out.println(dto.getGender());
		System.out.println(dto.getDob());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
		
	}
	
	public static void testFindByPK() throws ApplicationException {
		FacultyDTO dto = facultyModel.findByPK(5);
		System.out.println(dto.getId());
		System.out.println(dto.getFirstName());
		System.out.println(dto.getLastName());
		System.out.println(dto.getCollegeId());
		System.out.println(dto.getCollegeName());
		System.out.println(dto.getCourseId());
		System.out.println(dto.getCourseName());
		System.out.println(dto.getSubjectId());
		System.out.println(dto.getSubjectName());
		System.out.println(dto.getQualification());
		System.out.println(dto.getEmailId());
		System.out.println(dto.getMobNo());
		System.out.println(dto.getGender());
		System.out.println(dto.getDob());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testList() throws ApplicationException {
		List<FacultyDTO> list = facultyModel.list();
		FacultyDTO dto = new FacultyDTO();
		Iterator<FacultyDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getCollegeId());
			System.out.println(dto.getCollegeName());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectId());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getQualification());
			System.out.println(dto.getEmailId());
			System.out.println(dto.getMobNo());
			System.out.println(dto.getGender());
			System.out.println(dto.getDob());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
	
	public static void testSearch() throws ApplicationException {
		FacultyDTO dto = new FacultyDTO();
		dto.setCollegeId(2);
		List<FacultyDTO> list = facultyModel.search(dto);
		Iterator<FacultyDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println("here : "+dto.getCollegeId());
			System.out.println(dto.getCollegeName());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectId());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getQualification());
			System.out.println(dto.getEmailId());
			System.out.println(dto.getMobNo());
			System.out.println(dto.getGender());
			System.out.println(dto.getDob());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
}
