package me.fuzzystatic.EventAdministrator.commands.spawn;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.SpawnCommand;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class SpawnIsBoss extends SpawnCommand {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		String spawnName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		SpawnConfigurationStructure scs = new SpawnConfigurationStructure(plugin, eventName, spawnName);
		scs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if (args.length > 2) {
	    		scs.setIsBoss(Boolean.valueOf(args[2]));
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "isBoss set to " + ChatColor.DARK_AQUA + args[2] + ChatColor.LIGHT_PURPLE + " for spawn " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + ".");
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current isBoss for spawn " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + " is " + ChatColor.DARK_AQUA + scs.getIsBoss() + ChatColor.LIGHT_PURPLE + ". TO SET: " + usage());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("isboss");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " isboss [true|false]";
	}
}
