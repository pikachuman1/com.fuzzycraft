package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldConditions {
	
	private JavaPlugin plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public WorldConditions(JavaPlugin plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public void start() {
		int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				ecs.getPasteLocation().getWorld().setTime(ecs.getWorldConditionsTime());
			}
		}, 0, ecs.getWorldConditionsTimeCycleTime() * 20);
		SchedulerEventMap esm = new SchedulerEventMap();
		esm.set(id, eventName);
	}
}
