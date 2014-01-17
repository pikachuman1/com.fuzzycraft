package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.utilities.ConfigAccessor;
import me.fuzzystatic.EventManager.utilities.ConsoleLogs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class EventCycle implements CommandExecutor {
	
	private EventManager plugin;
	
	public EventCycle(EventManager plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSetCycle")) {
			if (args.length > 0) {
				ConfigAccessor eventAccessor = new ConfigAccessor(plugin, EventName.getFilename());
				FileConfiguration eventConfig = eventAccessor.getConfig();
				eventConfig.set(EventConfigurationStructure.CYCLE, Long.parseLong(String.valueOf(args[0])));
				eventAccessor.saveConfig();
				ConsoleLogs.message("New event cycle set.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event cycle set to" + ChatColor.DARK_AQUA + args[0] + ".");
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Please enter a value (in seconds). EX: /emsetcycle 14400.");
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("emGetCycle")) {
			ConfigAccessor eventAccessor = new ConfigAccessor(plugin, EventName.getFilename());
			FileConfiguration eventConfig = eventAccessor.getConfig();
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Cycle (in seconds): " + ChatColor.DARK_AQUA + eventConfig.getLong(EventConfigurationStructure.CYCLE));
			return true;
		}	
		return false;
	}
}
