

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.StudentModelInt;

public class StudentModelTest {
public static void main(String[] args) throws ParseException {
	StudentModelInt model=ModelFactory.getInstance().getStudentModel();
	String dob="05/05/1995";
	SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
	Date date=format.parse(dob);
	StudentDTO dto=new StudentDTO();
	dto.setFirstName("Naqeeb");
	dto.setLastName("Khan");
	dto.setMobileNo("8956895689");
	dto.setGender("M");
	dto.setCollegeId(1l);
	dto.setCollegeName("PCST");
	dto.setDob(date);
	dto.setEmail("nk@gmail.com");
	dto.setCreatedBy("root");
	dto.setModifiedBy("root");
	dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
	dto.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));
	
	try {
		model.add(dto);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DuplicateRecordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
