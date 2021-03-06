package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.schedules.StopEvent;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class EventStop extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender)) {
			if(args.length > 1) {
				StopEvent stopEvent = new StopEvent(plugin, args[1]);
				stopEvent.stop();
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " has stopped");
			} else {
				if(isPlayer(sender)) {
					String eventName = new CommandSenderEventMap().get().get(sender);
					StopEvent stopEvent = new StopEvent(plugin, eventName);
					stopEvent.stop();
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " has stopped");
				} else {
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO START: " + usage());
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("stop");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " stop [event name]";
	}
}
