package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OlympiadDB {
	
	
	
	public static Connection getConnection() throws IOException,SQLException,ClassNotFoundException {
	
	FileInputStream fis=new FileInputStream("Resources/DB.properties");
	
	Properties p=new Properties();
	p.load(fis);
	String a=p.getProperty("url");
	String b=p.getProperty("driver");
	String c=p.getProperty("username");
	String d=p.getProperty("password");

     Class.forName(b);
	Connection con=DriverManager.getConnection(a,c,d);
	
	
	return con;
	
}
}