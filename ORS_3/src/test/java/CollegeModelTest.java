import java.sql.Timestamp;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelHibImpl;
import in.co.sunrays.proj3.model.CollegeModelInt;
import in.co.sunrays.proj3.model.ModelFactory;

public class CollegeModelTest {
public static void main(String[] args) {
	CollegeDTO dto=new CollegeDTO();
	dto.setName("PCST");
	dto.setAddress("Bhopal");
	dto.setCity("Bhopal");
	dto.setState("MP");
	dto.setCreatedBy("root");
	dto.setModifiedBy("root");
	dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
	dto.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));
	dto.setPhoneNo("8956895689");
	
CollegeModelInt model=	(CollegeModelInt) ModelFactory.getInstance().getCollegeModel();
try {
	model.add(dto);
} catch (ApplicationException | DuplicateRecordException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
