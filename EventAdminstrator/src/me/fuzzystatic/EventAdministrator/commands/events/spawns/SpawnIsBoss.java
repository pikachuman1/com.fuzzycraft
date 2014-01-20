package me.fuzzystatic.EventAdministrator.commands.events.spawns;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnIsBoss implements CommandExecutor {
	
	private EventAdministrator plugin;
	
	public SpawnIsBoss(EventAdministrator plugin) {
		this.plugin = plugin;
	}
		
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSpawnIsBoss")) {
			if(args.length > 0) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("This command can only be run by a player.");
				} else {
		    		SpawnConfigurationStructure scs = new SpawnConfigurationStructure(this.plugin, SpawnName.getName());	
					scs.createFileStructure();
		    		scs.setIsBoss(Boolean.valueOf(args[0]));
				    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New spawn is boss set.");
				    return true;
				}
			}
		}
		return false;
	}
}
