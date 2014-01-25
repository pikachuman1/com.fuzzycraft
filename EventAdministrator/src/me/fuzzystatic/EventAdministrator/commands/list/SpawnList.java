package me.fuzzystatic.EventAdministrator.commands.list;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.ListCommand;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class SpawnList extends ListCommand {

	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {			
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Spawns in event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ": ");
			
			if (ecs.getSpawns() != null) {
				for (String spawnName : ecs.getSpawns()) {
					sender.sendMessage(ChatColor.DARK_AQUA + spawnName);
				}
			} else {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "The event has no " + ChatColor.DARK_AQUA + "spawns" + ChatColor.LIGHT_PURPLE + ".");
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("spawns");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " s{pawns}";
	}
	
}
