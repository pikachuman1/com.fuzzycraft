package me.fuzzystatic.EventAdministrator.sql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.configuration.structure.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLConnection {

	private final DefaultConfigurationStructure dcs;
	private Connection connection;

    public SQLConnection(JavaPlugin plugin) {
		if (!plugin.isInitialized()) throw new IllegalArgumentException("Plugin must be initialized!");
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
	
	public static void disconnect(Connection connection) {
		try {			
			connection.close();
    	} catch (SQLException e) {
    		ConsoleLogs.sendMessage("Error: Could not disconnect from database. Connection lost.");
    	}
	}
}
