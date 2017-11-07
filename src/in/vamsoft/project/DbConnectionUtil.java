package in.vamsoft.project;

import java.beans.Statement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import in.vamsoft.project.DbConnectionUtil;

public class DbConnectionUtil {
	public static Logger logger=Logger.getLogger(DbConnectionUtil.class);
	
	public static Connection getConnection() {
	
	String driver=null;
	String url=null;
	String username=null;
	String password=null;	
	Connection connection=null;
	try {
		FileReader reader=new FileReader("src//db.properties");
		Properties properties =new Properties();
		properties.load(reader);
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		username=properties.getProperty("username");
		password=properties.getProperty("password");
		Class.forName(driver);
		connection=DriverManager.getConnection(url,username,password);
		logger.info("Connection Done");
	}catch (FileNotFoundException e) {
		logger.info("db properties file not found");
	}catch (IOException e) {
		logger.error("Could not load properties");			
	}catch (ClassNotFoundException e) {
		logger.error("Error in loading database driver",e);
	}catch (SQLException se) {
		logger.error("Error While establishing the connection",se);
	}
	return connection;
	
}
public static void closeConnection(Connection connection) {
	try {
		connection.close();
		logger.info("Connection closed Successfully");
	} catch (SQLException e) {
		logger.error("Error in closing connection",e);
	}
	
}


}


