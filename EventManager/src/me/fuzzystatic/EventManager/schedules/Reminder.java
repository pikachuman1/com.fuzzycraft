package me.fuzzystatic.EventManager.schedules;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Reminder {

	private EventManager plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public Reminder(EventManager plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public void start() {
		if (ecs.getReminderCycleTime() >= 0) {
			int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().broadcastMessage(ChatColor.GREEN + ecs.getReminderMessage());
				}
			}, ecs.getReminderCycleTime() * 20, ecs.getReminderCycleTime() * 20);
			EventSchedulerMultimap esm = new EventSchedulerMultimap();
			esm.set(eventName, id);
		}
	}
}
