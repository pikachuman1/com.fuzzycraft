package me.fuzzystatic.EventAdministrator.sql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLConnection {

	private final DefaultConfigurationStructure dcs;
	private Connection connection;

    public SQLConnection(JavaPlugin plugin) {
        if (!plugin.isInitialized())
            throw new IllegalArgumentException("plugin must be initiaized");
        this.dcs = new DefaultConfigurationStructure(plugin);
		try {	
			connection =  DriverManager.getConnection("jdbc:mysql://" + dcs.getMySQLHost() + ":" + dcs.getMySQLPort() + "/" + dcs.getMySQLDatabase(), dcs.getMySQLUser(), dcs.getMySQLPassword());
		} catch (SQLException e) {
    		ConsoleLogs.sendMessage("Error: Could not connect to database. Please check the config.yml.");
    	}
    }
	
	public Connection getConnection() {
		return connection;
	}
	
	public void disconnect(Connection connection) {
		try {			
			connection.close();
    	} catch (SQLException e) {
    		ConsoleLogs.sendMessage("Error: Could not disconnect from database. Connection lost.");
    	}
	}
	
	public String playersTable() {
		return "CREATE TABLE IF NOT EXISTS `" + dcs.getMySQLPrefix() + "players`"
				+ "("
				+ "`id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`player` VARCHAR(16) DEFAULT NULL UNIQUE,"
				+ "`lastlogin` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`id`)"
				+ ")";
	}
	
	public boolean createPlayersTable(Connection connection) {
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
	
	public String statsTable() {
		return "CREATE TABLE IF NOT EXISTS `" + dcs.getMySQLPrefix() + "stats`"
				+ "("
				+ "`player_id` INT(10) NOT NULL AUTO_INCREMENT UNIQUE,"
				+ "`kills` INT(10) DEFAULT NULL,"
				+ "`bosskills` INT(10) DEFAULT NULL,"
				+ "`deaths` INT(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`player_id`)"
				+ ")";
	}

	public boolean createStatsTable(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(statsTable());
			return true;
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not create stats table.");
			e.printStackTrace();
		}
		return false;
	}
}
