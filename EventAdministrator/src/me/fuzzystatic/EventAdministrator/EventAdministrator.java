package me.fuzzystatic.EventAdministrator;

import java.io.File;

import me.fuzzystatic.EventAdministrator.commands.CommandParser;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.DirectoryStructure;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.listeners.BossDeathListener;
import me.fuzzystatic.EventAdministrator.listeners.StatsListener;
import me.fuzzystatic.EventAdministrator.schedules.StartEvent;
import me.fuzzystatic.EventAdministrator.sql.SQLConnection;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;
import net.minecraft.util.org.apache.commons.io.FilenameUtils;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventAdministrator extends JavaPlugin {
			
	private SQLConnection sc = new SQLConnection(this);	
	private DefaultConfigurationStructure dcs = new DefaultConfigurationStructure(this);	
	private DirectoryStructure ds = new DirectoryStructure(this);
	
	// Listeners
	private BossDeathListener bdl = new BossDeathListener(this);
	private StatsListener sl = new StatsListener(this, sc.getConnection());
			
	public void onEnable() {
		// Check for dependancies
		PluginManager pm = getServer().getPluginManager();
		if(!pm.isPluginEnabled("WorldEdit")) ConsoleLogs.sendMessage("EventManager requires the WorldEdit plugin");
		if(!pm.isPluginEnabled("WorldGuard")) ConsoleLogs.sendMessage("EventManager requires the WorldGuard plugin");
		
		// Register listeners
		pm.registerEvents(this.bdl, this);
		pm.registerEvents(this.sl, this);

		// Create directory structure
		getDataFolder().mkdir();
		ConsoleLogs.sendMessage(getDataFolder().getParent());
		dcs.createFileStructure();
		ds.createEventDirectory();
		ds.createSchematicDirectory();
		
		// Create database structure
		sc.createPlayersTable(sc.getConnection());
		sc.createStatsTable(sc.getConnection());

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
		sc.disconnect(sc.getConnection());
	}
}