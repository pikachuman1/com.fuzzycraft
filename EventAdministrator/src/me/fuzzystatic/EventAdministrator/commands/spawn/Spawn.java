package me.fuzzystatic.EventAdministrator.commands.spawn;

import me.fuzzystatic.EventAdministrator.commands.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class Spawn extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String[] args) {
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
