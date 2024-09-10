package hotel.recommendation.system.databaseconfigration;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.CallableStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DataBaseConfigration extends PathHelper {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	private CallableStatement cs=null;
	static private DataBaseConfigration db=null;
	
	
	
	
	private DataBaseConfigration() throws SQLException,ClassNotFoundException,InterruptedException ,IOException{
	
	Properties property=new Properties();
	
	property.load(fr);
	
	String driver=property.getProperty("driver");
	String url=property.getProperty("url");
	String username=property.getProperty("username");
	String password=property.getProperty("password");
	
    Class.forName(driver);
	conn=DriverManager.getConnection(url,username,password);
	}
	
	public static DataBaseConfigration getInstance(){
		
		if(db==null)
			try {
				db=new DataBaseConfigration();
			} catch (ClassNotFoundException | SQLException | InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				
				System.out.println("error in DataBaseConfigration "+e);
				e.printStackTrace();
			}
		return db;
		
	}
	
	public PreparedStatement getPreparedStatement() {
		return pstmt;
	}
	
	public Statement getStatement() {
		return stmt;
	}
	public Connection getConnection() {
		return conn;
	}
	public ResultSet getResultSet() {
		return rs;
	}
	public CallableStatement getCallable() {
		return cs;
	}
	
	

}
