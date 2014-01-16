package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.utilities.ConfigAccessor;
import me.fuzzystatic.EventManager.utilities.SerializableLocation;
import me.fuzzystatic.EventManager.utilities.ConsoleLogs;
import me.fuzzystatic.EventManager.utilities.YMLLocation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EventLocations implements CommandExecutor {
	
	private EventManager plugin;
	
	public EventLocations(EventManager plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSetEntrance")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
				ConfigAccessor eventAccessor = new ConfigAccessor(plugin, EventName.getFilename());
				FileConfiguration eventConfig = eventAccessor.getConfig();
	    		SerializableLocation sl = new SerializableLocation(player.getLocation());
				YMLLocation ymlLocation = new YMLLocation();
				ymlLocation.setLocation(sl.serialize(), eventConfig, EventConfigurationStructure.ENTRANCE);
			    eventAccessor.saveConfig();
			    ConsoleLogs.logMessage("New event entrance set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event entrance set.");
			    return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("emSetExit")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
				ConfigAccessor eventAccessor = new ConfigAccessor(plugin, EventName.getFilename());
				FileConfiguration eventConfig = eventAccessor.getConfig();
	    		SerializableLocation sl = new SerializableLocation(player.getLocation());
				YMLLocation ymlLocation = new YMLLocation();
				ymlLocation.setLocation(sl.serialize(), eventConfig, EventConfigurationStructure.EXIT);
				eventAccessor.saveConfig();
				ConsoleLogs.logMessage("New event exit set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event exit set.");
			    return true;
			}
		}
		return false;
	}
}
