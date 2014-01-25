package me.fuzzystatic.EventAdministrator.command;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.spawn.SpawnAmount;
import me.fuzzystatic.EventAdministrator.commands.spawn.SpawnCycleTime;
import me.fuzzystatic.EventAdministrator.commands.spawn.SpawnIsBoss;
import me.fuzzystatic.EventAdministrator.commands.spawn.SpawnLocation;
import me.fuzzystatic.EventAdministrator.commands.spawn.SpawnMob;
import me.fuzzystatic.EventAdministrator.commands.spawn.SpawnName;
import me.fuzzystatic.EventAdministrator.commands.spawn.SpawnStartTime;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class SpawnCommand extends Command {

	@Override
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
			case "start" 		: new SpawnStartTime().runCommand(plugin, sender, args); break;
			}
			return true;
		} else {
			sender.sendMessage(new SpawnName().usage());
			sender.sendMessage(new SpawnMob().usage());
			sender.sendMessage(new SpawnLocation().usage());
			sender.sendMessage(new SpawnAmount().usage());
			sender.sendMessage(new SpawnStartTime().usage());
			sender.sendMessage(new SpawnCycleTime().usage());
			sender.sendMessage(new SpawnIsBoss().usage());
			return true;
		}
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("spawn");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	public String usage() {
		return super.usage() + " s{pawn}";
	}
}
