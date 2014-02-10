package me.fuzzystatic.EventAdministrator.commands.teleport;

import me.fuzzystatic.EventAdministrator.configuration.structure.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class TeleportExit extends Teleport {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (ecs.hasExit()) {
			if (hasPermissionNode(sender) && isPlayer(sender)) {		
				Player player = (Player) sender;
				player.teleport(ecs.getExit());
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED + "Exit location does not exist.");
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("exit");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " ex{it}";
	}
}
