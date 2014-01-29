package me.fuzzystatic.EventAdministrator.sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class SQLConnection {

    private final JavaPlugin plugin;
	private final DefaultConfigurationStructure dcs;
	
	private Connection connection;
	private String driver = "com.mysql.jdbc.Driver";

		
    public SQLConnection(JavaPlugin plugin) {
        if (!plugin.isInitialized())
            throw new IllegalArgumentException("plugin must be initiaized");
        this.plugin = plugin;
        this.dcs = new DefaultConfigurationStructure(plugin);
    }
    
	private String getMySQLURL() {
		return "jdbc:mysql://" + dcs.getMySQLHost() + ":" + dcs.getMySQLPort() + "/" + dcs.getMySQLDatabase();
	}
    
	public void connect()  {
		DefaultConfigurationStructure dcs = new DefaultConfigurationStructure(plugin);
		try {			
			connection = DriverManager.getConnection(getMySQLURL(), dcs.getMySQLUser(), dcs.getMySQLPassword());
			
			ConsoleLogs.sendMessage(getMySQLURL());
			ConsoleLogs.sendMessage(dcs.getMySQLUser());
			ConsoleLogs.sendMessage(dcs.getMySQLPassword());

			Statement st = null;
			ResultSet rs = null;

			st = connection.createStatement();

			rs = st.executeQuery("SELECT VERSION()");

			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not connect to database. Please check the config.yml.");
		}
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			ConsoleLogs.sendMessage("Error: Could not connect to database. Please check the config.yml.");
		}
	}

}
