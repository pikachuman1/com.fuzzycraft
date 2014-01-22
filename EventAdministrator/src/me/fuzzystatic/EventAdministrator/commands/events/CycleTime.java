package me.fuzzystatic.EventAdministrator.commands.events;

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
		if(hasPermissionNode(sender)) {
			if (args.length > 0) {		
				ecs.setCycleTime(Long.valueOf(args[0]));
				sendMessage(sender, message(args[0]));
			} else {
				ecs.getCycleTime();
				sendMessage(sender, message(args[0]));
			}
		}
	}
	
	@Override
	public String permissionNode() {
		return "eventadministrator.cycle";
	}
	
	public String message(String string) {	
		return ChatColor.LIGHT_PURPLE + "New event cycle set to " + ChatColor.DARK_AQUA + string + ".";
	}
}
