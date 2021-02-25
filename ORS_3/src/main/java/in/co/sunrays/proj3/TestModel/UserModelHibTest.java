package in.co.sunrays.proj3.TestModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import java.util.Date;

import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.UserModelHibImpl;
import in.co.sunrays.proj3.model.UserModelInt;

public class UserModelHibTest {

	public static UserModelInt userModel = new UserModelHibImpl();
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException, RecordNotFoundException {
	//	testAdd();
		//testUpdate();
		//testDelete();
		//testFindByLogin();
		testFindByPK();
		//testSearch();
		//testList();
		//testChangePassword();
		//testAuthenticate();
		//testRegisterUser();
	}
	
	public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		UserDTO dto = new UserDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		dto.setFname("Ashu");
		dto.setLname("Pathan");
		dto.setLogin("ashu48@gmail.com");
		dto.setPassword("ashu4888");
		dto.setDob(sdf.parse("25-08-1994"));
		dto.setRoleId(1);
		dto.setUnSuccessfulLogin(1);
		dto.setGender("male");
		dto.setLastLogin(new Timestamp(new Date().getTime()));
		dto.setMobileNo("7400550734");
		dto.setCreatedBy("slkcsalkcasd@gmail.com");
		dto.setModifiedBy("kfkmdsohfs@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		long i=userModel.add(dto);
		if(i>0) {
			System.out.println("Record Added Successfullly");
		}else {
			System.out.println("Exceeption");
		}
	}
	
	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		UserDTO dto = userModel.findByPK(2);
		dto.setPassword("123456");
		userModel.update(dto);
		System.out.println("Updated Successfully");
	}
	
	public static void testDelete() throws ApplicationException {
		UserDTO dto = new UserDTO();
		dto.setId(3);
		userModel.delete(dto);
		System.out.println("Delete Successful");
	}
	
	public static void testFindByLogin() throws ApplicationException {
		UserDTO dto = userModel .findByLogin("ki64@gmail.com");
		System.out.println(dto.getFname());
		System.out.println(dto.getLname());
		System.out.println(dto.getLogin());
		System.out.println(dto.getPassword());
		System.out.println(dto.getDob());
		System.out.println(dto.getGender());
	}
	
	public static void testFindByPK() throws ApplicationException {
		UserDTO dto = userModel.findByPK(1);
		System.out.println(dto.getFname());
		System.out.println(dto.getLname());
		System.out.println(dto.getLogin());
		System.out.println(dto.getPassword());
		System.out.println(dto.getDob());
		System.out.println(dto.getGender());
	}
	
	public static void testSearch() throws ApplicationException {
		UserDTO dto = new UserDTO();
		dto.setFname("pr");
		List<UserDTO> list = userModel.search(dto);
		System.out.println(list.size());
		Iterator<UserDTO> it = list.iterator();
		UserDTO dto1=null;
		while(it.hasNext()) {
			dto1=it.next();
			System.out.println(dto1.getFname());
			System.out.println(dto1.getLname());
			System.out.println(dto1.getLogin());
			System.out.println(dto1.getPassword());
			System.out.println(dto1.getDob());
			System.out.println(dto1.getGender());
		}
	}
	
	public static void testList() throws ApplicationException {
		List<UserDTO> list = userModel.list(0, 2);
		System.out.println(list.size());
		Iterator<UserDTO> it = list.iterator();
		UserDTO dto1=null;
		while(it.hasNext()) {
			dto1=it.next();
			System.out.println(dto1.getFname());
			System.out.println(dto1.getLname());
			System.out.println(dto1.getLogin());
			System.out.println(dto1.getPassword());
			System.out.println(dto1.getDob());
			System.out.println(dto1.getGender());
		}
	}
	
	public static void testChangePassword() throws ApplicationException, RecordNotFoundException {
		UserDTO dto =userModel.findByPK(1);
		boolean flag = userModel.changePassword(dto.getId(), "PASS000", "pratiks");
	}
	
	public static void testAuthenticate() throws ApplicationException {
		UserDTO dto = userModel.authenticate("pr64@gmail.com", "p");
		System.out.println(dto.getFname());
		System.out.println(dto.getLname());
		System.out.println(dto.getLogin());
		System.out.println(dto.getPassword());
	}
	
	public static void testRegisterUser() throws ParseException, ApplicationException, DuplicateRecordException {
		UserDTO dto = new UserDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setFname("mahendra singh");
		dto.setLname("dhoni");
		dto.setLogin("mahendra@gmail.com");
		dto.setPassword("mahendra");
		dto.setGender("male");
		dto.setMobileNo("8989898989");
		dto.setDob(sdf.parse("12-12-1999"));
		long i = userModel.registerUser(dto);
		if(i>0) {
			System.out.println("Registered successfully");
		}else {
			System.out.println("Exception");
		}
	}
}
