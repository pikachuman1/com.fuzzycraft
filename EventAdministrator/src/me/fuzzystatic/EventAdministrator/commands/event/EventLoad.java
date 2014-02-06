package me.fuzzystatic.EventAdministrator.commands.event;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.utilities.WorldEditHook;

public class EventLoad extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			WorldEditHook weh = new WorldEditHook(plugin, eventName);
			weh.load();
			return true;
		} else {
			playerOnly(sender);
			return false;
		}
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("load");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " load";
	}
}
