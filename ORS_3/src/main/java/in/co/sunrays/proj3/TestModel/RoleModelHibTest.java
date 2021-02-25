package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.RoleDTO;
import  in.co.sunrays.proj3.exception.ApplicationException;
import  in.co.sunrays.proj3.exception.DuplicateRecordException;
import  in.co.sunrays.proj3.model.RoleModelHibImpl;
import  in.co.sunrays.proj3.model.RoleModelInt;

public class RoleModelHibTest {
	public static RoleModelInt roleModel = new RoleModelHibImpl();
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByName();
		//testFindByPK();
		//testSearch();
		//testList();
	}
	
	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		RoleDTO dto = new RoleDTO();
		dto.setName("Admin");
		dto.setDescription("Admin Role");
		dto.setCreatedBy("sahilkhan868.sk@gmail.com");
		dto.setModifiedBy("sahilkhan868.sk@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i = roleModel.add(dto);
		if(i>0) {
			System.out.println("Inserted");
		}else {
			System.out.println("Exception");
		}
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		RoleDTO dto = roleModel.findByName("student");
		dto.setDescription("Admin Role");
		roleModel.update(dto);
		System.out.println("Updated");
	}
	
	public static void testDelete() throws ApplicationException {
		RoleDTO dto = roleModel.findByPK(3);
		roleModel.delete(dto);
		System.out.println("Deleted");
	}
	
	public static void testFindByName() throws ApplicationException {
		RoleDTO dto = roleModel.findByName("Admin");
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testFindByPK() throws ApplicationException {
		RoleDTO dto = roleModel.findByPK(1);
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		System.out.println(dto.getCreatedBy());
		System.out.println(dto.getModifiedBy());
		System.out.println(dto.getCreatedDateTime());
		System.out.println(dto.getModifiedDateTime());
	}
	
	public static void testSearch() throws ApplicationException {
		RoleDTO dto = new RoleDTO();
		dto.setName("Ad");
		dto.setDescription("Adm");
		List<RoleDTO> list = roleModel.search(dto);
		Iterator<RoleDTO> it = list.iterator();
		RoleDTO dtoo = new RoleDTO();
		while(it.hasNext()) {
			dtoo = it.next();
			System.out.println(dtoo.getId());
			System.out.println(dtoo.getName());
			System.out.println(dtoo.getDescription());
			System.out.println(dtoo.getCreatedBy());
			System.out.println(dtoo.getModifiedBy());
			System.out.println(dtoo.getCreatedDateTime());
			System.out.println(dtoo.getModifiedDateTime());
		}
	}
	
	public static void testList() throws ApplicationException {
		List<RoleDTO> list = roleModel.list();
		Iterator<RoleDTO> it = list.iterator();
		RoleDTO dtoo = new RoleDTO();
		while(it.hasNext()) {
			dtoo = it.next();
			System.out.println(dtoo.getId());
			System.out.println(dtoo.getName());
			System.out.println(dtoo.getDescription());
			System.out.println(dtoo.getCreatedBy());
			System.out.println(dtoo.getModifiedBy());
			System.out.println(dtoo.getCreatedDateTime());
			System.out.println(dtoo.getModifiedDateTime());
		}
	}
}
