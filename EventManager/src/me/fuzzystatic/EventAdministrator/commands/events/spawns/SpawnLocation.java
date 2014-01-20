package me.fuzzystatic.EventAdministrator.commands.events.spawns;

import me.fuzzystatic.EventAdministrator.EventManager;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnLocation implements CommandExecutor {
	
	private EventManager plugin;
	
	public SpawnLocation(EventManager plugin) {
		this.plugin = plugin;
	}
		
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSpawnLocation")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
		   		SpawnConfigurationStructure scs = new SpawnConfigurationStructure(this.plugin, SpawnName.getName());
				scs.createFileStructure();
		   		scs.setLocation(player.getLocation());
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New spawn location set.");
			    return true;
			}
		}
		return false;
	}
}
