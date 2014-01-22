package me.fuzzystatic.EventAdministrator.sql;

import java.sql.SQLException;

public class SQLConnection {

	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
