package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLUpdatePlayer {
	
	protected final Connection connection;
	protected final String prefix;
	protected final String player;
	private String table = SQLSchema.TABLE_PLAYERS;
	
	private int playerID;
		
	public SQLUpdatePlayer(Connection connection, String prefix, String player) {
		this.connection = connection;
		this.prefix = prefix;
		this.player = player;
	}
	
	protected String checkQuery() {
		 return"SELECT id FROM "+ this.prefix + this.table
				 + " WHERE player = '" + this.player + "'";
	}
	
	protected int getPlayerID() {
		return playerID;
	}
	
    public boolean playerExists() {
    	try {
    		ResultSet resultSet = this.connection.prepareStatement(checkQuery()).executeQuery();
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
    	String checkQuery = "SELECT " + SQLSchema.COLUMN_PLAYER_ID + " FROM "+ this.prefix + table 
    			+ " WHERE player_id = " + getPlayerID();
    	try {
    		ResultSet resultSet = this.connection.prepareStatement(checkQuery).executeQuery();
        	if(resultSet.next()) {
        		return true;
        	}
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not find player data.");
			e.printStackTrace();
		}
		return false;    	
    }

    protected String updatePlayerData() {
		return "UPDATE " + this.prefix + this.table
				+ " SET lastlogin = " + System.currentTimeMillis()/1000
				+ " WHERE player = '" + player + "'";
	}
	
	protected String insertPlayerData() {
		return "INSERT INTO " + this.prefix + this.table + "(player, lastlogin)"
				+ " VALUES ('" + this.player + "', " + System.currentTimeMillis()/1000 + ")";
	}
    
    public boolean setPlayerData() {
		try {
			if(playerExists()) {	
				this.connection.prepareStatement(updatePlayerData()).executeUpdate();
			} else {
				this.connection.prepareStatement(insertPlayerData()).executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player data.");
			e.printStackTrace();
		}
		return false;
	}
    
}
