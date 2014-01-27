package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class EventAutoStart extends Command {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {		
				ecs.setAutoStart(Boolean.valueOf(args[1]));
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "New event auto-start set to " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ".");
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current auto-start for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " is " + ChatColor.DARK_AQUA + ecs.getAutoStart() + ChatColor.LIGHT_PURPLE + ". TO SET: " + usage());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("autostart");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " a{uto}s{tart} [true|false]>";
	}
}
