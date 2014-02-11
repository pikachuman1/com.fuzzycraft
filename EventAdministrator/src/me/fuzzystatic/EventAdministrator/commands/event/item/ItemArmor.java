package me.fuzzystatic.EventAdministrator.commands.event.item;

import me.fuzzystatic.EventAdministrator.configuration.SerializableItem;
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
			SerializableItem siHelmet = new SerializableItem(inventory.getHelmet());
			SerializableItem siChestplate = new SerializableItem(inventory.getChestplate());
			SerializableItem siLeggings = new SerializableItem(inventory.getLeggings());
			SerializableItem siBoots = new SerializableItem(inventory.getBoots());
			pics.setHelmet(siHelmet.serialize());
			pics.setChestplate(siChestplate.serialize());
			pics.setLeggings(siLeggings.serialize());
			pics.setBoots(siBoots.serialize());
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
