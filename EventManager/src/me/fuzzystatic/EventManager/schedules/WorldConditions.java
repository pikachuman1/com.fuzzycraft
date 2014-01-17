package me.fuzzystatic.EventManager.schedules;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.commands.events.EventName;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;

public class WorldConditions {
	
	private EventManager plugin;
	private final EventConfigurationStructure ecs;
	
	public WorldConditions(EventManager plugin) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(plugin, EventName.getName());
	}
	
	public void start() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				ecs.getPasteLocation().getWorld().setTime(ecs.getWorldConditionsTime());
			}
		}, 0, ecs.getWorldConditionsTimeCycleTime() * 20);
	}
}
