package me.fuzzystatic.EventAdministrator.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class SQLConnection {

	private static Connection connection;
    private String mysqlURL = "jdbc:mysql://localhost:3306/DOCSIS_PKT_CABLE_CONFIG_REPOSITORY";
    private String user = "root";
    private String password = "comcast";
	
	private void connect()  {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			connection = DriverManager.getConnection(mysqlURL, user, password);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
