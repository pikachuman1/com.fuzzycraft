package me.fuzzystatic.TutorialSpawn.commands;

import me.fuzzystatic.TutorialSpawn.TutorialSpawn;
import me.fuzzystatic.TutorialSpawn.utilities.SimpleClasses;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TeleportDelay implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public TeleportDelay(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
		
	private SimpleClasses sc = new SimpleClasses();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tsgettd")) {
			plugin.getConfig();
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Teleport delay value: " + ChatColor.GREEN + plugin.config.getDouble(plugin.teleportDelayYml));
		}
		if (cmd.getName().equalsIgnoreCase("tssettd")) {
			if (args.length > 0) {
			    plugin.getConfig();
			    plugin.config.set(plugin.teleportDelayYml, Double.parseDouble(args[0]));
			    plugin.saveConfig();
			    sc.logMessage(plugin.tsMarker + " New teleport delay value set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New teleport delay value set.");
			    return true;
			}
		}
		return false;
	}
}
