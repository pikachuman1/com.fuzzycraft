package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.SQLException;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLUpdateTotal extends SQLUpdatePlayer{
		
	private String table;
	
	public SQLUpdateTotal(Connection connection, String prefix, String player, String table) {
		super(connection, prefix, player);
		this.table = table;
	}

	private String updateTotalData(String column) {
		return "UPDATE " + prefix + this.table
				+ " SET " + column + "=" + column + " + 1"
				+ " WHERE " + SQLSchema.COLUMN_PLAYER_ID + " = " + super.getPlayerID() + "";
	}
    
    private String insertPlayerData(String column) {
		return "INSERT INTO " + prefix + this.table + "(" + SQLSchema.COLUMN_PLAYER_ID + ", " + column + ")"
				+ " VALUES (" + super.getPlayerID() + ", 1)";
	}
    
    public boolean setTotalData(String column) {
    	try {
			if(super.playerExists()) {	
				if (super.playerIDExists(this.table)) {
					this.connection.prepareStatement(updateTotalData(column)).executeUpdate();
				} else {
					this.connection.prepareStatement(insertPlayerData(column)).executeUpdate();
				}
			} else {
				this.connection.prepareStatement(super.insertPlayerData()).executeUpdate();
				this.connection.prepareStatement(insertPlayerData(column)).executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player total.");
			e.printStackTrace();
		}
		return false;
	}
}
