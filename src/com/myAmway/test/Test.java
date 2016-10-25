package com.myAmway.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
	public static void main(String[] args) {
        Connection conn = null; 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/myAmway"; 
			String user = "root"; 
			String password = "root"; 
			conn = DriverManager.getConnection(user,password,url); 
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
}
