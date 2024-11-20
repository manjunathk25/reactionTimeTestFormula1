package com.f1.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionfactory {
	public static Connection requestConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/formula1";
		String usr = "root";
		String pass = "tiger";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, usr, pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return con;	
	}
}
