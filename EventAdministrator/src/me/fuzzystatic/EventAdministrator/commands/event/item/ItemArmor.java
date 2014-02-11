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
			boolean hasArmor = false;
			if (inventory.getHelmet() != null) {
				SerializableItemString sisHelmet = new SerializableItemString(inventory.getHelmet());
				pics.setHelmet(sisHelmet.serialize());
				hasArmor = true;
			}
			if (inventory.getHelmet() != null) {
				SerializableItemString sisChestplate = new SerializableItemString(inventory.getChestplate());
				pics.setChestplate(sisChestplate.serialize());
				hasArmor = true;
			}
			if (inventory.getHelmet() != null) {
				SerializableItemString sisLeggings = new SerializableItemString(inventory.getLeggings());
				pics.setLeggings(sisLeggings.serialize());
				hasArmor = true;
			}
			if (inventory.getHelmet() != null) {
				SerializableItemString sisBoots = new SerializableItemString(inventory.getBoots());
				pics.setBoots(sisBoots.serialize());
				hasArmor = true;
			}
			if (hasArmor) {
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Armor for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " has been set.");
				return true;
			} else {
				sendMessage(sender, ChatColor.DARK_RED + "No armor equipped. Please equip some armor.");
				return false;
			}
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
