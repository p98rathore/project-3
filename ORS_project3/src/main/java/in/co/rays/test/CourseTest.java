package in.co.rays.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModelHibImpl;
import in.co.rays.model.CourseModelInt;
import in.co.rays.model.CourseModelJDBCImpl;

public class CourseTest {
	
	private static CourseModelInt model= new CourseModelHibImpl();
	
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		testAdd();
		//find();
		//update();
		//delet();
		//Search();
		//list();
	}
	


	private static void list() throws ApplicationException {
	CourseDTO dto = new CourseDTO();
	List list= null;
	list=model.list(1, 2);
	Iterator it = list.iterator();
	while(it.hasNext()){
		dto= (CourseDTO) it.next();
		System.out.println(dto.getName());
	}
	}



	private static void Search() throws ApplicationException {
		CourseDTO dto =new CourseDTO();
		List list= null;
		//dto.setName("me");
		list=model.search(dto, 1, 2);
		Iterator it = list.iterator();
		while(it.hasNext()){
			dto= (CourseDTO) it.next();
			System.out.println(dto.getName());
		}
		
	}



	private static void delet() throws ApplicationException {
		
		CourseDTO dto = new CourseDTO();
		dto.setId(6);
		model.delete(dto);
		
	}



	private static void update() throws ApplicationException, DuplicateRecordException {
		CourseDTO dto= new  CourseDTO();
		dto.setId(6);
		dto.setName("rani");
		model.update(dto);
	}



	private static void find() throws ApplicationException {
		CourseDTO dto= new CourseDTO();
	dto=model.findByPK(4l);
System.out.println(	dto.getName());
		
	}



	private static void testAdd() throws ApplicationException, DuplicateRecordException {
	CourseDTO dto = new CourseDTO();
	dto.setName("java");
	dto.setDuration("1 ys");
	dto.setDescription("corporate");
	model.add(dto);
		
	}

}
