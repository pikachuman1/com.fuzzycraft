package me.fuzzystatic.EventAdministrator.commands.events.spawns;

import me.fuzzystatic.EventAdministrator.EventManager;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnName implements CommandExecutor {
	
	private EventManager plugin;
	
	public SpawnName(EventManager plugin) {
		this.plugin = plugin;
	}
		
	private static String spawnName = "myFirstSpawn";
	
	public void setName(String spawnName) {
		SpawnName.spawnName = spawnName;
	}
	
	public static String getName() {
		return spawnName;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSpawnName")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if(args.length > 0) {
					setName(args[0]);
					SpawnConfigurationStructure scs = new SpawnConfigurationStructure(this.plugin, spawnName);
					scs.createFileStructure();
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Spawn " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + " selected.");
					return true;
				} else if(!(getName().equals(null))) {
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current selected spawn is " + ChatColor.DARK_AQUA + spawnName + ChatColor.LIGHT_PURPLE + ". Use /emspawnname [name] to change selected spawn.");
					return true;
				}
			}
		}
		return false;
	}
}
