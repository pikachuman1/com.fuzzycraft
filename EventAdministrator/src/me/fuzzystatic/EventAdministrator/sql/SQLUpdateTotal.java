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

	private String updateTotalData(String column, String amount) {
		return "UPDATE " + prefix + this.table
				+ " SET " + column + "=" + amount
				+ " WHERE " + SQLSchema.COLUMN_PLAYER_ID + " = " + super.getPlayerID() + "";
	}
    
    private String insertPlayerData(String column, String amount) {
		return "INSERT INTO " + prefix + this.table + "(" + SQLSchema.COLUMN_PLAYER_ID + ", " + column + ")"
				+ " VALUES (" + super.getPlayerID() + ", " + amount + ")";
	}
    
    public boolean setTotalData(String column, String amount, boolean update) {
    	if (update) {
    		amount = column + "+" + amount;
    	}
    	try {
			if(super.playerExists()) {	
				if (super.playerIDExists(this.table)) {
					this.connection.prepareStatement(updateTotalData(column, amount)).executeUpdate();
				} else {
					this.connection.prepareStatement(insertPlayerData(column, amount)).executeUpdate();
				}
			} else {
				this.connection.prepareStatement(super.insertPlayerData()).executeUpdate();
				this.connection.prepareStatement(insertPlayerData(column, amount)).executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player total.");
			e.printStackTrace();
		}
		return false;
	}
}
