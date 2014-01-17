package me.fuzzystatic.EventManager.commands.events;

import java.io.File;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventName implements CommandExecutor {
	
	private EventManager plugin;
	
	public EventName(EventManager plugin) {
		this.plugin = plugin;
	}
	
	private static String eventName;
	
	public static void setName(String eventName) {
		EventName.eventName = eventName;
	}
	
	public static String getName() {
		return eventName;
	}
	
	public static String getFilename() {
		return EventConfigurationStructure.DIRECTORY + File.separator + getName() + File.separator + ".yml";
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emName")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if(args.length > 0) {
					setName(args[0]);
					EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);
					ecs.createFileStructure();
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + eventName + " selected.");
					return true;
				} else if(!(getName() == null)) {
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current selected event is " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + ". Use /emname [name] to change selected event.");
					return true;
				}
			}
		}
		return false;
	}
}
