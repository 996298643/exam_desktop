package com.css.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库管理类
 */
public class DBManager {

	private static DruidDataSource dataSource = null;
	private static final Properties config = new Properties();

	/**
	 * 静态块加载JDBC驱动
	 */
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			config.load(new FileInputStream("./config/database.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initDataSource();
	}

	private static void initDataSource() {
		dataSource = new DruidDataSource();
		dataSource.setUrl(config.getProperty("url"));
		dataSource.setMaxActive(Integer.parseInt(config.getProperty("maxActive")));
		dataSource.setMinIdle(Integer.parseInt(config.getProperty("minIdle")));
		dataSource.setMaxWait(Integer.parseInt(config.getProperty("maxWait")));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(config.getProperty("testWhile")));
	}

	/**
	 * 从Durid数据库连接池中获取Connection对象
	 */
	public static Connection getConnection() {
		if (dataSource == null) {
			initDataSource();
		}
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭连接池
	 */
	public static void closeDataBases() {
		if (dataSource != null)
			dataSource.close();
	}

	public static void execute(String sql) {
		Connection conn = getConnection();
		try {
			Statement state = conn.createStatement();
			state.execute(sql);
			if (conn != null)
				conn.close();
			if (state != null)
				state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet executeQuery(String sql) {
		Connection conn = getConnection();
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
