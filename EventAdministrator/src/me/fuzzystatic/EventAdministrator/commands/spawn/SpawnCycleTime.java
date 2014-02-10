package me.fuzzystatic.EventAdministrator.commands.spawn;

import me.fuzzystatic.EventAdministrator.configuration.structure.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configuration.structure.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnCycleTime extends Spawn {
	
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
	    		scs.setCycleTime(Long.valueOf(args[2]));
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Cycle set to " + ChatColor.DARK_AQUA + args[2] + ChatColor.LIGHT_PURPLE + " for spawn " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + ".");
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current cycle for spawn " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + " is " + ChatColor.DARK_AQUA + scs.getAmount() + ChatColor.LIGHT_PURPLE + ". TO SET: " + usage());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("cycle");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " cycle <time (seconds)>";
	}
}
