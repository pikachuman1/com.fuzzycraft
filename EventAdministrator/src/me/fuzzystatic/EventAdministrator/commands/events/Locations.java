package me.fuzzystatic.EventAdministrator.commands.events;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Locations extends Command {

	@Override
	public void runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, EventName.getName());	
		ecs.createFileStructure();
		if (args.length > 1) {
			if (hasPermissionNode(sender) && isPlayer(sender)) {
				if (args[1].equalsIgnoreCase("en") || args[1].equalsIgnoreCase("entrance")) {
					Player player = (Player) sender;
					SerializableLocation sl = new SerializableLocation(player.getLocation());
					ecs.setEntrance(sl.serialize());
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event entrance set.");
				}
				if (args[1].equalsIgnoreCase("ex") || args[1].equalsIgnoreCase("exit")) {
					Player player = (Player) sender;
					SerializableLocation sl = new SerializableLocation(player.getLocation());
					ecs.setExit(sl.serialize());
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event exit set.");
				}
			} else {
				playerOnly(sender);
			}
		} else {
			sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO SET: " + usage());
		}
	}
	
	@Override
	public String permissionNode() {
		return "eventadministrator.locations";
	}
	
	@Override
	public String usage() {
		return ChatColor.LIGHT_PURPLE + "/ea loc [en{trance}|ex{it}]";
	}
}
