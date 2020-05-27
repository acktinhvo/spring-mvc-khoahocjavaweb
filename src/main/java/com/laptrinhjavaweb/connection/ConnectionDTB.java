package com.laptrinhjavaweb.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDTB {
	public Connection checkConnection(Connection conn) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khoahoclaptrinhjavaweb1", "root",
					"Abc12345");
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}
}
