package com.example.wen.instructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/instructor_info?useSSL=false";
		String user = "webemployee";
		String pwd = "webemployee";
		
		try {
			System.out.println("connecting to schema");
			Connection conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("connecting to schema granted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
