import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.FacultyModelInt;
import in.co.sunrays.proj3.model.ModelFactory;

public class FacultyModelTest {
public static void main(String[] args) throws ParseException {
FacultyModelInt	model=ModelFactory.getInstance().getFacultyModel();
String dob="05/05/1995";
SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
Date date=format.parse(dob);

FacultyDTO dto=new FacultyDTO();
dto.setId(1l);
/*dto.setFirstName("Manish");
dto.setLastName("Pushpad");
dto.setGender("M");
dto.setMobNo("7898658967");
*///dto.setDob(date);
//dto.setCollegeId(1);
//dto.setEmailId("manish@gmail.com");
//dto.setCollegeName("PCST");
//dto.setCourseId(1);
//dto.setCourseName("BE");
//dto.setSubjectId(1);
/*dto.setSubjectName("cse");
dto.setQualification("BE,MTech.");
dto.setCreatedBy("root");
dto.setModifiedBy("root");
dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
dto.setModifiedDatetime(new  Timestamp(System.currentTimeMillis()));
*/
try {
	//model.add(dto);
	//model.update(dto);
	model.delete(dto);
} catch (ApplicationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
