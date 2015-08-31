package idv.trashchu.finance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class PureJDBCUtil implements DatabaseUtil {
	
	//private static Log log = LogFactory.getLog(PureJDBCUtil.class);
	
	private static final String driver;
	private static final String url;
	private static final String user;
	private static final String password;
	
	static {
		try {
			Properties prop = new Properties();
			prop.loadFromXML(PureJDBCUtil.class.getClassLoader().getResourceAsStream("purejdbc.xml"));
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public PureJDBCUtil() {
		
	}
	
	private static final ThreadLocal<Connection> connection = new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {
		Connection c = (Connection) connection.get();

		if (c == null) {
        	
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}
            c = DriverManager.getConnection(url, user, password);
            
			connection.set(c);
		}

        return c;
	}
	
	public static void closeConnection() throws SQLException {
		Connection c = (Connection) connection.get();
		if (c != null) {
			c.close();
		}
		connection.set(null);
	}
}
