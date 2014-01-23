package me.fuzzystatic.EventAdministrator;

import me.fuzzystatic.EventAdministrator.command.CommandParser;
import me.fuzzystatic.EventAdministrator.commands.event.*;
import me.fuzzystatic.EventAdministrator.commands.event.spawn.*;
import me.fuzzystatic.EventAdministrator.configurations.DirectoryStructure;
import me.fuzzystatic.EventAdministrator.listeners.BossDeathListener;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventAdministrator extends JavaPlugin {
		
	private BossDeathListener bdl = new BossDeathListener(this);
	
	public FileConfiguration config;
	
	private EventAdministrator plugin = this;
		
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		if(!pm.isPluginEnabled("WorldEdit")) {
			ConsoleLogs.sendMessage("EventManager requires the WorldEdit plugin");
		}
		pm.registerEvents(this.bdl, this);
		
		// Create directory structure
		getDataFolder().mkdir();
		DirectoryStructure.createEventDirectory(getDataFolder());
		DirectoryStructure.createSchematicDirectory(getDataFolder());
		
		getCommand("ea").setExecutor(new CommandParser(plugin));
		getCommand("emeventlist").setExecutor(new EventList(plugin));
		getCommand("emspawnlist").setExecutor(new SpawnList(plugin));
	}
}