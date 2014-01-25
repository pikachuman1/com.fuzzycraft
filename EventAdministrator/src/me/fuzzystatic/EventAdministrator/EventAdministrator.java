package me.fuzzystatic.EventAdministrator;

import java.io.File;

import me.fuzzystatic.EventAdministrator.command.CommandParser;
import me.fuzzystatic.EventAdministrator.configurations.DirectoryStructure;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.listeners.BossDeathListener;
import me.fuzzystatic.EventAdministrator.schedules.StartEvent;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;
import net.minecraft.util.org.apache.commons.io.FilenameUtils;

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
		
		// Initialize commands
		getCommand("ea").setExecutor(new CommandParser(plugin));
		
		// Auto-start any applicable events.
		DirectoryStructure ds = new DirectoryStructure(plugin);
		for (File file : ds.eventFiles()) {
			String eventName = FilenameUtils.removeExtension(file.getName());
			EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
			if(ecs.getAutoStart()) {
				StartEvent startEvent = new StartEvent(plugin);
				startEvent.start(eventName);
			}
		}
	}
}