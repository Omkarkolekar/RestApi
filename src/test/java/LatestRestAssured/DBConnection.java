package LatestRestAssured;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	 static final String DB_URL = "jdbc:mysql://localhost/Employeeportal";
	   static final String USER = "root";
	   static final String PASS = "root";
	   static final String QUERY = "SELECT id, name, dept, age  FROM Employeeinfo";

	   public static void main(String[] args) {
		   System.out.print("id" + DB_URL);
	      try{
	    	  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(QUERY);
	         // Extract data from result set
	         while (rs.next()) {
	            // Retrieve by column name
	            System.out.print("id" + rs.getInt("id"));
	            System.out.print("name" + rs.getInt("name"));
	            System.out.print("dept" + rs.getString("dept"));
	            System.out.println("age" + rs.getString("age"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	   }
}
