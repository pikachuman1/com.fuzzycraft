package me.fuzzystatic.EventAdministrator.command;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.events.CycleTime;
import me.fuzzystatic.EventAdministrator.commands.events.Load;
import me.fuzzystatic.EventAdministrator.commands.events.Locations;
import me.fuzzystatic.EventAdministrator.commands.events.Start;

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
					case "cycle" 		: new CycleTime().runCommand(plugin, sender, args); break;
					case "load" 		: new Load().runCommand(plugin, sender, args); break;
					case "loc" 			: new Locations().runCommand(plugin, sender, args); break;
					case "locations" 	: new Locations().runCommand(plugin, sender, args); break;
					case "start" 		: new Start().runCommand(plugin, sender, args); break;
				}
				return true;
			}
		}
		return false;
	}
}
