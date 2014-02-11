package me.fuzzystatic.EventAdministrator.commands.event.item;

import me.fuzzystatic.EventAdministrator.configuration.SerializableItemString;
import me.fuzzystatic.EventAdministrator.configuration.structure.PlayerItemsConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemArmor extends Item {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			String eventName = new CommandSenderEventMap().get().get(sender);
			PlayerItemsConfigurationStructure pics = new PlayerItemsConfigurationStructure(plugin, eventName);
			Player player = (Player) sender;
			PlayerInventory inventory = player.getInventory();
			SerializableItemString sisHelmet = new SerializableItemString(inventory.getHelmet());
			SerializableItemString sisChestplate = new SerializableItemString(inventory.getChestplate());
			SerializableItemString sisLeggings = new SerializableItemString(inventory.getLeggings());
			SerializableItemString sisBoots = new SerializableItemString(inventory.getBoots());
			pics.setHelmet(sisHelmet.serialize());
			pics.setChestplate(sisChestplate.serialize());
			pics.setLeggings(sisLeggings.serialize());
			pics.setBoots(sisBoots.serialize());
			sendMessage(sender, ChatColor.LIGHT_PURPLE + "Armor for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " has been set.");
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("armor");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " a{rmor}";
	}
}
