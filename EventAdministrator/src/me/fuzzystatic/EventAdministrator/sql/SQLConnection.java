package me.fuzzystatic.EventAdministrator.sql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;

public class SQLConnection {

    private final JavaPlugin plugin;
	private final DefaultConfigurationStructure dcs;
	
	private static Connection connection;
	
    public SQLConnection(JavaPlugin plugin) {
        if (!plugin.isInitialized())
            throw new IllegalArgumentException("plugin must be initiaized");
        this.plugin = plugin;
        this.dcs = new DefaultConfigurationStructure(plugin);
    }
    
	private void connect()  {
		DefaultConfigurationStructure dcs = new DefaultConfigurationStructure(plugin);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			connection = DriverManager.getConnection(getMySQLURL(), dcs.getMySQLUser(), dcs.getMySQLPassword());
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String getMySQLURL() {
		return "jdbc:mysql://" + dcs.getMySQLHost() + ":" + dcs.getMySQLPort() + "/";
	}
}
