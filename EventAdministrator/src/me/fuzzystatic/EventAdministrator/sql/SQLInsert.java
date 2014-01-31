package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLInsert {

	public static String playerData(String prefix, String player) {
		return "INSERT INTO "
				+ prefix + SQLSchema.TABLE_PLAYERS + " (name, lastlogin) "
				+ "VALUES ('" + player + "', '" + System.currentTimeMillis()/1000 + "')";
	}
    
    public static boolean insertPlayerData(Connection connection, String prefix, String player) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(playerData(prefix, player));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert player data.");
			e.printStackTrace();
		}
		return false;
	}
}
