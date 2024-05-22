package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Ketnoi {
	public static Connection getConnection() {
		Connection conn = null; 
		try {
			String userName = "root";
			String password = "";
			String url = "jdbc:mysql://localhost:3307/quanliktx";
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Ok");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		Ketnoi.getConnection();
	}
}