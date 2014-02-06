package me.fuzzystatic.EventAdministrator.commands.list;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ListSpawn extends List {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		EventConfigurationStructure ecs;
		String eventName;
		if (hasPermissionNode(sender)) {			
			if (args.length > 2) {	
				eventName = args[2];
				ecs = new EventConfigurationStructure(plugin, eventName);	
				ecs.createFileStructure();
			} else {
				eventName = new CommandSenderEventMap().get().get(sender);
				ecs = new EventConfigurationStructure(plugin, eventName);	
				ecs.createFileStructure();
			}
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Spawns in event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ": ");	
			if (ecs.getSpawns() != null) {
				for (String spawnName : ecs.getSpawns()) {
					SpawnConfigurationStructure scs = new SpawnConfigurationStructure(plugin, eventName, spawnName);
					scs.createFileStructure();
					if (scs.getIsBoss()) {
						sender.sendMessage(ChatColor.DARK_AQUA + spawnName + " "  + ChatColor.WHITE+ "("  + ChatColor.GOLD + "boss" + ChatColor.WHITE + ")");
					} else {
						sender.sendMessage(ChatColor.DARK_AQUA + spawnName);
					}
				}
			} else {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "The event has no " + ChatColor.DARK_AQUA + "spawns" + ChatColor.LIGHT_PURPLE + ".");
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("spawns");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " s{pawns} <event name>";
	}
	
}
