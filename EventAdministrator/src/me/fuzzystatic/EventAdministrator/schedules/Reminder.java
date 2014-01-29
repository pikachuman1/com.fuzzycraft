package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Reminder {

	private JavaPlugin plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public Reminder(JavaPlugin plugin, String eventName) {
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
			SchedulerEventMap esm = new SchedulerEventMap();
			esm.set(id, eventName);
		}
	}
}
