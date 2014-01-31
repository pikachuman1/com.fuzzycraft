package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class EventReminderCycleTime extends EventReminder {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {		
				ecs.setReminderCycleTime(Long.valueOf(args[1]));
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "New event reminder cycle set to " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " seconds for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ".");
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current reminder cycle for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " is " + ChatColor.DARK_AQUA + ecs.getReminderCycleTime() + ChatColor.LIGHT_PURPLE + " seconds. TO SET: " + usage());
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
		return super.usage() + " c{ycle} <time (in seconds)>";
	}
}
