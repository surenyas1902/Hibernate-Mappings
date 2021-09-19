package jdbc;

import java.sql.DriverManager;

import java.sql.*;

public class TestJDBC {

	public static void main(String[] args) {
		String jdbcURLString = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String userString = "hbstudent";
		String passwordString = "hbstudent";
		try {
			System.out.println("Connecting to database: " + jdbcURLString);
			
			Connection connection = DriverManager.getConnection(jdbcURLString, userString, passwordString);
			System.out.println("Connection successful");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
