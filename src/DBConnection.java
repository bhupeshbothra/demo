import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;


public class DBConnection {
	// static reference to itself

	private static DBConnection instance = new DBConnection();

	// private constructor
	private DBConnection() {

	}
	
	private Connection createConnection() {
		
		Connection connection = null;
	
		try {
			
			String factory_context ="com.ibm.websphere.naming.WsnInitialContextFactory";
			String provider_URL_context ="iiop://localhost:2810";
			String dataSource = "HLMDatasourceJNDI";
	
			
		
			
			Hashtable<String, String> pdEnv = new Hashtable<String, String>();
			
			pdEnv.put(Context.INITIAL_CONTEXT_FACTORY,factory_context);
			pdEnv.put(Context.PROVIDER_URL,provider_URL_context);	
			
			javax.naming.Context ctx = new javax.naming.InitialContext(pdEnv);
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(dataSource);

			connection = ds.getConnection();


		} catch (SQLException e) {
			//log.debug("ERROR: Unable to Connect to Database");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}
}