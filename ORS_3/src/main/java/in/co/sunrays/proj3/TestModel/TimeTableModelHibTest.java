package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.TimeTableModelHibImpl;
import in.co.sunrays.proj3.model.TimeTableModelInt;

public class TimeTableModelHibTest {

	public static TimeTableModelInt ttModel = new TimeTableModelHibImpl();
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		//testAdd();
		//testFindByPk();
		//testUpdate();
		//testDelete();
		//testList();
		testSearch();
	}
	
	public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		TimeTableDTO dto = new TimeTableDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setCourseId(2);
		dto.setSubjectId(3);
		dto.setSemester("VI");
		dto.setExamDate(sdf.parse("12-12-2019"));
		dto.setTime("10 to 1");
		dto.setCreatedBy("pratiksolanki64@gmail.com");
		dto.setModifiedBy("pratiksolanki64@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = ttModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Error");
		}
	}
	
	public static void testFindByPk() throws ApplicationException {
		TimeTableDTO dto = ttModel.findByPK(1);
		System.out.println(dto.getId());
		System.out.println(dto.getCourseId());
		System.out.println(dto.getCourseName());
		System.out.println(dto.getSubjectId());
		System.out.println(dto.getSubjectName());
		System.out.println(dto.getSemester());
		System.out.println(dto.getExamDate());
		System.out.println(dto.getTime());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		TimeTableDTO dto = ttModel.findByPK(1);
		dto.setCourseId(2);
		ttModel.update(dto);
		System.out.println("UPdated");
	}
	
	public static void testDelete() throws ApplicationException {
		TimeTableDTO dto = ttModel.findByPK(4);
		ttModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testList() throws ApplicationException {
		List<TimeTableDTO> list = ttModel.list();
		TimeTableDTO dto = new TimeTableDTO();
		Iterator<TimeTableDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectId());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getSemester());
			System.out.println(dto.getExamDate());
			System.out.println(dto.getTime());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
	
	public static void testSearch() throws ApplicationException {
		TimeTableDTO dto = new TimeTableDTO();
		dto.setCourseId(3);
		dto.setSubjectId(6);
		List<TimeTableDTO> list = ttModel.search(dto);
		Iterator<TimeTableDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectId());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getSemester());
			System.out.println(dto.getExamDate());
			System.out.println(dto.getTime());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
	}
	}
}
