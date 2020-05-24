package in.co.rays.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModelHibImpl;
import in.co.rays.model.CollegeModelInt;
import in.co.rays.model.CollegeModelJDBCImpl;

public class Collegetest {
	private static CollegeModelInt model = new CollegeModelJDBCImpl();
	
	
//	private static CollegeModelInt model = new CollegeModelHibImpl();
	
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//testAdd();
		testDelet();
		//testUpdate();
		//testfPK();
//	testsearch();
	//testList();
	}


	private static void testList() throws ApplicationException {
		CollegeDTO dto=null;
	List list=	model.list(1,10);
		Iterator it =list.iterator();
		while(it.hasNext()){
			dto= (CollegeDTO) it.next();
			System.out.println(dto.getName());
		}
	}


	private static void testsearch() throws ApplicationException {
		CollegeDTO dto= new CollegeDTO();
		List list=null;
	//	dto.setName("jnv");
	list=	model.search(dto,1,2);
		Iterator it =list.iterator();
		while(it.hasNext()){
			dto= (CollegeDTO) it.next();
			System.out.println(dto.getName());
		}
	}


	private static void testfPK() throws ApplicationException {
		CollegeDTO dto= null;
	dto=	model.findByPK(5);
		System.out.println(dto.getName());
	}


	private static void testDelet() throws ApplicationException {
		CollegeDTO dto = new  CollegeDTO();
		dto.setId(5);
		model.delete(dto);
	}


	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
CollegeDTO dto=new CollegeDTO();
dto.setId(5);
dto.setName("lnct");
dto.setCity("bhopal");
dto.setAddress("bhopal");
dto.setState("mp");
model.update(dto);
		
	}


	private static void testAdd() throws ApplicationException, DuplicateRecordException {
		CollegeDTO dto = new CollegeDTO();
		dto.setName("asahsh");
		dto.setAddress("Bhopal");
		dto.setCity("Bhopal");
		dto.setState("MP");
		model.add(dto);
		
	}
}
