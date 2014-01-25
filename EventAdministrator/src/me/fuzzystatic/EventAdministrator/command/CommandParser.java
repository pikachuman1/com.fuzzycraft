package me.fuzzystatic.EventAdministrator.command;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.event.EventCycleTime;
import me.fuzzystatic.EventAdministrator.commands.event.EventLoad;
import me.fuzzystatic.EventAdministrator.commands.event.EventLocations;
import me.fuzzystatic.EventAdministrator.commands.event.EventName;
import me.fuzzystatic.EventAdministrator.commands.event.EventStart;

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
					case "cycle" 		: new EventCycleTime().runCommand(plugin, sender, args); break;
					case "load" 		: new EventLoad().runCommand(plugin, sender, args); break;
					case "loc" 			: new EventLocations().runCommand(plugin, sender, args); break;
					case "location" 	: new EventLocations().runCommand(plugin, sender, args); break;
					case "name" 		: new EventName().runCommand(plugin, sender, args); break;
					case "start" 		: new EventStart().runCommand(plugin, sender, args); break;
					
					case "s" 			: new SpawnCommandParser().runCommand(plugin, sender, args); break;
					case "spawn" 		: new SpawnCommandParser().runCommand(plugin, sender, args); break;
					
					case "l" 			: new ListCommandParser().runCommand(plugin, sender, args); break;
					case "list" 		: new ListCommandParser().runCommand(plugin, sender, args); break;
				}
				return true;
			}
		}
		return false;
	}
}
