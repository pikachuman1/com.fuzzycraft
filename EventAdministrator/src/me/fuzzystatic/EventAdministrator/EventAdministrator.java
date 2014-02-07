package me.fuzzystatic.EventAdministrator;

import java.io.File;
import java.sql.Connection;

import me.fuzzystatic.EventAdministrator.commands.CommandParser;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.DirectoryStructure;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.listeners.BossDeathListener;
import me.fuzzystatic.EventAdministrator.listeners.StatsListener;
import me.fuzzystatic.EventAdministrator.schedules.StartEvent;
import me.fuzzystatic.EventAdministrator.schedules.StopEvent;
import me.fuzzystatic.EventAdministrator.sql.SQLConnection;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;
import net.minecraft.util.org.apache.commons.io.FilenameUtils;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author FuzzyStatic
 *
 */
public class EventAdministrator extends JavaPlugin {

	private static Connection connection;
			
	public void onEnable() {		
		SQLConnection sc = new SQLConnection(this);
		DefaultConfigurationStructure dcs = new DefaultConfigurationStructure(this);	
		DirectoryStructure ds = new DirectoryStructure(this);
		
		// Listeners
		BossDeathListener bdl = new BossDeathListener(this);
		
		// Check for dependencies
		PluginManager pm = getServer().getPluginManager();
		if(!pm.isPluginEnabled("WorldEdit")) ConsoleLogs.sendMessage(getName() + "EventManager requires the WorldEdit plugin");
		if(!pm.isPluginEnabled("WorldGuard")) ConsoleLogs.sendMessage(getName() + "EventManager requires the WorldGuard plugin");
		
		// Register listeners
		pm.registerEvents(bdl, this);

		// Create directory structure
		getDataFolder().mkdir();
		dcs.createFileStructure();
		ds.createEventDirectory();
		ds.createSchematicDirectory();
		
		// Create database structure and register data listener
		connection = sc.getConnection();
		if(connection != null) {
			SQLSchema ss = new SQLSchema(connection, dcs.getMySQLPrefix());
			if (ss.createPlayersTable()) ConsoleLogs.sendMessage(getName() + "Players table created");
			if (ss.createTotalPveStatsTable()) ConsoleLogs.sendMessage(getName() + "Total PvE stats table created");
			if (ss.createTotalPvpStatsTable()) ConsoleLogs.sendMessage(getName() + "Total PvP stats table created");
		
			StatsListener sl = new StatsListener(this);
			pm.registerEvents(sl, this);
		}
		
		// Initialize commands
		getCommand("ea").setExecutor(new CommandParser(this));
		
		// Auto-start any applicable events.
		for (File file : ds.eventFiles()) {
			String eventName = FilenameUtils.removeExtension(file.getName());
			EventConfigurationStructure ecs = new EventConfigurationStructure(this, eventName);	
			if(ecs.getAutoStart()) {
				StartEvent startEvent = new StartEvent(this, eventName);
				startEvent.start();
			}
		}
	}
	
	public void onDisable() {
		DirectoryStructure ds = new DirectoryStructure(this);
		
		if(connection != null) {
			SQLConnection.disconnect(connection);
		}
		
		for (File file : ds.eventFiles()) {
			String eventName = FilenameUtils.removeExtension(file.getName());
			StopEvent stopEvent = new StopEvent(this, eventName);
			stopEvent.stop();
			stopEvent.clearEntities();
		}
		
		getServer().getScheduler().cancelTasks(this);
	}
	
	public static Connection getConnection() {
		return connection;
	}
}