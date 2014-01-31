package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLUpdate {

	public static String updatePlayerData(String prefix, String player) {
		return "UPDATE " + prefix + SQLSchema.TABLE_PLAYERS
				+ "SET lastlogin='" + System.currentTimeMillis()/1000 + "'"
				+ "WHERE name='" + player + "'";
	}
	
	public static String insertPlayerData(String prefix, String player) {
		return "INSERT INTO " + prefix + SQLSchema.TABLE_PLAYERS + "(name, lastlogin)"
				+ "VALUES ('" + player + "', '" + System.currentTimeMillis()/1000 + "')";
	}
    
    public static boolean playerData(Connection connection, String prefix, String player) {
    	String checkQuery = "SELECT count() FROM "+ prefix + SQLSchema.TABLE_PLAYERS + " WHERE name '" + player + "'";
		try {
			PreparedStatement ps1 = connection.prepareStatement(checkQuery);
			ResultSet resultSet = ps1.executeQuery();
			if(resultSet.next()) {	
				PreparedStatement ps2 = connection.prepareStatement(updatePlayerData(prefix, player));
				ps2.executeUpdate();
			} else {
				PreparedStatement ps2 = connection.prepareStatement(insertPlayerData(prefix, player));
				ps2.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player data.");
			e.printStackTrace();
		}
		return false;
	}
    
    public static String totalPveData(String prefix, String player) {
		return "UPDATE "
				+ prefix + SQLSchema.TABLE_PVE_STATS_TOTAL + " (name, lastlogin) "
				+ "VALUES ('" + player + "', '" + System.currentTimeMillis()/1000 + "')";
	}
    
    public static boolean insertTotalPveData(Connection connection, String prefix, String player) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(insertPlayerData(prefix, player));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert player data.");
			e.printStackTrace();
		}
		return false;
	}
}
