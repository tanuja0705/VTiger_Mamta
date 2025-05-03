package com.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection con;
	public void getDataFromDB(String url, String username, String password) throws SQLException {
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) {
		}
	}
	
	public void getDataFromLocalDB() throws SQLException {
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student", "root", "root");
		}
		catch (Exception e) {
		}
	}
	
	public ResultSet exeQuery(String query) throws SQLException {
		ResultSet result = null;
		try {
			Statement state = con.createStatement();
			result = state.executeQuery(query);
		}
		catch (Exception e) {
		}
		
		return result;
	}
	
	public int exeUpdate(String query) throws SQLException {
		int result = 0;
		try {
			Statement state = con.createStatement();
			result = state.executeUpdate(query);
		}
		catch (Exception e) {
		}
		
		return result;
	}
	
	public void closeDbConnection() {
		try {
			con.close();
		}
		catch (Exception e) {
		}
	}

}
