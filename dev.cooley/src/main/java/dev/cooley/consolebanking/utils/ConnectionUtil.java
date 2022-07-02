package dev.cooley.consolebanking.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Singleton Design pattern: makes sure that you only have one instance of something.
// factory design pattern: generates and returns a particular object.
public class ConnectionUtil {
	
	// singleton: private constructor, public static synchronized getter method.
	private static ConnectionUtil connUtil;
	private Properties prop;
	
	private ConnectionUtil() {
		
		prop = new Properties();
		InputStream propFile = ConnectionUtil.class.getClassLoader().getResourceAsStream("database.properties");
		try {
			prop.load(propFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (connUtil == null) {
			connUtil = new ConnectionUtil();
		}
		return connUtil;
	}
	
	//factory: creates Connection objects and returns them.
	public Connection openConnection() {
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("usr");
		String pass = prop.getProperty("pas");
		
		Connection conn = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
	
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
