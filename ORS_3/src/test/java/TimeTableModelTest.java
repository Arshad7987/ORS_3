import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.TimeTableModelInt;

public class TimeTableModelTest {
public static void main(String[] args) throws ParseException {
	TimeTableModelInt model=ModelFactory.getInstance().getTimeTableModel();
TimeTableDTO dto=new TimeTableDTO();
String date="12/05/2019";
SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
Date edate=format.parse(date);
dto.setId(1l);
dto.setCourseId(1);
dto.setCourseName("BE");
dto.setSubjectId(1);
dto.setSubjectName("CSE");
dto.setSemester("I");
dto.setExamDate(edate);
dto.setTime("10:00 AM -01:00 PM");
dto.setCreatedBy("Admin");
dto.setModifiedBy("root");
dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
dto.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));


try {
	try {
		try {
			model.update(dto);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (DuplicateRecordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

} catch (ApplicationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
