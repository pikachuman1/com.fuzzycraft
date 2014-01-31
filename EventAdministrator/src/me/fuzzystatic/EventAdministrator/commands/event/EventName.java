package me.fuzzystatic.EventAdministrator.commands.event;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

public class EventName extends Command {
		
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {	
				new CommandSenderEventMap().set(sender, args[1]);
				EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, args[1]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " selected.");
				if(ecs.createFileStructure()) {
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "Config defaults created.");
				}
			} else {
				String eventName = new CommandSenderEventMap().get().get(sender);	
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current selected event is " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ". TO SET: " + usage());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("name");
		permission.addParent(super.permission(), true);
		return permission;	
	}
	
	@Override
	public String usage() {
		return super.usage() + " name <event name>";
	}
}
