package com.fuzzycraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaManagementCommandExecutor implements CommandExecutor {
	
	public ArenaManagement am;
	 
	public ArenaManagementCommandExecutor(ArenaManagement instance) {
		am = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("am") || cmd.getName().equalsIgnoreCase("arenamanagement")) { 
			if (args.length == 0) {
				sender.sendMessage("/am loc - Display current location selections");
			} else if (args[0].equals("loc") || args[0].equals("location")) {
				if (am.locationLeftClick() == null && am.locationRightClick() != null) {
					sender.sendMessage("First location not set (left click)");
					return true;
				} else if (am.locationRightClick() == null && am.locationRightClick() != null) {
					sender.sendMessage("Second location not set (right click)");
					return true;
				} else if (am.locationLeftClick() == null && am.locationRightClick() == null) {
					sender.sendMessage("First location not set (left click)");
					sender.sendMessage("Second location not set (right click)");
					return true;
				} else {
					sender.sendMessage(am.locationLeftClick().toString());
					sender.sendMessage(am.locationRightClick().toString());
					return true;
				}
			}
		} 
		return false;
	}

}