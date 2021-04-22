/************************************/
/* Employee JDBC					*/
/************************************/

import java.io.*;
import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class hw2
{
	public static void main(String args[]) throws SQLException, IOException, Exception
	{
		String user, pass, snum, namer,lognum, selectednum;
		int enumber,lognumber,selectednumber ;

		user = "hr";			// Your Oracle Account ID
		pass = "arDA1998"; 		// Password of Oracle Account

		// Connection
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleConnection conn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", user, pass);
		
		System.out.println(
			"Enter correct number to login into system(-1 to exit)");
		System.out.println();
                
                lognum = readEntry("1 for student/2 for administrator login: ");
                lognumber = Integer.valueOf(lognum).intValue();

			while (lognumber != -1)
                {
                    
                if(lognumber == 1)
                {
					while (lognumber != -1)
                {
					System.out.println("Select the option from the below");
					System.out.println("1) list all the courses in the system");
					System.out.println("2) list all courses you are registered");
					System.out.println("3) register to a course");
					System.out.println("4) modify your personal information");
                
					lognum = readEntry("Enter your choice(-1 to exit): ");
					lognumber = Integer.valueOf(lognum).intValue();
					if(lognumber == 1) {System.out.println("Courses in the system");
					listAllCourses(conn);
					}
					else if(lognumber == 2){
						
						System.out.println("Registered Courses in the system");
						
					listRegisteredCourses(conn);
					}
					else if(lognumber == 3){
						RegisterToCourse(conn);
						System.out.println("Successfully registered to the course");
					}
					else if(lognumber == 4){
						//modifyInformation(conn);
						System.out.println("Successfully modified");
					}
                }
                }
                else if(lognumber == 2)
                {
					while (lognumber != -1)
                {
                System.out.println("Select the option from the below");
                System.out.println( "1) list all the courses and students in the system"); //from which table should we take it?
                System.out.println( "2) list all students in any department");
                System.out.println( "3) add new course/student ");
                System.out.println( "4) modify course/student information");
				System.out.println( "5) modify grade of a student");
				
                
                 lognum = readEntry("Enter your choice(-1 to exit): ");
                 lognumber = Integer.valueOf(lognum).intValue();
				 if(lognumber == 1) {}
					else if(lognumber == 2){
						
						listStudentsFromDepartment(conn);
					}
					else if(lognumber == 3){}
					else if(lognumber == 4){
						
					}
					else if(lognumber == 5){
					modifyGrade(conn);
					}
				}
                }
                else if(lognumber != -1)
                {
                    System.out.println("Invalid number");
					System.out.println();
                    System.out.println("Enter correct number to login into system(-1 to exit)");
		
					lognum = readEntry("1 for student/2 for administrator login: ");
					lognumber = Integer.valueOf(lognum).intValue();
                }
			}
                
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
	public static void modifyInformation(Connection conn)
        {
			int numid;
			String id;
			id = readEntry("Give all STUDENT_ID,STUDENT_NAME,DEPARTMENT,ADDRESS,BIRTHDATE,GENDER in order: ");
			numid = Integer.valueOf(id).intValue();
			
			PreparedStatement prepareQuery = conn.prepareStatement(
			"SELECT DISTINCT COURSE_ID FROM ENROLLMENT WHERE STUDENT_ID = ?");
			
			id = readEntry("enter student ID: ");
			numid = Integer.valueOf(id).intValue();
			prepareQuery.setString(1, numid);
		}*/
	
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
        }
		public static void listRegisteredCourses(Connection conn)
        {
            try
            {
                PreparedStatement prepareQuery = conn.prepareStatement(
			"SELECT DISTINCT COURSE_ID FROM ENROLLMENT WHERE STUDENT_ID = ?");
			ResultSet rset=null;
			
			String id;
			id = readEntry("enter student ID: ");
			prepareQuery.setString(1, id);
			rset = prepareQuery.executeQuery();
			while (rset.next())
			{
				if (!rset.wasNull())
				{
				System.out.println(rset.getString(1) + " " );
				}
			}
			System.out.println();
        
            }catch (SQLException e) {
            e.printStackTrace();}
        }
		
		public static void RegisterToCourse(Connection conn)throws Exception 
        {
			try
            {
			Statement stmt = null;
			String stuid,courseid,datestr;
			Date date;
			stuid = readEntry("enter student ID: ");
			courseid = readEntry("enter course ID: ");
			datestr = readEntry("enter date: ");
			SimpleDateFormat f=new SimpleDateFormat("dd-MMM-yyyy");
			date = f.parse(datestr); 
			
			String sql = "INSERT INTO ENROLLMENT (STUDENT_ID,COURSE_ID,REG_DATE,GRADE)" +
                   "VALUES (" + stuid +"," + courseid + ", " + date + ", 0)";
				   
			stmt = conn.createStatement();
            stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");
			
			}catch (SQLException e) {
            e.printStackTrace();}
		}
		
		public static void modifyGrade(Connection conn)
        {
			try
            {
			PreparedStatement updateGrade;

			String updateString = "UPDATE ENROLLMENT " + "SET GRADE = ? WHERE STUDENT_ID = ? AND COURSE_ID = ?";
			updateGrade = conn.prepareStatement(updateString);
			String stuid, courseid, gradestr;
			int grade;
			stuid = readEntry("enter student ID: ");
			courseid = readEntry("enter course ID: ");
			gradestr = readEntry("enter grade: ");
			grade = Integer.valueOf(gradestr).intValue();
			updateGrade.setInt(1,grade);
			updateGrade.setString(2,stuid);
			updateGrade.setString(2,courseid);
			updateGrade.executeUpdate();
			
			System.out.println();
        
            }catch (SQLException e) {
            e.printStackTrace();}
			
		}
		
		public static void listStudentsFromDepartment(Connection conn)
        {
			try
            {
                PreparedStatement prepareQuery = conn.prepareStatement(
			"SELECT DISTINCT STUDENT_ID FROM STUDENTS WHERE DEPARTMENT = ?");
			ResultSet rset=null;
			
			String dep;
			dep = readEntry("enter department: ");
			prepareQuery.setString(1, dep);
			rset = prepareQuery.executeQuery();
			
			System.out.println("All students in " + dep);
			while (rset.next())
			{
				if (!rset.wasNull())
				{
				System.out.println(rset.getString(1) + " " );
				}
			}
			System.out.println();
        
            }catch (SQLException e) {
            e.printStackTrace();}
		}
}
