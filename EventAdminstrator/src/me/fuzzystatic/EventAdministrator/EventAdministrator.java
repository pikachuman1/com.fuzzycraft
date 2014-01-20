package me.fuzzystatic.EventAdministrator;

import me.fuzzystatic.EventAdministrator.commands.events.*;
import me.fuzzystatic.EventAdministrator.commands.events.spawns.*;
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
			ConsoleLogs.message("EventManager requires the WorldEdit plugin");
		}
		pm.registerEvents(this.bdl, this);
		
		// Create directory structure
		getDataFolder().mkdir();
		DirectoryStructure.createEventDirectory(getDataFolder());
		DirectoryStructure.createSchematicDirectory(getDataFolder());
		
		getCommand("emsetcycle").setExecutor(new EventCycleTime(plugin));
		getCommand("emgetcycle").setExecutor(new EventCycleTime(plugin));
		getCommand("emload").setExecutor(new EventLoad(plugin));
		getCommand("emname").setExecutor(new EventName(plugin));
		getCommand("emsetentrance").setExecutor(new EventLocations(plugin));
		getCommand("emsetexit").setExecutor(new EventLocations(plugin));
		getCommand("emsave").setExecutor(new EventSave(plugin));
		getCommand("emstart").setExecutor(new EventStart(plugin));
		getCommand("emstop").setExecutor(new EventStop(plugin));
		
		getCommand("emspawnamount").setExecutor(new SpawnAmount(plugin));
		getCommand("emspawncycle").setExecutor(new SpawnCycleTime(plugin));
		getCommand("emspawnisboss").setExecutor(new SpawnIsBoss(plugin));
		getCommand("emspawnlocation").setExecutor(new SpawnLocation(plugin));
		getCommand("emspawnmob").setExecutor(new SpawnMob(plugin));
		getCommand("emspawnname").setExecutor(new SpawnName(plugin));
		getCommand("emspawnstart").setExecutor(new SpawnStartTime(plugin));
		
		/*long ticks = plugin.config.getLong(Strings.DC_EVENT_CYCLE.toString()) * 20;	
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				EventStop eventStop = new EventStop(plugin);
				eventStop.stopEvent();
				EventStart eventStart = new EventStart(plugin);
				eventStart.startEvent();
				eventStart.startSpawns();
				eventStart.items(120);
				eventStart.eventTimeMidnight(60);
				eventStart.reminder(300);
			}
		}, 0, ticks);*/
	}
}