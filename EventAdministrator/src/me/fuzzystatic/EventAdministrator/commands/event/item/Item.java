package me.fuzzystatic.EventAdministrator.commands.event.item;

import me.fuzzystatic.EventAdministrator.commands.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class Item extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String[] args) {
		if(args.length > 1) {
			switch(args[1]) {
			case "a" 			: new ItemArmor().runCommand(plugin, sender, args); break;
			case "armor" 		: new ItemArmor().runCommand(plugin, sender, args); break;
			case "h" 			: new ItemHelmet().runCommand(plugin, sender, args); break;
			case "helm" 		: new ItemHelmet().runCommand(plugin, sender, args); break;
			case "helmet" 		: new ItemHelmet().runCommand(plugin, sender, args); break;
			case "c" 			: new ItemChestplate().runCommand(plugin, sender, args); break;
			case "chest" 		: new ItemChestplate().runCommand(plugin, sender, args); break;
			case "chestplate" 	: new ItemChestplate().runCommand(plugin, sender, args); break;
			case "l" 			: new ItemLeggings().runCommand(plugin, sender, args); break;
			case "legs" 		: new ItemLeggings().runCommand(plugin, sender, args); break;
			case "leggings"		: new ItemLeggings().runCommand(plugin, sender, args); break;
			case "b" 			: new ItemBoots().runCommand(plugin, sender, args); break;
			case "boots" 		: new ItemBoots().runCommand(plugin, sender, args); break;
			case "i" 			: new ItemInventory().runCommand(plugin, sender, args); break;
			case "inventory"	: new ItemInventory().runCommand(plugin, sender, args); break;
			}
			return true;
		} else {
			sender.sendMessage(new ItemArmor().usage());
			sender.sendMessage(new ItemHelmet().usage());
			sender.sendMessage(new ItemChestplate().usage());
			sender.sendMessage(new ItemLeggings().usage());
			sender.sendMessage(new ItemBoots().usage());
			sender.sendMessage(new ItemInventory().usage());
			return true;
		}
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("items");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " i{tem}";
	}
}
