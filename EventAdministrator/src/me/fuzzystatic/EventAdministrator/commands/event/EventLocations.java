package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventLocations implements CommandExecutor {
	
	private EventAdministrator plugin;
	
	public EventLocations(EventAdministrator plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSetEntrance")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
	    		SerializableLocation sl = new SerializableLocation(player.getLocation());
	    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
	    		ecs.setEntrance(sl.serialize());
			    ConsoleLogs.sendMessage("New event entrance set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event entrance set.");
			    return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("emSetExit")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
	    		SerializableLocation sl = new SerializableLocation(player.getLocation());
	    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
	    		ecs.setExit(sl.serialize());
				ConsoleLogs.sendMessage("New event exit set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event exit set.");
			    return true;
			}
		}
		return false;
	}
}
