package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				+ " WHERE player_id = " + super.getPlayerID() + "";
	}
    
    private String insertPlayerData(String column) {
		return "INSERT INTO " + prefix + this.table + "(player_id, " + column + ")"
				+ " VALUES (" + super.getPlayerID() + ", 1)";
	}
    
    public boolean setTotalData(String column) {
    	try {
			if(super.playerExists()) {	
				if (super.playerIDExists(this.table)) {
					PreparedStatement preparedStatement = this.connection.prepareStatement(updateTotalData(column));
					preparedStatement.executeUpdate();
				} else {
					PreparedStatement preparedStatement = this.connection.prepareStatement(insertPlayerData(column));
					preparedStatement.executeUpdate();
				}
			} else {
				PreparedStatement preparedStatement1 = this.connection.prepareStatement(super.insertPlayerData());
				preparedStatement1.executeUpdate();
				PreparedStatement preparedStatement2 = this.connection.prepareStatement(insertPlayerData(column));
				preparedStatement2.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not insert / update player total.");
			e.printStackTrace();
		}
		return false;
	}
}
