package me.fuzzystatic.EventAdministrator.commands.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configuration.SerializableItem;
import me.fuzzystatic.EventAdministrator.configuration.structure.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configuration.structure.PlayerItemsConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

public class EventName extends Command {
		
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		if (hasPermissionNode(sender)) {
			if (args.length > 1) {	
				new CommandSenderEventMap().set(sender, args[1]);
				EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, args[1]);
				PlayerItemsConfigurationStructure pics = new PlayerItemsConfigurationStructure(plugin, args[1]);
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + args[1] + ChatColor.LIGHT_PURPLE + " selected.");
				if(ecs.createFileStructure()) {
					sendMessage(sender, ChatColor.LIGHT_PURPLE + "Config defaults created.");
				}
				ItemStack item = new ItemStack(Material.BOW, 64);
				ItemMeta meta = (ItemMeta) item.getItemMeta();
				List<String> lore = new ArrayList<String>();
			    lore.add("Special");
			    lore.add("Things");
			    lore.add("Can");
			    lore.add("Go");
			    lore.add("Here");
				meta.setDisplayName("It's a Fancy Name");
				meta.setLore(lore);
				item.setItemMeta(meta);
				item.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
				item.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
				item.serialize();
				SerializableItem si = new SerializableItem(item);
				pics.setInventory(si.serialize());
			} else {
				String eventName = new CommandSenderEventMap().get().get(sender);	
				sendMessage(sender, ChatColor.LIGHT_PURPLE + "Current selected event is " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ". TO SET: " + usage());
				PlayerItemsConfigurationStructure pics = new PlayerItemsConfigurationStructure(plugin, eventName);
				ConsoleLogs.sendMessage(pics.getInventory().toString());
				Player player = (Player) sender;
				PlayerInventory inventory = player.getInventory();
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
		return super.usage() + " name <event name>";
	}
}
