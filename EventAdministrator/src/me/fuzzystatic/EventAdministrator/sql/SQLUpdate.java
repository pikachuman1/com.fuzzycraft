package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLUpdate {
	
	private final Connection connection;
	private final String prefix;
	private final String player;
	
	public SQLUpdate(Connection connection, String prefix, String player) {
		this.connection = connection;
		this.prefix = prefix;
		this.player = player;
	}
	
    public boolean playerExists() {
    	String checkQuery = "SELECT count(*) FROM "+ this.prefix + SQLSchema.TABLE_PLAYERS + " WHERE name '" + this.player + "'";
    	try {
    		PreparedStatement preparedStatement = this.connection.prepareStatement(checkQuery);
    		ResultSet resultSet = preparedStatement.executeQuery();
        	if(resultSet.next()) {	
        		return true;
        	}
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player data.");
			e.printStackTrace();
		}
		return false;    	
    }

	private String updatePlayerData() {
		return "UPDATE " + prefix + SQLSchema.TABLE_PLAYERS
				+ "SET lastlogin='" + System.currentTimeMillis()/1000 + "'"
				+ "WHERE name='" + player + "'";
	}
	
	private String insertPlayerData() {
		return "INSERT INTO " + this.prefix + SQLSchema.TABLE_PLAYERS + "(name, lastlogin)"
				+ "VALUES ('" + this.player + "', '" + System.currentTimeMillis()/1000 + "')";
	}
    
    public boolean setPlayerData() {
		try {
			if(playerExists()) {	
				PreparedStatement preparedStatement = this.connection.prepareStatement(updatePlayerData());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = this.connection.prepareStatement(insertPlayerData());
				preparedStatement.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player data.");
			e.printStackTrace();
		}
		return false;
	}
    
    private String updateTotalPveData() {
		return "UPDATE "
				+ prefix + SQLSchema.TABLE_PVE_STATS_TOTAL + " (name, lastlogin) "
				+ "VALUES ('" + player + "', '" + System.currentTimeMillis()/1000 + "')";
	}
    
    private String insertTotalPveData() {
		return "INSERT INTO " + prefix + SQLSchema.TABLE_PLAYERS + "(name, lastlogin)"
				+ "VALUES ('" + player + "', '" + System.currentTimeMillis()/1000 + "')";
	}
    
    public boolean totalPveData() {
    	String checkQuery1 = "SELECT count(*) FROM "+ prefix + SQLSchema.TABLE_PLAYERS + " WHERE name '" + player + "'";
		try {
			PreparedStatement ps1 = connection.prepareStatement(checkQuery1);
			ResultSet resultSet = ps1.executeQuery();
			if(resultSet.next()) {	
		    	String checkQuery2 = "SELECT count() FROM "+ prefix + SQLSchema.TABLE_PLAYERS + " WHERE name '" + player + "'";
				PreparedStatement ps2 = connection.prepareStatement(updatePlayerData());
				ps2.executeUpdate();
			} else {
				PreparedStatement ps2 = connection.prepareStatement(insertPlayerData());
				ps2.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player data.");
			e.printStackTrace();
		}
		return false;
	}
    
}
