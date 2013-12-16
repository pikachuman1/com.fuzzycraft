package me.fuzzystatic.TutorialSpawn.commands;

import me.fuzzystatic.TutorialSpawn.TutorialSpawn;
import me.fuzzystatic.TutorialSpawn.utilities.ConsoleLog;
import me.fuzzystatic.TutorialSpawn.utilities.SerializableLocation;
import me.fuzzystatic.TutorialSpawn.utilities.YMLLocation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public Spawn(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
		
	private ConsoleLog cl = new ConsoleLog();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tsspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
		    	plugin.getConfig();
				if(!plugin.config.get(plugin.spawnYml + ".world").equals("")) {
					Player player = (Player) sender;
					YMLLocation ymlLocation = new YMLLocation();
					SerializableLocation sl = new SerializableLocation(ymlLocation.getLocation(plugin.config, plugin.spawnYml));
			    	player.teleport(sl.getLocation());
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "Spawn not set");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("tssetspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
				plugin.getConfig();
	    		SerializableLocation sl = new SerializableLocation(player.getLocation());
				YMLLocation ymlLocation = new YMLLocation();
				ymlLocation.setLocation(sl.serialize(), plugin.config, plugin.spawnYml);
			    plugin.saveConfig();
			    cl.message(plugin.tsMarker + " New tutorial spawn set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New tutorial spawn set.");
			}
		}
		return false;
	}
}
