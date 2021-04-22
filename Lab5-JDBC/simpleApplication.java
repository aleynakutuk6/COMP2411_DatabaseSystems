import java.io.*;
import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class simpleApplication
{
	public static void main(String args[]) throws SQLException, IOException
	{
		String username, password;
		username = ".....";			// Your Oracle Account ID
		password = "....."; 		// Password of Oracle Account

		// Connection
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleConnection conn = 
			(OracleConnection)DriverManager.getConnection(
			 "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms",
			 username, password);
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT EMPNO, ENAME, JOB FROM EMP");
		while (rset.next())
		{
			System.out.println(rset.getInt(1) 
			+ " " + rset.getString(2) 
			+ " " + rset.getString(3));
		}
		System.out.println();
		conn.close();
	}
}
