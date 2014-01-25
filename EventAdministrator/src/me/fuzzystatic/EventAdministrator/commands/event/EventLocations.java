package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class EventLocations extends Command {

	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);
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
			return true;
		} else {
			sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO SET: " + usage());
			return false;
		}
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("locations");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " loc{ation} [en{trance}|ex{it}]";
	}
}
