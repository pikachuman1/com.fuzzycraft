package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.schedules.EventSchedulerMultimap;
import me.fuzzystatic.EventManager.schedules.PlayerItems;
import me.fuzzystatic.EventManager.schedules.Reminder;
import me.fuzzystatic.EventManager.schedules.Spawning;
import me.fuzzystatic.EventManager.schedules.StopEvent;
import me.fuzzystatic.EventManager.schedules.WorldConditions;
import me.fuzzystatic.EventManager.utilities.Regeneration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventStart implements CommandExecutor {

	private EventManager plugin;
	
	public EventStart(EventManager plugin) {
		this.plugin = plugin;
	}	
	
	public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStart")) {
			final String eventName = EventName.getName();
			EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, eventName);
			ecs.createFileStructure();
			int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
				public void run() {
					StopEvent stopEvent = new StopEvent(plugin, eventName);
					Regeneration regeneration = new Regeneration(plugin, eventName);
					Spawning spawning = new Spawning(plugin, eventName);
					WorldConditions worldConditions = new WorldConditions(plugin, eventName);
					Reminder reminder = new Reminder(plugin, eventName);
					PlayerItems playerItems = new PlayerItems(plugin, eventName);
					
					if(stopEvent.stop()) {
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "All events stopped.");
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Error stopping event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + ".");	
					}
					if(stopEvent.clearEntities()) {
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " cleared.");
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Paste location does not exist. Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " not cleared.");	
					}
					if(regeneration.regen()) {
						spawning.start();
						worldConditions.start();
						reminder.start();
						playerItems.start();
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " has started.");
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Paste location does not exist. Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " not started.");	
					}
				}
			}, 0, ecs.getCycleTime() * 20);
			EventSchedulerMultimap esm = new EventSchedulerMultimap();
			esm.set(eventName, id);
			
			return true;
		}
		return false;
	}
}
