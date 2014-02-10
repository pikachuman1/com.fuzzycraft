package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.SQLException;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLSchema {
	
	public static final String TABLE_PLAYERS 			= "players";
	public static final String TABLE_PVE_STATS 			= "pve_stats";
	public static final String TABLE_PVP_STATS 			= "pvp_stats";
	public static final String TABLE_PVE_STATS_TOTAL 	= "pve_stats_total";
	public static final String TABLE_PVP_STATS_TOTAL 	= "pvp_stats_total";
	
	public static final String COLUMN_ID 				= "id";
	public static final String COLUMN_PLAYER 			= "player";
	public static final String COLUMN_LASTLOGIN 		= "lastlogin";
	
	public static final String COLUMN_PLAYER_ID 		= "player_id";
	public static final String COLUMN_KILLS 			= "kills";
	public static final String COLUMN_BOSSKILLS 		= "bosskills";
	public static final String COLUMN_DEATHS 			= "deaths";
	public static final String COLUMN_STREAK 			= "streak";
	public static final String COLUMN_MAXSTREAK 		= "maxstreak";
	
	private final Connection connection;
	private final String prefix;
	
	public SQLSchema(Connection connection, String prefix) {
		this.connection = connection;
		this.prefix = prefix;
	}
	
	public String playersTable() {
		return "CREATE TABLE IF NOT EXISTS `" + this.prefix + TABLE_PLAYERS + "`"
				+ "("
				+ "`" + COLUMN_ID + "` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`" + COLUMN_PLAYER + "` VARCHAR(16) DEFAULT NULL UNIQUE,"
				+ "`" + COLUMN_LASTLOGIN + "` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`" + COLUMN_ID + "`)"
				+ ")";
	}
	
	public boolean createPlayersTable() {
		try {
			if (connection.createStatement().execute(playersTable())) return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create players table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String totalPveStatsTable() {
		return "CREATE TABLE IF NOT EXISTS `" + this.prefix + TABLE_PVE_STATS_TOTAL + "`"
				+ "("
				+ "`" + COLUMN_PLAYER_ID + "` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`" + COLUMN_KILLS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_BOSSKILLS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_DEATHS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_STREAK + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_MAXSTREAK + "` INT(10) DEFAULT 0,"
				+ "PRIMARY KEY (`" + COLUMN_PLAYER_ID + "`)"
				+ ")";
	}

	public boolean createTotalPveStatsTable() {
		try {
			if (connection.createStatement().execute(totalPveStatsTable())) return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String totalPvpStatsTable() {
		return "CREATE TABLE IF NOT EXISTS `" + this.prefix + TABLE_PVP_STATS_TOTAL + "`"
				+ "("
				+ "`" + COLUMN_PLAYER_ID + "` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`" + COLUMN_KILLS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_DEATHS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_STREAK + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_MAXSTREAK + "` INT(10) DEFAULT 0,"
				+ "PRIMARY KEY (`" + COLUMN_PLAYER_ID + "`)"
				+ ")";
	}

	public boolean createTotalPvpStatsTable() {
		try {
			if (connection.createStatement().execute(totalPvpStatsTable())) return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String eventPveStatsTable(String eventName) {
		return "CREATE TABLE IF NOT EXISTS `" + this.prefix + TABLE_PVE_STATS + "_" + eventName + "`"
				+ "("
				+ "`" + COLUMN_PLAYER_ID + "` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`" + COLUMN_KILLS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_BOSSKILLS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_DEATHS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_STREAK + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_MAXSTREAK + "` INT(10) DEFAULT 0,"
				+ "PRIMARY KEY (`" + COLUMN_PLAYER_ID + "`)"
				+ ")";
	}

	public boolean createEventPveStatsTable(String eventName) {
		try {
			if (connection.createStatement().execute(eventPveStatsTable(eventName))) return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table for event " + eventName + ".");
			e.printStackTrace();
		}
		return false;
	}
	
	public String eventPvpStatsTable(String eventName) {
		return "CREATE TABLE IF NOT EXISTS `" + this.prefix + TABLE_PVP_STATS + "_" + eventName + "`"
				+ "("
				+ "`" + COLUMN_PLAYER_ID + "` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`" + COLUMN_KILLS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_DEATHS + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_STREAK + "` INT(10) DEFAULT 0,"
				+ "`" + COLUMN_MAXSTREAK + "` INT(10) DEFAULT 0,"
				+ "PRIMARY KEY (`" + COLUMN_PLAYER_ID + "`)"
				+ ")";
	}

	public boolean createEventPvpStatsTable(String eventName) {
		try {
			if (connection.createStatement().execute(eventPvpStatsTable(eventName))) return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table for event " + eventName + ".");
			e.printStackTrace();
		}
		return false;
	}
}
