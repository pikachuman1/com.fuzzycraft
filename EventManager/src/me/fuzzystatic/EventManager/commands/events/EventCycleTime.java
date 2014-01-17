package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.utilities.ConsoleLogs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventCycleTime implements CommandExecutor {
	
	private EventManager plugin;
	
	public EventCycleTime(EventManager plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSetCycle")) {
			if (args.length > 0) {
	    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());			
	    		ecs.setCycleTime(Long.valueOf(args[0]));
				ConsoleLogs.message("New event cycle set.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event cycle set to" + ChatColor.DARK_AQUA + args[0] + ".");
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Please enter a value (in seconds). EX: /emsetcycle 14400.");
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("emGetCycle")) {
    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());			
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Cycle (in seconds): " + ChatColor.DARK_AQUA + ecs.getCycleTime());
			return true;
		}	
		return false;
	}
}
