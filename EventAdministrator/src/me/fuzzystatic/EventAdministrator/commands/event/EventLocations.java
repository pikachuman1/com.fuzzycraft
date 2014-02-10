package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configuration.SerializableLocation;
import me.fuzzystatic.EventAdministrator.configuration.structure.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class EventLocations extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			if (args.length > 1) {
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
