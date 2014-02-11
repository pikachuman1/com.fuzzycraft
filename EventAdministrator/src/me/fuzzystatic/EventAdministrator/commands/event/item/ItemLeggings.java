package me.fuzzystatic.EventAdministrator.commands.event.item;

import me.fuzzystatic.EventAdministrator.configuration.serializable.SerializableItemString;
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
			if (inventory.getLeggings() != null) {
				SerializableItemString sis = new SerializableItemString(inventory.getLeggings());
				pics.setItem(sis.serialize(), PlayerItemsConfigurationStructure.LEGGINGS);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Leggings for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " has been set.");
				return true;
			} else {
				sendMessage(sender, ChatColor.DARK_RED + "No leggings equipped. Please equip leggings.");
				return false;
			}
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("legs");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " l{egs}";
	}
}
