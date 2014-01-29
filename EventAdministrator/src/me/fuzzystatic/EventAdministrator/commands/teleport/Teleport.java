package me.fuzzystatic.EventAdministrator.commands.teleport;

import me.fuzzystatic.EventAdministrator.commands.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class Teleport extends Command {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String[] args) {
		if(args.length > 1) {
			switch(args[1]) {
			case "en" 			: new TeleportEntrance().runCommand(plugin, sender, args); break;
			case "entrance" 	: new TeleportEntrance().runCommand(plugin, sender, args); break;
			case "ex" 			: new TeleportExit().runCommand(plugin, sender, args); break;
			case "exit" 		: new TeleportExit().runCommand(plugin, sender, args); break;
			}
			return true;
		} else {
			sender.sendMessage(new TeleportEntrance().usage());
			sender.sendMessage(new TeleportExit().usage());
			return true;
		}
	}
	
	
	public Permission permission() {
		Permission permission = new Permission("teleport");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	public String usage() {
		return super.usage() + " t{ele}p{ort}";
	}
}
