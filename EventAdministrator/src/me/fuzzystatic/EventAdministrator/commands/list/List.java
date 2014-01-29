package me.fuzzystatic.EventAdministrator.commands.list;

import me.fuzzystatic.EventAdministrator.commands.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class List extends Command {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String[] args) {
		if(args.length > 1) {
			switch(args[1]) {
			case "e" 			: new ListEvent().runCommand(plugin, sender, args); break;
			case "event" 		: new ListEvent().runCommand(plugin, sender, args); break;
			case "events" 		: new ListEvent().runCommand(plugin, sender, args); break;
			case "s" 			: new ListSpawn().runCommand(plugin, sender, args); break;
			case "spawn" 		: new ListSpawn().runCommand(plugin, sender, args); break;
			case "spawns" 		: new ListSpawn().runCommand(plugin, sender, args); break;
			}
			return true;
		} else {
			sender.sendMessage(new ListEvent().usage());
			sender.sendMessage(new ListActiveEvent().usage());
			sender.sendMessage(new ListSpawn().usage());
			return true;
		}
	}
	
	
	public Permission permission() {
		Permission permission = new Permission("list");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	public String usage() {
		return super.usage() + " list";
	}
}
