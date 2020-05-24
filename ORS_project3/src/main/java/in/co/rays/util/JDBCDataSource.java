
package in.co.rays.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import in.co.rays.exception.ApplicationException;

// TODO: Auto-generated Javadoc
//TODO: Auto-generated Javadoc
/**
* The Class JDBCDataSource.
*/
public final class JDBCDataSource {

 /** JDBC Database connection pool ( DCP ). */
 private static JDBCDataSource datasource;

 /**
  * Instantiates a new JDBC data source.
  */
 private JDBCDataSource() {
 }

 /** The cpds. */
 private ComboPooledDataSource cpds = null;

 /**
  * Create instance of Connection Pool.
  *
  * @return instance of this class
  */
 public static JDBCDataSource getInstance() {
     if (datasource == null) {

         ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.proj3.bundle.system");

         datasource = new JDBCDataSource();
         datasource.cpds = new ComboPooledDataSource();
         
             try {
             	
					datasource.cpds.setDriverClass(rb.getString("driver"));
				    datasource.cpds.setJdbcUrl(rb.getString("url"));
		            datasource.cpds.setUser(rb.getString("username"));
		            datasource.cpds.setPassword(rb.getString("password"));
		            datasource.cpds.setInitialPoolSize(new Integer((String) rb.getString("initialPoolSize")));
		            datasource.cpds.setAcquireIncrement(new Integer((String) rb.getString("acquireIncrement")));
		            datasource.cpds.setMaxPoolSize(new Integer((String) rb.getString("maxPoolSize")));
		            datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
		            datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
        
        
     }
     return datasource;
 }

 /**
  * Gets the connection from ComboPooledDataSource.
  *
  * @return connection
  * @throws SQLException the SQL exception
  */
 public static Connection getConnection() throws SQLException  {
     return getInstance().cpds.getConnection();
 }

 /**
  * Closes a connection.
  *
  * @param connection the connection
  */
 public static void closeConnection(Connection connection) {
    if(connection!=null){
 	try {
 		
 		
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 }

 
 /**
  * Rollback connection.
  *
  * @param connection the connection
  */
 public static void trnRollback(Connection connection)
          {
     if (connection != null) {
         try {
             connection.rollback();
         } catch (SQLException ex) {
        	ex.printStackTrace();
         }
     }
 }

}
