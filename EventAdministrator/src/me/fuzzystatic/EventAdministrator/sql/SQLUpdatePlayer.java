package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLUpdatePlayer {
	
	protected final Connection connection;
	protected final String prefix;
	protected final String player;
	
	private int playerID;

		
	public SQLUpdatePlayer(Connection connection, String prefix, String player) {
		this.connection = connection;
		this.prefix = prefix;
		this.player = player;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
    public boolean playerExists() {
    	String checkQuery = "SELECT id, name FROM "+ this.prefix + SQLSchema.TABLE_PLAYERS + " WHERE name '" + this.player + "'";
    	try {
    		PreparedStatement preparedStatement = this.connection.prepareStatement(checkQuery);
    		ResultSet resultSet = preparedStatement.executeQuery();
        	if(resultSet.next()) {
        		this.playerID = resultSet.getInt("id");
        		return true;
        	}
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not find player data.");
			e.printStackTrace();
		}
		return false;    	
    }
    
    public boolean playerIDExists(String table) {
    	String checkQuery = "SELECT count(*) FROM "+ this.prefix + table + " WHERE player_id '" + getPlayerID() + "'";
    	try {
    		PreparedStatement preparedStatement = this.connection.prepareStatement(checkQuery);
    		ResultSet resultSet = preparedStatement.executeQuery();
        	if(resultSet.next()) {
        		return true;
        	}
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not find player data.");
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
    
}
