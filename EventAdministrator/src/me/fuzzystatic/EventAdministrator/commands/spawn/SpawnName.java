package me.fuzzystatic.EventAdministrator.commands.spawn;

import me.fuzzystatic.EventAdministrator.configuration.structure.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderSpawnMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnName extends Spawn {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender)) {
			if (args.length > 2) {
				String eventName = new CommandSenderEventMap().get().get(sender);
				new CommandSenderSpawnMap().set(sender, args[2]);
				SpawnConfigurationStructure scs = new SpawnConfigurationStructure(plugin, eventName, args[2]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Spawn " + ChatColor.DARK_AQUA + args[2] + " selected" + usage());
				if(scs.createFileStructure()) {
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
	public Permission permission() {
		Permission permission = new Permission("name");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " name <spawn name>";
	}
}
