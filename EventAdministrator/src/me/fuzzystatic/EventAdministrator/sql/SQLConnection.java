package me.fuzzystatic.EventAdministrator.sql;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class SQLConnection {

	private static Connection connection;
	
	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
