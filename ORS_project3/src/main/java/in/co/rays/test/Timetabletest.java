package in.co.rays.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.co.rays.dto.TimetableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.TimetableModelHibImpl;
import in.co.rays.model.TimetableModelInt;

public class Timetabletest {

	public static TimetableModelInt model= new TimetableModelHibImpl() ;
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		testadd();
	}

	private static void testadd() throws ParseException, ApplicationException, DuplicateRecordException {
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyy");
	TimetableDTO dto= new TimetableDTO();
	dto.setCourseId(1);
	
	dto.setSubId(1);
	dto.setExamDate(sdf.parse("30/09/2019"));
		dto.setDescription("asddddddd");
		
		model.add(dto);
	}
			
			
		
		
}