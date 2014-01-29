package me.fuzzystatic.EventAdministrator.commands;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.event.EventAutoStart;
import me.fuzzystatic.EventAdministrator.commands.event.EventClear;
import me.fuzzystatic.EventAdministrator.commands.event.EventCycleTime;
import me.fuzzystatic.EventAdministrator.commands.event.EventLoad;
import me.fuzzystatic.EventAdministrator.commands.event.EventLocations;
import me.fuzzystatic.EventAdministrator.commands.event.EventName;
import me.fuzzystatic.EventAdministrator.commands.event.EventSave;
import me.fuzzystatic.EventAdministrator.commands.event.EventStart;
import me.fuzzystatic.EventAdministrator.commands.event.EventStop;
import me.fuzzystatic.EventAdministrator.commands.event.EventReminder;
import me.fuzzystatic.EventAdministrator.commands.list.List;
import me.fuzzystatic.EventAdministrator.commands.spawn.Spawn;
import me.fuzzystatic.EventAdministrator.commands.teleport.Teleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandParser implements CommandExecutor {
	
	private EventAdministrator plugin;
	
	public CommandParser(EventAdministrator plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("ea")) {
			if(args.length > 0) {
				switch(args[0]) {
				case "as" 			: new EventAutoStart().runCommand(plugin, sender, args); break;
				case "autostart" 	: new EventAutoStart().runCommand(plugin, sender, args); break;
				case "clear" 		: new EventClear().runCommand(plugin, sender, args); break;
				case "cycle" 		: new EventCycleTime().runCommand(plugin, sender, args); break;
				case "load" 		: new EventLoad().runCommand(plugin, sender, args); break;
				case "loc" 			: new EventLocations().runCommand(plugin, sender, args); break;
				case "location" 	: new EventLocations().runCommand(plugin, sender, args); break;
				case "name" 		: new EventName().runCommand(plugin, sender, args); break;
				case "save" 		: new EventSave().runCommand(plugin, sender, args); break;
				case "start" 		: new EventStart().runCommand(plugin, sender, args); break;
				case "stop" 		: new EventStop().runCommand(plugin, sender, args); break;
				
				case "rem" 			: new EventReminder().runCommand(plugin, sender, args); break;
				case "reminder" 	: new EventReminder().runCommand(plugin, sender, args); break;

				case "s" 			: new Spawn().runCommand(plugin, sender, args); break;
				case "spawn" 		: new Spawn().runCommand(plugin, sender, args); break;

				case "tp" 			: new Teleport().runCommand(plugin, sender, args); break;
				case "teleport" 	: new Teleport().runCommand(plugin, sender, args); break;
				
				case "l" 			: new List().runCommand(plugin, sender, args); break;
				case "list" 		: new List().runCommand(plugin, sender, args); break;
				}
				return true;
			} else {
				sender.sendMessage(new EventName().usage());
				sender.sendMessage(new EventSave().usage());
				sender.sendMessage(new EventLocations().usage());
				sender.sendMessage(new EventCycleTime().usage());
				sender.sendMessage(new EventStart().usage());
				sender.sendMessage(new EventAutoStart().usage());
				sender.sendMessage(new EventStop().usage());
				sender.sendMessage(new EventClear().usage());
				sender.sendMessage(new EventLoad().usage());
				sender.sendMessage(new EventReminder().usage());
				sender.sendMessage(new Spawn().usage());
				sender.sendMessage(new List().usage());
				return false;
			}
		}
		return false;
	}
}
