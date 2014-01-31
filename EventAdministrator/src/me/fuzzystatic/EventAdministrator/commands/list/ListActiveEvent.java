package me.fuzzystatic.EventAdministrator.commands.list;

import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ListActiveEvent extends ListEvent {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender)) {
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Active events on this server:");

			for (String activeEvent : new CommandSenderEventMap().get().values()) {
				sender.sendMessage(ChatColor.DARK_AQUA + activeEvent);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("active");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " a{ctive}";
	}
}
