/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp;

import java.io.*;
import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

/**
 *
 * @author aleyna
 */
public class JavaApp {

   public static void main(String args[]) throws SQLException, IOException
	{
		String user, pass, snum, namer,lognum, selectednum;
		int enumber,lognumber,selectednumber ;
		
		user = "hr";			// Your Oracle Account ID
		pass = "arDA1998"; 		// Password of Oracle Account

		// ConnectionDatabase URL
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleConnection conn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", user, pass);
		
		System.out.println(
			"Enter correct number to login into system(-1 to exit)");
		System.out.println();
                
                lognum = readEntry("1 for student/2 for administrator login: ");
                lognumber = Integer.valueOf(lognum).intValue();
              /*
                while (lognumber != -1)
                {
                    
                if(lognumber == 1)
                {
                System.out.println(
			"Select the option from the below"
                                + "1) list all the courses in the system"
                                + "2) list all courses you are registered"
                                + "3) register to a course"
                                + "4) modify your personal information");
                
		System.out.println();
                lognum = readEntry("Enter your choice(-1 to exit): ");
                lognumber = Integer.valueOf(lognum).intValue();
                
                
                }
                else if(lognumber == 2)
                {
                System.out.println(
			"Select the option from the below"
                                + "1) list all the courses in the system"
                                + "2) list all courses you are registered"
                                + "3) register to a course"
                                + "4) modify your personal information");
                
		System.out.println();
                 lognum = readEntry("Enter your choice(-1 to exit): ");
                 lognumber = Integer.valueOf(lognum).intValue();
                }
                else if(lognumber != -1)
                {
                    System.out.println("Invalid number");
		    System.out.println();
                    System.out.println(
			"Enter correct number to login into system(-1 to exit)");
		System.out.println();
                
                lognum = readEntry("1 for student/2 for administrator login: ");
                lognumber = Integer.valueOf(lognum).intValue();
                }
               
                }*/
                conn.close();

		
	}

	// readEntry function -- Read input string
	static String readEntry(String prompt)
	{
		try
		{
			StringBuffer buffer = new StringBuffer();
			System.out.print(prompt);
			System.out.flush();
			int c = System.in.read();
			while (c != '\n' && c != -1)
			{
				buffer.append((char)c);
				c = System.in.read();
			}
			return buffer.toString().trim();
		}
		catch (IOException e)
		{
			return "";
		}
	}
        /*
        public static void listAllCourses(Connection conn)
        {
            try
            {
                Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(
			"SELECT DISTINCT COURSE_ID,COURSE_TITLE,STAFF_NAME,SECTION FROM COURSES");
		while (rset.next())
		{
			if (!rset.wasNull())
			{
				System.out.println(rset.getString(1) + " " + rset.getString(2) + " " +
						rset.getString(3) + " " + rset.getString(4));
			}
		}
		System.out.println();
        
            }catch (SQLException e) {
            e.printStackTrace();}
        }*/
}
