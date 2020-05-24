package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModelHibImpl;
import in.co.rays.model.UserModelInt;
import in.co.rays.model.UserModelJDBCImpl;

public class UserTest {

	
	
	private static UserModelInt model =new UserModelJDBCImpl();
	//private static UserModelInt model = new UserModelHibImpl();
	public static void main(String[] args) throws ParseException, ApplicationException, RecordNotFoundException, DuplicateRecordException {
		//testAdd();
	//testUpdate();
		//testFLogin();
	//testDelete();
	//	testFPK();
	testpSearch();
		//testSearch();
		//testpList();
		//testList();
//	testRegister();
	//	testChangePass();
//	testAuthenticate();
		//testforgret();
		
	}

	private static void testforgret() throws ApplicationException, RecordNotFoundException {
		model.forgetPassword("naresh@gmail.com");
		
	}

	private static void testAuthenticate() throws ApplicationException, RecordNotFoundException {
		UserDTO dto =null;
		dto=model.authenticate("naresh@gmail.com","newPassword");
		if(dto!=null){
			System.out.println("user is right");
		}
		
	}

	private static void testChangePass() throws RecordNotFoundException, ApplicationException, DuplicateRecordException {
		UserDTO dto= null;
		//dto.setPassword("newPassword");
		model.changePassword(7, "1234", "newPassword");
		
	}

	private static void testRegister() throws ApplicationException, DuplicateRecordException, ParseException {
		UserDTO dto = new UserDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dto.setFirstName("anil");
		dto.setLastName("meena");
		dto.setRoleId(1);
		dto.setDob(sdf.parse("20/09/1996"));
		dto.setPassword("mohanmeena");
		dto.setLogin("mandloinaresh@gmail.com");
		model.registerUser(dto);
		
	}

	private static void testList() throws ApplicationException {
		UserDTO dto=new  UserDTO();
		
		List list=null;
		list=model.list();
		
		Iterator it =list.iterator();
		while(it.hasNext()){
			dto=(UserDTO)it.next();
			System.out.println(dto.getFirstName()+dto.getLastName());
		}
	}

	private static void testpList() throws ApplicationException {
		UserDTO dto= new UserDTO();
		List list =null;
		list=model.list(1, 2);
		Iterator it =list.iterator();
		
		while(it.hasNext()){
			dto=(UserDTO)it.next();
			System.out.println(dto.getFirstName()+dto.getLastName());
		}
	}

	private static void testSearch() throws ApplicationException {
		UserDTO dto= new UserDTO();
		List list=null;
		list=model.search(dto);
		
		Iterator it = list.iterator();
		while(it.hasNext()){
			dto=(UserDTO) it.next();
			System.out.println(dto.getFirstName()+dto.getLastName());
		}
		
	}

	private static void testpSearch() throws ApplicationException {
		UserDTO dto = new UserDTO();
	dto.setRoleId(2);
List list=null;
		int pageNo=1;
			int pageSize=7;	
list=	model.search(dto, pageNo, pageSize);
	
	Iterator it =list.iterator();
	while(it.hasNext()){
		dto=(UserDTO) it.next();
		System.out.println(dto.getFirstName());
		System.out.println(dto.getLastName());
	}
		
	}

	private static void testFPK() throws ApplicationException, RecordNotFoundException {
		UserDTO dto= new UserDTO();
	//	dto.setId(2);
	dto=	model.findByPK(7);
		System.out.println(dto.getFirstName());
	}

	private static void testDelete() throws ApplicationException {
		UserDTO dto = new UserDTO();
		dto.setId(2);
		model.delete(dto);
		
	}

	private static void testFLogin() throws ApplicationException {
		UserDTO dto = new UserDTO();
		dto=model.findByLogin("raul@gmail.com");
		System.out.println(dto.getFirstName());
	}

	private static void testUpdate() throws ParseException, ApplicationException, DuplicateRecordException {
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
		UserDTO dto= new UserDTO();
		dto.setId(2);
		dto.setRoleId(2);
		dto.setFirstName("mayank");
		dto.setLastName("sdd");
		dto.setCreatedBy("pradeep");
		dto.setDob(sdf.parse("30/10/2000"));
		model.update(dto);
	}

	private static void testAdd() throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
		UserDTO dto = new UserDTO();
		try{
		dto.setFirstName("naresh");
		dto.setLastName("lastName");
		dto.setLogin("naresh@gmail.com");
		dto.setPassword("1234");
		dto.setDob(sdf.parse("30/10/1997"));
		dto.setMobileNo("ddsdssd");
		dto.setRoleId(2);
		dto.setGender("gender");

		model.add(dto);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	
	}
}
