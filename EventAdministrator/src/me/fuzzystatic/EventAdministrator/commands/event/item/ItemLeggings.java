package me.fuzzystatic.EventAdministrator.commands.event.item;

import me.fuzzystatic.EventAdministrator.configuration.structure.PlayerItemsConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemLeggings extends Item {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			String eventName = new CommandSenderEventMap().get().get(sender);
			PlayerItemsConfigurationStructure pics = new PlayerItemsConfigurationStructure(plugin, eventName);
			Player player = (Player) sender;
			PlayerInventory inventory = player.getInventory();
			pics.setLeggings(inventory.getLeggings().serialize());
			sendMessage(sender, ChatColor.LIGHT_PURPLE + "Leggings for event " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " has been set.");
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("leggings");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " l{eggings}";
	}
}
