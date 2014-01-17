package me.fuzzystatic.EventManager.schedules;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.commands.events.EventName;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.entities.EventEntities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Reminder {

	private EventManager plugin;
	private final EventConfigurationStructure ecs;
	
	public Reminder(EventManager plugin) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(plugin, EventName.getName());
	}
	
	public void start() {
		if (ecs.getReminderCycleTime() >= 0) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
				   	EventEntities eventEntities = new EventEntities(ecs.getPasteLocation().getWorld());
					if(eventEntities.bossAlive()) Bukkit.getServer().broadcastMessage(ChatColor.GREEN + ecs.getReminderMessage());
				}
			}, ecs.getReminderCycleTime() * 20, ecs.getReminderCycleTime() * 20);
		}
	}
}
