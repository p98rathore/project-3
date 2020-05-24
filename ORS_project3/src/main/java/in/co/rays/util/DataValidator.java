
package in.co.rays.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Provide validation on input data.
 *
 * @author Proxy
 */
public class DataValidator 
{
	
	/**
	 * Checks if is name.
	 *
	 * @param name the name
	 * @return true, if is name
	 */
	public static boolean isName(String name) { 

		String namereg = "^[^-\\s][\\p{L} .']+$";
		
		

		//String sname = name.trim();

		if (isNotNull(name) && name.matches(namereg)) {

			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if value of Password is in between 8 and 12 characters.
	 *
	 * @param val the val
	 * @return true, if is password length
	 */
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Checks if is valid age.
	 *
	 * @param val the val
	 * @return true, if is valid age
	 */
	public static boolean isValidAge(String val)
	{
		
		boolean pass = false;
		if (isDate(val)) {
			Date cdate = new Date();
			try {
				Date userdate = DataUtility.formatter.parse(val);
				int age = cdate.getYear()-userdate.getYear();
				System.out.println("final age  "+age);
				if(age>=18){
					pass=true;
				}
			} catch (ParseException e) {
				/*e.printStackTrace();*/
			}
		}
		
		return pass;
	}
	
	/**
	 * Checks if is password.
	 *
	 * @param pass the pass
	 * @return true, if is password
	 */
	public static boolean isPassword(String pass) { // my method created
        
		System.out.println("validate pass");
		String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		//String passreg="^[0-9a-zA-Z]{5}$";
		//String spass = pass.trim();
		//int checkIndex = spass.indexOf(" ");
                                //checkIndex==-1
		if (isNotNull(pass) && pass.matches(passreg) ) {
			System.out.println("true");
			return true;
		}

		else {
			return false;
		}
	}
	
	/**
	 * Checks if is roll no.
	 *
	 * @param roll the roll
	 * @return true, if is roll no
	 */
	public static boolean isRollNo(String roll) { // my method created
		//System.out.println("rollno");
		//String rollreg = "((?=.*\\d).{1,4}(?=.*[A-Z]).{1,2}(?=.*\\d).{1,6})$";
		//String rollreg="^[a-zA-z\\s]+$";
		
//		String rollreg="^[0-9]{4}[A-Za-z]{2}[0-9]{2,6}$";
		String rollreg = "[a-zA-Z]{2}[0-9]{4}";
		//String sroll = roll.trim();

		if (DataValidator.isNotNull(roll)) 
		{
			boolean check = roll.matches(rollreg);
			System.out.println(check);
			return check;
		}
		return false;
		
	}
	
	/**
	 * Ckeck if value is Null.
	 *
	 * @param val :val
	 * @return boolean
	 */
 
	public static boolean isNull(String val)
	{
		if(val==null || val.trim().length()==0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	/**
	 * check if value is Not Null.
	 *
	 * @param val :value
	 * @return boolean
	 */
     
	public static boolean isNotNull(String val)
	{
		return !isNull(val);
	}
	
	/**
	 * check if an value is an Integer.
	 *
	 * @param val :value
	 * @return boolean
	 */
	 public static boolean isInteger(String val)
	 {
		 if(isNotNull(val))
		 {
			 try {
				 int i = Integer.parseInt(val);
				 return true;
			} catch (Exception e) {
				return false;
			}
		 }else
		 {
			 return false;
		 }
	 }
	 
 	/**
 	 * check if an value is an Long.
 	 *
 	 * @param val :value
 	 * @return boolean
 	 */
		 public static boolean isLong(String val)
		 {
			 if(isNotNull(val))
			 {
				 try {
					 long l = Long.parseLong(val);
					 return true;
				} catch (Exception e) {
					return false;
				}
			 }else
			 {
				 return false;
			 }
		 }
	 
 	/**
 	 * Check if value is valid EmailId.
 	 *
 	 * @param val :val
 	 * @return boolean
 	 */
	 public static boolean isEmail(String val)
	 {
		 String emailregex= "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	  
		 if(isNotNull(val))
		 {
			 try {
				 return val.matches(emailregex);
			} catch (Exception e) {
				return false;
			}	 
		 }else
		 {
			 return false;
		 } 
	 }
	 
 	/**
 	 * check if value is date.
 	 *
 	 * @param val :val
 	 * @return boolean
 	 */
	   public static boolean isDate(String val)
	   {
		    Date d=null;
//		    String s = val;
//			s = s.replace("-", "/");
		    if(isNotNull(val))
		    {
		    d =	DataUtility.getDate(val);
		    }
		    return d!=null;
	   }
	   
	   /**
		 * Checks if value is valid Phone No.
		 * 
		 * @param val :val
		 * @return boolean
		 */
		public static boolean isPhoneNo(String val) {

			String phonereg = "^[6-9][0-9]{9}$";

			if (isNotNull(val)) {
				try {
					return val.matches(phonereg);
				} catch (NumberFormatException e) {
					return false;
				}

			} else {
				return false;
			}
		}

		/**
		 * Checks if value of Mobile No is 10.
		 *
		 * @param val :value
		 * @return boolean
		 */
		public static boolean isPhoneLength(String val) {

			if (isNotNull(val) && val.length() == 10) {
				return true;
			} else {
				return false;
			}
		}
	   
	   
	 
	 /**
 	 * Test Above Methods.
 	 *
 	 * @param args :args
 	 */
	public static void main(String[] args) 
	{
//	  System.out.println(isNull("ssd"));
//	  System.out.println(isNotNull(""));//dought
//	  System.out.println(isInteger("2147483649"));
//	  System.out.println(isLong("50.5"));
//	  System.out.println(isEmail("rah@g.com"));
//	  System.out.println(isDate("18/11/1989"));
	//  System.out.println(isName("Ankur Agrawal"));
	}
	   
}
