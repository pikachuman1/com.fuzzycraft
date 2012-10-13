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
		if (cmd.getName().equalsIgnoreCase("basic")) { // If the player typed /basic then do the following...
			if (am.locationLeftClick() == null) {
				sender.sendMessage("FAILURE");
				return true;
					
			} else { 
				sender.sendMessage(am.locationLeftClick().toString());
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("basic2")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				sender.sendMessage("Test.");
			}
			return true;
		}
		return false;
	}

}