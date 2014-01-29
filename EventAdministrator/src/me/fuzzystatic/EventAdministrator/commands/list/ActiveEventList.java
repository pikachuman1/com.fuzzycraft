package me.fuzzystatic.EventAdministrator.commands.list;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ActiveEventList extends EventList {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
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
