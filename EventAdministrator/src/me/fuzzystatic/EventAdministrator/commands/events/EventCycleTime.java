package me.fuzzystatic.EventAdministrator.commands.events;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventCycleTime implements CommandExecutor {
	
	private EventAdministrator plugin;
	
	public EventCycleTime(EventAdministrator plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSetCycle")) {
			if (args.length > 0) {
	    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());	
				ecs.createFileStructure();
	    		ecs.setCycleTime(Long.valueOf(args[0]));
				ConsoleLogs.message("New event cycle set.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event cycle set to " + ChatColor.DARK_AQUA + args[0] + ".");
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Please enter a value (in seconds). EX: /emsetcycle 14400.");
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("emGetCycle")) {
			sender.sendMessage(commandLabel);
    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());			
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Cycle (in seconds): " + ChatColor.DARK_AQUA + ecs.getCycleTime());
			return true;
		}	
		return false;
	}
}
