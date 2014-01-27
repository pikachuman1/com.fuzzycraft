package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.ReminderCommand;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class EventReminderMessage extends ReminderCommand {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {		
				ecs.setReminderMessage(args[1]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "New event reminder message set to " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ".");
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current reminder message for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " is " + ChatColor.DARK_AQUA + ecs.getReminderMessage() + ChatColor.LIGHT_PURPLE + ". TO SET: " + usage());
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
		return super.usage() + " m{essage} <time (in seconds)>";
	}

}
