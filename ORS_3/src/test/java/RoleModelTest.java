import java.sql.Timestamp;
import java.util.List;

import in.co.sunrays.proj3.dto.RoleDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.RoleModelHibImpl;
import in.co.sunrays.proj3.model.RoleModelJDBCImpl;
import in.co.sunrays.proj3.util.DataUtility;

public class RoleModelTest {
	public static boolean jdbcTest(RoleDTO bean) throws DatabaseException, ApplicationException, DuplicateRecordException{
		boolean pas=false;
		
		
		RoleModelJDBCImpl model=new RoleModelJDBCImpl();
		
		//System.out.println("next PK ="+model.nextPK());
		//System.out.println("Add Methd PK="+model.add(bean));
		//model.update(bean);
		
        //System.out.println(model.list(0, 0).size());	
		//System.out.println(model.search(bean, 1, 10).size());
		//System.out.println(model.findByPK(1).getName());
		model.delete(bean);
		pas=true;
		return pas;
	}
	public static boolean hibernateTest(RoleDTO bean) throws DuplicateRecordException{
		boolean pas=false;
		
		
		
		RoleModelHibImpl model=new RoleModelHibImpl();
		try {
			/*RoleDTO dto=model.findByName("Admin");
			System.out.println("find by name="+dto.getName());
			RoleDTO r=model.findByPK(1);
			System.out.println("find by pk="+r.getName());*/
			//model.update(bean);
			//System.out.println("Add PK="+model.add(bean));
			//model.delete(bean);
			//List l=model.list();
			List l=model.search(bean,1,10);
			System.out.println("List="+l.size());
			
			pas=true;
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return pas;
	}
	
	public static void main(String[] args) throws DatabaseException, DuplicateRecordException, ApplicationException {
		RoleDTO bean=new RoleDTO();
		bean.setId(3L);
		bean.setName("COLLEGE");
		bean.setDescription("COLLEGE");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		bean.setModifiedDatetime(new Timestamp(System.currentTimeMillis()));
		boolean b=false;
		//b = hibernateTest(bean);
	b=	jdbcTest(bean);
		System.out.println("JDBC TEST PAS="+b);
	}
}
