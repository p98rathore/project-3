
package in.co.rays.util;

import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyReader.
 */
public class PropertyReader {

	 /** The rb. */
 	private static ResourceBundle rb = ResourceBundle
	            .getBundle("in.co.sunrays.proj3.bundle.system");

	    /**
    	 * Return value of key.
    	 *
    	 * @param key the key
    	 * @return the value
    	 */

	    public static String getValue(String key) {

	        String val = null;

	        try {
	            val = rb.getString(key);
	        } catch (Exception e) {
	         val = key;
	        }

	        return val;

	    }

	    /**
    	 * Gets String after placing param values.
    	 *
    	 * @param key the key
    	 * @param param the param
    	 * @return String
    	 */
	    public static String getValue(String key, String param) {
	        String msg = getValue(key);
	        msg = msg.replace("{0}", param);
	        return msg;
	    }

	    /**
    	 * Gets String after placing params values.
    	 *
    	 * @param key the key
    	 * @param params the params
    	 * @return the value
    	 */
	    public static String getValue(String key, String[] params) {
	        String msg = getValue(key);
	        for (int i = 0; i < params.length; i++) {
	            msg = msg.replace("{" + i + "}", params[i]);
	        }
	        return msg;
	    }

	    /**
    	 * Test method.
    	 *
    	 * @param args the arguments
    	 */

	    public static void main(String[] args) {
	        String[] params = { "Roll No" };
	        System.out.println(PropertyReader.getValue("error.require", params));
	    }

	}
