package com.fuzzycraft;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

public class ArenaManagementCommandExecutor implements CommandExecutor {
	
	static {
		ConfigurationSerialization.registerClass(ConfigurationSerializableLocation.class);
	}
	
	public ArenaManagement am;
	 
	public ArenaManagementCommandExecutor(ArenaManagement instance) {
		am = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("am") || cmd.getName().equalsIgnoreCase("arenamanagement")) { 
			if (args.length == 0) {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/am save - Save locations.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/am load - Load locations.");
			} else if (args[0].equals("save") || args[0].equals("s")) {
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
					ConfigurationSerializableLocation locLC = new ConfigurationSerializableLocation(am.locationLeftClick());
					am.getConfig().set("Test.Location.position1", locLC);
					am.saveConfig();
					ConfigurationSerializableLocation locRC = new ConfigurationSerializableLocation(am.locationRightClick());
					am.getConfig().set("Test.Location.position2", locRC);
					am.saveConfig();
					return true;
				}
			} else if (args[0].equals("load") || args[0].equals("l")) {
				Location pos1 = ((ConfigurationSerializableLocation) am.getConfig().get("Test.Location.position1")).getLocation();
				Location pos2 = ((ConfigurationSerializableLocation) am.getConfig().get("Test.Location.position2")).getLocation();
				sender.sendMessage(pos1.toString());
				sender.sendMessage(pos2.toString());
				Cuboid cuboid = new Cuboid(pos1, am.locationRightClick());
			}
			
			
		} 
		return false;
	}
	
}