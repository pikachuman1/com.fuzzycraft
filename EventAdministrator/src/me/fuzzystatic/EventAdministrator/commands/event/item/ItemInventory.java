package me.fuzzystatic.EventAdministrator.commands.event.item;

import java.util.ArrayList;
import java.util.List;

import me.fuzzystatic.EventAdministrator.configuration.SerializableItemString;
import me.fuzzystatic.EventAdministrator.configuration.structure.PlayerItemsConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemInventory extends Item {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			String eventName = new CommandSenderEventMap().get().get(sender);
			PlayerItemsConfigurationStructure pics = new PlayerItemsConfigurationStructure(plugin, eventName);
			Player player = (Player) sender;
			PlayerInventory inventory = player.getInventory();
			List<String> itemList = new ArrayList<String>();
			for (ItemStack item : inventory.getContents()) {
				SerializableItemString sis = new SerializableItemString(item);
				itemList.add(sis.serialize());
			}
			pics.setInventory(itemList);
			sendMessage(sender, ChatColor.LIGHT_PURPLE + "Inventory for event " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " has been set.");
			return true;
		}
		return false;
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("inventory");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " i{nventory}";
	}
}
