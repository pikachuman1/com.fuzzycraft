package me.fuzzystatic.EventAdministrator.commands.event.spawn;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderSpawnMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class SpawnName extends Command {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
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
		return usage() + " name <spawn name>";
	}
}
