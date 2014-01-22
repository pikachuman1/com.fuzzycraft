package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CycleTime extends Command {
	
	@Override
	public void runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, EventName.getName());	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {		
				ecs.setCycleTime(Long.valueOf(args[1]));
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "New event cycle set to " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " seconds for event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + ".");
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current cycle for event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " is " + ChatColor.DARK_AQUA + ecs.getCycleTime() + ChatColor.LIGHT_PURPLE + " seconds. TO SET: " + usage());
			}
		}
	}
	
	@Override
	public String permissionNode() {
		return "eventadministrator.cycle";
	}
	
	@Override
	public String usage() {
		return ChatColor.LIGHT_PURPLE + "/ea cycle <time (in seconds)>";
	}
}
