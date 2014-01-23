package me.fuzzystatic.EventAdministrator.commands.event;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

public class EventName extends Command {
		
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {	
				new CommandSenderEventMap().set(sender, args[1]);
				EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, args[1]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + args[1] + " selected" + usage());
				if(ecs.createFileStructure()) {
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "Defaults created." + usage());
				}
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO SET: " + usage());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String permissionNode() {
		return "eventadministrator.name";
	}
	
	@Override
	public String usage() {
		return ChatColor.LIGHT_PURPLE + "/ea name <event name>";
	}
}
