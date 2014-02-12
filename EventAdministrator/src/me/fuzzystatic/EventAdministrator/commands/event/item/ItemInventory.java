package me.fuzzystatic.EventAdministrator.commands.event.item;

import java.util.ArrayList;
import java.util.List;

import me.fuzzystatic.EventAdministrator.configuration.serializable.SerializableItemString;
import me.fuzzystatic.EventAdministrator.configuration.structure.PlayerItemsConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemInventory extends Item {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if(args.length > 2) {
			switch(args[2]) {
			case "cycle" : new ItemInventoryCycleTime().runCommand(plugin, sender, args); break;
			case "start" : new ItemInventoryStartTime().runCommand(plugin, sender, args); break;
			}
			return true;
		} else {
			if (hasPermissionNode(sender) && isPlayer(sender)) {
				String eventName; 
				if(args.length > 2) {
					eventName = args[2];
				} else {
					eventName = new CommandSenderEventMap().get().get(sender);
				}
				PlayerItemsConfigurationStructure pics = new PlayerItemsConfigurationStructure(plugin, eventName);
				pics.createFileStructure();
				Player player = (Player) sender;
				List<String> itemList = new ArrayList<String>();
				for (ItemStack item : player.getInventory().getContents()) {
					if (item != null) {
						SerializableItemString sis = new SerializableItemString(item);
						itemList.add(sis.serialize().toString());
					}
				}
				pics.setInventoryItems(itemList);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Inventory for event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " has been set.");
				return true;
			}
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
		return super.usage() + " i{nventory} [event name]";
	}
}
