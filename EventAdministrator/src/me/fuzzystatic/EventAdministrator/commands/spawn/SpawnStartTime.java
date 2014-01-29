package me.fuzzystatic.EventAdministrator.commands.spawn;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnStartTime extends Spawn {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		String spawnName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		SpawnConfigurationStructure scs = new SpawnConfigurationStructure(plugin, eventName, spawnName);
		scs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if (args.length > 2) {
	    		scs.setStartTime(Long.valueOf(args[2]));
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Start time set to " + ChatColor.DARK_AQUA + args[2] + ChatColor.LIGHT_PURPLE + " for spawn " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + ".");
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current start time for spawn " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + " is " + ChatColor.DARK_AQUA + scs.getIsBoss() + ChatColor.LIGHT_PURPLE + ". TO SET: " + usage());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("amount");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " start <time (seconds)>";
	}
}
