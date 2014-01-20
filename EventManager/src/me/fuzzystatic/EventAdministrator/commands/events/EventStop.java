package me.fuzzystatic.EventAdministrator.commands.events;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.schedules.StopEvent;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventStop implements CommandExecutor {

	private EventAdministrator plugin;
	
	public EventStop(EventAdministrator plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		final String eventName = EventName.getName();
		if (cmd.getName().equalsIgnoreCase("emStop")) {	
			StopEvent stopEvent = new StopEvent(plugin, eventName);
			if(stopEvent.stop()) {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "All events stopped.");
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Error stopping event.");	
			}
			if(stopEvent.clearEntities()) {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "Event cleared.");
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Paste location does not exist. Event not cleared.");	
			}
			return true;
		}
		return false;
	}
}
