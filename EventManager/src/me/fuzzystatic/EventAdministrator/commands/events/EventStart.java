package me.fuzzystatic.EventAdministrator.commands.events;

import me.fuzzystatic.EventAdministrator.EventManager;
import me.fuzzystatic.EventAdministrator.schedules.StartEvent;

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
			StartEvent startEvent = new StartEvent(plugin);
			startEvent.start();
			return true;
		}
		return false;
	}
}
