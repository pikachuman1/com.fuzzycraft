package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLSchema {
	
	public static String TABLE_PLAYERS = "players";
	public static String TABLE_PVE_STATS = "pve_stats";
	public static String TABLE_PVP_STATS = "pvp_stats";
	public static String TABLE_PVE_STATS_TOTAL = "pve_stats_total";
	public static String TABLE_PVP_STATS_TOTAL = "pvp_stats_total";
	
	private final Connection connection;
	private final String prefix;
	
	public SQLSchema(Connection connection, String prefix) {
		this.connection = connection;
		this.prefix = prefix;
	}
	
	public String playersTable() {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + TABLE_PLAYERS + "`"
				+ "("
				+ "`id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`player` VARCHAR(16) DEFAULT NULL UNIQUE,"
				+ "`lastlogin` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`id`)"
				+ ")";
	}
	
	public boolean createPlayersTable() {
		try {
			Statement statement = connection.createStatement();
			statement.execute(playersTable());
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create players table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String totalPveStatsTable() {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + TABLE_PVE_STATS_TOTAL + "`"
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

	public boolean createTotalPveStatsTable() {
		try {
			Statement statement = connection.createStatement();
			statement.execute(totalPveStatsTable());
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String totalPvpStatsTable() {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + TABLE_PVP_STATS_TOTAL + "`"
				+ "("
				+ "`player_id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`kills` INT(10) DEFAULT NULL,"
				+ "`deaths` INT(10) DEFAULT NULL,"
				+ "`streak` INT(10) DEFAULT NULL,"
				+ "`maxStreak` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`player_id`)"
				+ ")";
	}

	public boolean createTotalPvpStatsTable() {
		try {
			Statement statement = connection.createStatement();
			statement.execute(totalPvpStatsTable());
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String eventPveStatsTable(String eventName) {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + eventName + TABLE_PVE_STATS + "`"
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

	public boolean createEventPveStatsTable(String eventName) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(eventPveStatsTable(eventName));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String eventPvpStatsTable(String eventName) {
		return "CREATE TABLE IF NOT EXISTS `" + prefix + eventName + TABLE_PVP_STATS + "`"
				+ "("
				+ "`player_id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`kills` INT(10) DEFAULT NULL,"
				+ "`deaths` INT(10) DEFAULT NULL,"
				+ "`streak` INT(10) DEFAULT NULL,"
				+ "`maxStreak` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`player_id`)"
				+ ")";
	}

	public boolean createEventPvpStatsTable(String eventName) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(eventPvpStatsTable(eventName));
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
}
