package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.MarksheetDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.MarksheetModelHibImpl;
import in.co.sunrays.proj3.model.MarksheetModelInt;

public class MarksheetModelHibTest {

	public static MarksheetModelInt marksheetModel = new MarksheetModelHibImpl();
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPK();
		//testFindByRollNo();
		//testList();
		//testSearch();
	}
	
	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		MarksheetDTO dto = new MarksheetDTO();
		dto.setStudentId(4);
		dto.setRollNo("1013");
		dto.setPhysics(40);
		dto.setChemistry(33);
		dto.setMaths(50);
		dto.setCreatedBy("pratiksolanki64@gmail.com");
		dto.setModifiedBy("pratiksolanki64@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = marksheetModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Error");
		}
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		MarksheetDTO dto = marksheetModel.findByPK(4);
		dto.setChemistry(50);
		marksheetModel.update(dto);
		System.out.println("Updated");
	}
	
	public static void testDelete() throws ApplicationException {
		MarksheetDTO dto = marksheetModel.findByRollNo("1012");
		marksheetModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testFindByPK() throws ApplicationException {
		MarksheetDTO dto = marksheetModel.findByPK(4);
		System.out.println(dto.getId());
		System.out.println(dto.getRollNo());
		System.out.println(dto.getStudentId());
		System.out.println(dto.getName());
		System.out.println(dto.getPhysics());
		System.out.println(dto.getChemistry());
		System.out.println(dto.getMaths());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	
	public static void testFindByRollNo() throws ApplicationException {
		MarksheetDTO dto = marksheetModel.findByRollNo("1011");
		System.out.println(dto.getId());
		System.out.println(dto.getRollNo());
		System.out.println(dto.getStudentId());
		System.out.println(dto.getName());
		System.out.println(dto.getPhysics());
		System.out.println(dto.getChemistry());
		System.out.println(dto.getMaths());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testList() throws ApplicationException {
		List<MarksheetDTO> list = marksheetModel.list();
		MarksheetDTO dto = new MarksheetDTO();
		Iterator<MarksheetDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getRollNo());
			System.out.println(dto.getStudentId());
			System.out.println(dto.getName());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getChemistry());
			System.out.println(dto.getMaths());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
	
	public static void testSearch() throws ApplicationException {
		MarksheetDTO dto = new MarksheetDTO();
		dto.setPhysics(50);
		List<MarksheetDTO> list = marksheetModel.search(dto);
		Iterator<MarksheetDTO> it = list.iterator();
		while(it.hasNext()) {
			dto=it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getRollNo());
			System.out.println(dto.getStudentId());
			System.out.println(dto.getName());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getChemistry());
			System.out.println(dto.getMaths());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDateTime());
			System.out.println(dto.getModifiedDateTime());
		}
	}
}
