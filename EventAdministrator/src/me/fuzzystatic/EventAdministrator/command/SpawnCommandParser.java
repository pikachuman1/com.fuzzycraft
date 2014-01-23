package me.fuzzystatic.EventAdministrator.command;

import org.bukkit.command.CommandSender;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.event.spawn.SpawnAmount;
import me.fuzzystatic.EventAdministrator.commands.event.spawn.SpawnCycleTime;
import me.fuzzystatic.EventAdministrator.commands.event.spawn.SpawnIsBoss;
import me.fuzzystatic.EventAdministrator.commands.event.spawn.SpawnLocation;
import me.fuzzystatic.EventAdministrator.commands.event.spawn.SpawnMob;
import me.fuzzystatic.EventAdministrator.commands.event.spawn.SpawnName;

public class SpawnCommandParser extends Command {

	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String[] args) {
		if(args.length > 1) {
			switch(args[1]) {
				case "amount" 		: new SpawnAmount().runCommand(plugin, sender, args); break;
				case "cycle" 		: new SpawnCycleTime().runCommand(plugin, sender, args); break;
				case "isboss" 		: new SpawnIsBoss().runCommand(plugin, sender, args); break;
				case "loc" 			: new SpawnLocation().runCommand(plugin, sender, args); break;
				case "location" 	: new SpawnLocation().runCommand(plugin, sender, args); break;
				case "mob" 			: new SpawnMob().runCommand(plugin, sender, args); break;
				case "name" 		: new SpawnName().runCommand(plugin, sender, args); break;
			}
			return true;
		}
	return false;
	}

}
