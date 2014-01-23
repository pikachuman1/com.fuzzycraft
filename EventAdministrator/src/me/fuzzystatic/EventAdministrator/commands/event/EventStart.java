package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.schedules.StartEvent;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class EventStart extends Command {

	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		if (hasPermissionNode(sender)) {
			if(args.length > 1) {
				StartEvent startEvent = new StartEvent(plugin);
				startEvent.start(args[1]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " has started");
			} else {
				if(isPlayer(sender)) {
					StartEvent startEvent = new StartEvent(plugin);
					startEvent.start(eventName);
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " has started");
				} else {
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO START: " + usage());
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String permissionNode() {
		return "eventadministrator.start";
	}
	
	@Override
	public String usage() {
		return ChatColor.LIGHT_PURPLE + "/ea start [event name]";
	}
}
