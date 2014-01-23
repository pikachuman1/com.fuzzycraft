package me.fuzzystatic.EventAdministrator.commands.event.spawn;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class SpawnCycleTime extends Command {
	
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
	public String permissionNode() {
		return "eventadministrator.spawns.cycle";
	}
	
	@Override
	public String usage() {
		return ChatColor.LIGHT_PURPLE + "/ea s cycle <time (seconds)>";
	}
}
