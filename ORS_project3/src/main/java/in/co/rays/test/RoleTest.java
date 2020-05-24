package in.co.rays.test;

import java.util.Iterator;
import java.util.List;

import javax.management.relation.Role;

import in.co.rays.dto.RoleDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.RoleModelHibImpl;
import in.co.rays.model.RoleModelInt;
import in.co.rays.model.RoleModelJDBCImpl;

public class RoleTest {
	private static RoleModelInt model= new RoleModelJDBCImpl();
	//private static RoleModelInt model= new RoleModelHibImpl();
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		
		
		//testadd();
		//testUpdate();
		//testDelete();
		//testfindPk();
		//testsearch();
		//testList();
		
	}

	private static void testList() throws ApplicationException {
RoleDTO dto=new RoleDTO();
List list=null;
list=model.list(2,2);
Iterator it = list.iterator();
while(it.hasNext()){
	dto= (RoleDTO) it.next();
	System.out.println(dto.getName());
}
		
	}

	private static void testsearch() throws ApplicationException {
		RoleDTO dto= new RoleDTO();
		List list=null;
		dto.setName("admin");
	list=model.search(dto,1,2);
	
	Iterator it = list.iterator();
	while(it.hasNext()){
		dto= (RoleDTO) it.next();
		System.out.println(dto.getName());
	}
		
	}

	private static void testfindPk() throws ApplicationException {
		RoleDTO dto= new RoleDTO();
		//dto.setId(1);
		dto=model.findByPK(1);
		System.out.println(dto.getName()+dto.getDescription());
		
	}

	private static void testDelete() throws ApplicationException {
		RoleDTO dto = new RoleDTO();
		dto.setId(5);
		model.delete(dto);
		
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
	RoleDTO dto= new RoleDTO();
	dto.setId(7);
	dto.setName("kiosk");
	dto.setDescription("online");
	model.update(dto);
		
	}

	private static void testadd() throws ApplicationException, DuplicateRecordException {
		RoleDTO dto= new RoleDTO();
		dto.setName("Pradeep");
		dto.setDescription("Admin");
		model.add(dto);
		
	}
}
