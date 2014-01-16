package me.fuzzystatic.EventManager.commands.events.spawns;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.commands.events.EventName;
import me.fuzzystatic.EventManager.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventManager.utilities.ConfigAccessor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnStart implements CommandExecutor {
	
	private EventManager plugin;
	
	public SpawnStart(EventManager plugin) {
		this.plugin = plugin;
	}
		
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSpawnStart")) {
			if(args.length > 0) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("This command can only be run by a player.");
				} else {
		    		ConfigAccessor eventAccessor = new ConfigAccessor(plugin, EventName.getFilename());			
		    		SpawnConfigurationStructure scs = new SpawnConfigurationStructure(eventAccessor, EventName.getFilename());
		    		scs.setStart(Long.valueOf(args[0]));
				    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New spawn start time set.");
				    return true;
				}
			}
		}
		return false;
	}
}
