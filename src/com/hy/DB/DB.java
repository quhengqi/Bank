package com.hy.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static Connection conn ;
	{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			try {
				DB.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank?"
				+ "useUnicode=true&characterEnicoding=utf-8", "root", "root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConn() {
		return conn;
	}
}
