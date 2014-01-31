package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLSchema {
	
	public static String playersTable(String prefix) {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + "players`"
				+ "("
				+ "`id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`player` VARCHAR(16) DEFAULT NULL UNIQUE,"
				+ "`lastlogin` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`id`)"
				+ ")";
	}
	
	public static boolean createPlayersTable(Connection connection, String prefix) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(playersTable(prefix));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create players table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public static String totalPveStatsTable(String prefix) {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + "total_pve_stats`"
				+ "("
				+ "`player_id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`kills` INT(10) DEFAULT NULL,"
				+ "`bosskills` INT(10) DEFAULT NULL,"
				+ "`deaths` INT(10) DEFAULT NULL,"
				+ "`streak` INT(10) DEFAULT NULL,"
				+ "`maxStreak` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`player_id`)"
				+ ")";
	}

	public static boolean createTotalPveStatsTable(Connection connection, String prefix) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(totalPveStatsTable(prefix));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public static String totalPvpStatsTable(String prefix) {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + "total_pve_stats`"
				+ "("
				+ "`player_id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`kills` INT(10) DEFAULT NULL,"
				+ "`deaths` INT(10) DEFAULT NULL,"
				+ "`streak` INT(10) DEFAULT NULL,"
				+ "`maxStreak` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`player_id`)"
				+ ")";
	}

	public static boolean createTotalPvpStatsTable(Connection connection, String prefix) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(totalPvpStatsTable(prefix));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public static String eventPveStatsTable(String prefix, String eventName) {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + eventName + "_pve_stats`"
				+ "("
				+ "`player_id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`kills` INT(10) DEFAULT NULL,"
				+ "`bosskills` INT(10) DEFAULT NULL,"
				+ "`deaths` INT(10) DEFAULT NULL,"
				+ "`streak` INT(10) DEFAULT NULL,"
				+ "`maxStreak` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`player_id`)"
				+ ")";
	}

	public static boolean createEventPveStatsTable(Connection connection, String prefix, String eventName) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(eventPveStatsTable(prefix, eventName));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public static String eventPvpStatsTable(String prefix, String eventName) {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + eventName + "total_pve_stats`"
				+ "("
				+ "`player_id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`kills` INT(10) DEFAULT NULL,"
				+ "`deaths` INT(10) DEFAULT NULL,"
				+ "`streak` INT(10) DEFAULT NULL,"
				+ "`maxStreak` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`player_id`)"
				+ ")";
	}

	public static boolean createEventPvpStatsTable(Connection connection, String prefix, String eventName) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(eventPvpStatsTable(prefix, eventName));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
}
