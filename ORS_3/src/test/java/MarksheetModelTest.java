import java.sql.Timestamp;

import in.co.sunrays.proj3.dto.MarksheetDTO;
import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.MarksheetModelInt;
import in.co.sunrays.proj3.model.ModelFactory;

public class MarksheetModelTest {
public static void main(String[] args) {
	MarksheetModelInt model=ModelFactory.getInstance().getMarksheetModel();
StudentDTO dto2=new StudentDTO();
dto2.setFirstName("Naqeeb");
dto2.setLastName("Khan");
MarksheetDTO dto=new MarksheetDTO();
/*dto.setStudentId(1);

dto.setRollNo("cs1");
dto.setMaths(80);
dto.setChemistry(50);
dto.setPhysics(50);
dto.setCreatedBy("root");
dto.setModifiedBy("root");
dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
dto.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));*/
try {
	//model.add(dto);
	System.out.println(model.getMeritList(1, 10).size());
} catch (ApplicationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
