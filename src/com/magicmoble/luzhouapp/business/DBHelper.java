package com.magicmoble.luzhouapp.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
//	public static final String url = "jdbc:mysql://122.152.216.95:3306/sjlz?characterEncoding=utf-8";
//	public static final String user = "sjlz";
//	public static final String password = "qwe123";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sjlz?characterEncoding=utf-8";
	public static final String user = "root";
	public static final String password = "chenloveguitar@8";
//	public static final String password = "admin123456";
	public Connection conn = null;
	public PreparedStatement pst = null;

	public DBHelper(String sql) {
		try {

			byte[] bytes = sql.getBytes();
			String _sql = new String(bytes, "utf-8");
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
			this.pst = this.conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location: F:\新建文件夹 (5)\classes\
 * 
 * Qualified Name: cn.xinxing.business.DBHelper
 * 
 * JD-Core Version: 0.7.0.1
 * 
 */