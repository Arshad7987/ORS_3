import java.sql.Timestamp;

import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.SubjectModelInt;

public class SubjectModelTest {
public static void main(String[] args) {
	SubjectModelInt model=ModelFactory.getInstance().getSubjectModel();
	SubjectDTO dto=new SubjectDTO();
	//dto.setId(1l);
	//dto.setName("CM");
	//dto.setCourseId(1);
	//dto.setCourseName("BE");
	//dto.setDescription("Civil Mechenics");
	dto.setSemester("I");
	/*dto.setCreatedBy("root");
	dto.setModifiedBy("root");
	dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
	dto.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));
	
	*/
	try {
		//model.add(dto);
		//model.update(dto);
System.out.println("size="+model.list().size());
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
