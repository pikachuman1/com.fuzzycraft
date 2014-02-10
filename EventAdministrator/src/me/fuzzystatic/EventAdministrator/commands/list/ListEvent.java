package me.fuzzystatic.EventAdministrator.commands.list;

import java.io.File;

import me.fuzzystatic.EventAdministrator.configuration.structure.DirectoryStructure;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;
import net.minecraft.util.org.apache.commons.io.FilenameUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ListEvent extends List {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender)) {
			if(args.length > 2) {
				switch(args[2]) {
				case "a" 			: new ListActiveEvent().runCommand(plugin, sender, args); break;
				case "active" 		: new ListActiveEvent().runCommand(plugin, sender, args); break;
				}
				return true;
			} else {
				DirectoryStructure ds = new DirectoryStructure(plugin);
				
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "Events on this server:");
				
				for (File file : ds.eventFiles()) {
					String eventName = FilenameUtils.removeExtension(file.getName()).toString();
					if (file.isFile()) {
						if(new SchedulerEventMap().getUniqueValues().contains(eventName)) {
							sender.sendMessage(ChatColor.DARK_AQUA + FilenameUtils.removeExtension(eventName) + " "  + ChatColor.WHITE+ "("  + ChatColor.GOLD + "active" + ChatColor.WHITE + ")");
						} else {
							sender.sendMessage(ChatColor.DARK_AQUA + FilenameUtils.removeExtension(eventName));
						}
					}
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
