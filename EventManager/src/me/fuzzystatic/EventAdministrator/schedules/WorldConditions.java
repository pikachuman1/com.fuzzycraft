package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;

public class WorldConditions {
	
	private EventAdministrator plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public WorldConditions(EventAdministrator plugin, String eventName) {
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
		EventSchedulerMap esm = new EventSchedulerMap();
		esm.set(id, eventName);
	}
}
