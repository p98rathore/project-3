package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.StudentModelHibImpl;
import in.co.rays.model.StudentModelInt;
import in.co.rays.model.StudentModelJDBCImpl;

public class StudentTest {

	private static StudentModelInt model= new StudentModelJDBCImpl() ;	
//	private static StudentModelInt model= new StudentModelHibImpl() ;
	public static void main(String[] args) throws ParseException, DuplicateRecordException, ApplicationException {
		//testAdd();
	//	testfindLogin();
		//testUpdate();
		//testDelete();
	//	testfPk();
	//testSearch();
		//testList();
		
	}

	private static void testList() throws ApplicationException {
		List list=null;
		StudentDTO dto= new StudentDTO();
		list=model.list(0,1);
		Iterator it =list.iterator();
		while(it.hasNext()){
			dto=(StudentDTO) it.next();
			System.out.println(dto.getCollegeID());
		}
		
	}

	private static void testSearch() throws ApplicationException {
		List list=null;
		StudentDTO dto= new StudentDTO();
		dto.setFirstName("rahul");
	list=	model.search(dto,1,2);
	Iterator it = list.iterator();
	while(it.hasNext()){
		dto= (StudentDTO) it.next();
		System.out.println(dto.getLastName());
	}
	
	}

	private static void testfPk() throws ApplicationException {
		StudentDTO dto= new StudentDTO();
	dto.setId(2);
	dto=	model.findByPK(2);
		System.out.println(dto.getFirstName());
		
	}

	private static void testDelete() throws ApplicationException {
		StudentDTO dto =new StudentDTO();
		dto.setId(2);
		model.delete(dto);
		
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
		StudentDTO dto = new StudentDTO();
		dto.setId(1);
		dto.setFirstName("rahul");
		dto.setLastName("sindhi");
		dto.setEmail("hrat@gmail.com");
		dto.setCollegeID(2);
		dto.setCollegeName("vit");
		model.update(dto);
		
	}

	private static void testfindLogin() throws ApplicationException {
StudentDTO dto = new StudentDTO();
//dto.setLastName("amit@gmail.com");
dto=model.findByEmailId("naresg@gmail.com");
System.out.println(dto.getFirstName()+dto.getLastName());
		
	}

	private static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		StudentDTO dto=new StudentDTO();
		dto.setFirstName("lala");
		dto.setLastName("singh");
		dto.setCollegeID(2);
		dto.setEmail("llasa@gmail.com");
		dto.setDob(sdf.parse("30/10/1999"));
		model.add(dto);
		
		
	}
}

