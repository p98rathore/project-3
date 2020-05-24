
package in.co.rays.exception;

// TODO: Auto-generated Javadoc
/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred.
 *
 * @author Facade
 * @version 1.0
 * Facade
 */
public class DatabaseException extends Exception {
	 
 	/**
 	 * Instantiates a new database exception.
 	 *
 	 * @param msg            : Error message
 	 */
    public DatabaseException(String msg) {
        super(msg);
    }
}

