
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.UserModelHibImpl;
import in.co.sunrays.proj3.model.UserModelJDBCImpl;

public class UserModelTest {
	public static boolean jdbcTest(UserDTO bean) throws DatabaseException, ApplicationException, DuplicateRecordException{
		boolean pass=false;
		UserModelJDBCImpl model=new UserModelJDBCImpl();
		bean.setId(2L);
		//System.out.println("pk="+model.add(bean));
//	System.out.println("pk="+model.registerUser(bean));
		//model.update(bean);
	
	//System.out.println("Auth name="+model.authenticate(bean.getLogin(), bean.getPassword()).getFirstName());
		
		pass=true;
		
	return pass;	
	}
	public static boolean hibernateTest(UserDTO bean) throws DuplicateRecordException, ApplicationException{
		boolean pass=false;
	
		UserModelHibImpl model=new UserModelHibImpl();
		//System.out.println("pk="+model.add(bean));
		System.out.println(model.registerUser(bean));
		
		pass=true;
	return pass;
	}
	
	
	public static void main(String[] args) throws ParseException, DatabaseException {
		UserDTO dto=new UserDTO();
		dto.setId(1L);
		dto.setFirstName("Naqeeb");
		dto.setLastName("khan");
		dto.setGender("M");
		dto.setMobileNo("8435725286");
		dto.setLogin("mdnkhan0786@gmail.com");
		dto.setPassword("cs12345");
		SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
		Date d=date.parse("05/05/1995");
				dto.setDob(d);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));
		dto.setRoleId(2);
		dto.setRoleName("STUDENT");
		try {
			System.out.println(jdbcTest(dto));
			
			
			//System.out.println(hibernateTest(dto));;
		} catch (DuplicateRecordException | ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
