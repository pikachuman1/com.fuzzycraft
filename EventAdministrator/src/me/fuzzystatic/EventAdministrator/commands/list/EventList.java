package me.fuzzystatic.EventAdministrator.commands.list;

import java.io.File;

import me.fuzzystatic.EventAdministrator.configurations.DirectoryStructure;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;
import net.minecraft.util.org.apache.commons.io.FilenameUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class EventList extends List {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			if(args.length > 2) {
				switch(args[2]) {
				case "a" 			: new ActiveEventList().runCommand(plugin, sender, args); break;
				case "active" 		: new ActiveEventList().runCommand(plugin, sender, args); break;
				}
				return true;
			} else {
				DirectoryStructure ds = new DirectoryStructure(plugin);
				
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "Events on this server:");
				
				for (File file : ds.eventFiles()) {
					if (file.isFile()) sender.sendMessage(ChatColor.DARK_AQUA + FilenameUtils.removeExtension(file.getName()));
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("events");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " e{vents}";
	}
}
