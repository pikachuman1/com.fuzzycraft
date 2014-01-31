package me.fuzzystatic.EventAdministrator.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLUpdateKills extends SQLUpdatePlayer{
	
	public SQLUpdateKills(Connection connection, String prefix, String player) {
		super(connection, player, player);
	}

	private String updatePveTotalData() {
		return "UPDATE "
				+ prefix + SQLSchema.TABLE_PVE_STATS_TOTAL + " (name, lastlogin) "
				+ "VALUES ('" + player + "', '" + System.currentTimeMillis()/1000 + "')";
	}
    
    private String insertTotalData(String table) {
		return "INSERT INTO " + prefix + table + "(player_id)"
				+ "VALUES ('" + super.getPlayerID() + "')";
	}
    
    public boolean pveTotalData() {
    	String table = SQLSchema.TABLE_PVE_STATS_TOTAL;
    	try {
			if(playerExists()) {	
				if (playerIDExists(table)) {
					PreparedStatement preparedStatement = this.connection.prepareStatement(insertTotalData(table));
					preparedStatement.executeUpdate();
				}
			} else {
				PreparedStatement preparedStatement = this.connection.prepareStatement(insertTotalData(table));
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
