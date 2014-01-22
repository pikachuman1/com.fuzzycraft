package me.fuzzystatic.EventAdministrator.commands.events;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.schedules.StartEvent;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Start extends Command {

	@Override
	public void runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, EventName.getName());	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if(args.length > 1) {
				StartEvent startEvent = new StartEvent(plugin);
				startEvent.start(args[1]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " has started");
			} else {
				if(isPlayer(sender)) {
					StartEvent startEvent = new StartEvent(plugin);
					startEvent.start(EventName.getName());
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " has started");
				} else {
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO START: " + usage());
				}
			}
		}
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
