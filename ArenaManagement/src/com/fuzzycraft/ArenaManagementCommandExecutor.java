package com.fuzzycraft;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ArenaManagementCommandExecutor implements CommandExecutor {
	
	public ArenaManagement am;
	 
	public ArenaManagementCommandExecutor(ArenaManagement instance) {
		am = instance;
	}
	
	public ArenaManagementPlayerListener ampl = new ArenaManagementPlayerListener(am);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("am") || cmd.getName().equalsIgnoreCase("arenamanagement")) { 
			if (args.length == 0) {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/am save - Save locations.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/am load - Load locations.");
			} else if (args[0].equals("save") || args[0].equals("s")) {
				if (am.getLocLC() == null && am.getLocRC() != null) {
					sender.sendMessage("First location not set (left click)");
					return true;
				} else if (am.getLocRC() == null && am.getLocLC() != null) {
					sender.sendMessage("Second location not set (right click)");
					return true;
				} else if (am.getLocLC() == null && am.getLocRC() == null) {
					sender.sendMessage("First location not set (left click)");
					sender.sendMessage("Second location not set (right click)");
					return true;
				} else {
					ConfigurationSerializableLocation locLC = new ConfigurationSerializableLocation(am.getLocLC());
					am.getConfig().set("Test.Location.position1", locLC);
					am.saveConfig();
					ConfigurationSerializableLocation locRC = new ConfigurationSerializableLocation(am.getLocRC());
					am.getConfig().set("Test.Location.position2", locRC);
					am.saveConfig();
					return true;
				}
			} else if (args[0].equals("load") || args[0].equals("l")) {
				Location pos1 = ((ConfigurationSerializableLocation) am.getConfig().get("Test.Location.position1")).getLocation();
				Location pos2 = ((ConfigurationSerializableLocation) am.getConfig().get("Test.Location.position2")).getLocation();
				//sender.sendMessage(pos1.toString());
				//sender.sendMessage(pos2.toString());
				Cuboid cuboid = new Cuboid(pos1, pos2);
				Integer x1 = cuboid.getMinX();
				Integer y1 = cuboid.getMinY();
				Integer z1 = cuboid.getMinZ();
				Integer x2 = cuboid.getMaxX();
				Integer y2 = cuboid.getMaxY();
				Integer z2 = cuboid.getMaxZ();
				Integer sizeX = cuboid.getSizeX();
				Integer sizeY = cuboid.getSizeY();
				Integer sizeZ = cuboid.getSizeZ();
				sender.sendMessage(x1.toString() + y1.toString() + z1.toString());
				sender.sendMessage(x2.toString() + y2.toString() + z2.toString());
				sender.sendMessage(sizeX.toString() + sizeY.toString() + sizeZ.toString());
				cuboid.saveBlockData();
			}
		} 
		return false;
	}
	
}