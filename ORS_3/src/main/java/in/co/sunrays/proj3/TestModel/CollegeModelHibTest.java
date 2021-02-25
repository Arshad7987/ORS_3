package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelHibImpl;
import in.co.sunrays.proj3.model.CollegeModelInt;

public class CollegeModelHibTest {

	public static CollegeModelInt collegeModel = new CollegeModelHibImpl();
	
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
		CollegeDTO dto = new CollegeDTO();
		dto.setName("hijk");
		dto.setAddress("lmno");
		dto.setState("MP");
		dto.setCity("Indore");
		dto.setPhoneNo("7878787878");
		dto.setCreatedBy("pratiksolanki64@gmail.com");
		dto.setModifiedBy("pratiksolanki64@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = collegeModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Error");
		}
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		CollegeDTO dto = collegeModel.findByName("VITS");
		dto.setAddress("Tejaji nagar");
		dto.setCity("indore");
		dto.setState("Madhya Pradesh");
		collegeModel.update(dto);
		System.out.println("Updated");
	}
	
	public static void testDelete() throws ApplicationException {
		CollegeDTO dto = collegeModel.findByPK(6);
		collegeModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testFindByName() throws ApplicationException {
		CollegeDTO dto = collegeModel.findByName("SVCE");
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getAddress());
		System.out.println(dto.getCity());
		System.out.println(dto.getState());
		System.out.println(dto.getPhoneNo());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testFindByPK() throws ApplicationException {
		CollegeDTO dto = collegeModel.findByPK(3);
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getAddress());
		System.out.println(dto.getCity());
		System.out.println(dto.getState());
		System.out.println(dto.getPhoneNo());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testList() throws ApplicationException {
		List<CollegeDTO> list = collegeModel.list();
		CollegeDTO dto = new CollegeDTO();
		Iterator<CollegeDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getAddress());
			System.out.println(dto.getCity());
			System.out.println(dto.getState());
			System.out.println(dto.getPhoneNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
	
	public static void testSearch() throws ApplicationException {
		CollegeDTO dto = new CollegeDTO();
		dto.setName("s");
		List<CollegeDTO> list = collegeModel.search(dto);
		Iterator<CollegeDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getAddress());
			System.out.println(dto.getCity());
			System.out.println(dto.getState());
			System.out.println(dto.getPhoneNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
}
