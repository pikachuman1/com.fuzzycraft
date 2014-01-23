package me.fuzzystatic.EventAdministrator.command;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Command {
		
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) {
		return true;
	}
	
	public boolean isPlayer(CommandSender sender) {
		if (sender instanceof Player) {
			return true;	
		}
		return false;	
	}
	
	public boolean isConsoleCommandSender(CommandSender sender) {
		if (sender instanceof ConsoleCommandSender) {
			return true;	
		}
		return false;	
	}
	
	public String permissionNode() {
		return "eventadministrator";
	}
	
	public boolean hasPermissionNode(CommandSender sender) {
		if(isPlayer(sender) && sender.hasPermission(permissionNode()) || isConsoleCommandSender(sender)) {
			return true;
		}
		return false;
	}
	
	public void sendMessage(CommandSender sender, String message) {
		if(isPlayer(sender)) {
			sender.sendMessage(message);
		} else {
			ConsoleLogs.sendMessage(message);
		}
	}

	public String usage() {
		return "/ea";
	}
	
	public void playerOnly(CommandSender sender) {
		ConsoleLogs.sendMessage("This command can only be run by a player.");
	}
}
