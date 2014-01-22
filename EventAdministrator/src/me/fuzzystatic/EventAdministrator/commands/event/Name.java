package me.fuzzystatic.EventAdministrator.commands.event;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.PlayerEventMap;

public class Name extends Command {
		
	@Override
	public void runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		PlayerEventMap pem = new PlayerEventMap();
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {	
				pem.set(sender, args[1]);
				EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, args[1]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + args[1] + " selected" + usage());
				if(ecs.createFileStructure()) {
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "Defaults created." + usage());
				}
			} else {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO SET: " + usage());
			}
		}
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
