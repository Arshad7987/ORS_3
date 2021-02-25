import java.sql.Timestamp;
import java.util.List;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;

public class CourseModelTest {
public static void main(String[] args) {
	CourseModelInt model=ModelFactory.getInstance().getCourseModel();
	CourseDTO dto=new CourseDTO();
//dto.setId(1L);
	//dto.setName("Bsc");
/*dto.setDuration("3 YEARS");
dto.setDescription("Bsc");
dto.setCreatedBy("root");
dto.setModifiedBy("root");
dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
dto.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));
*/	
	
	try {
		System.out.println(model.findByPK(1).getName());;
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



}
}
