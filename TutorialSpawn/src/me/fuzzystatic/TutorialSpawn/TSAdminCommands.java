package me.fuzzystatic.TutorialSpawn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TSAdminCommands implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public TSAdminCommands(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
	
	TSSimpleClasses tssc = new TSSimpleClasses(plugin);
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("ts setspawn") && sender.hasPermission("tutorialspawn.setspawn")) {
			Player player = (Player) sender;
			Location location = player.getLocation();
		    player.sendMessage(location.toString());
		    tssc.logMessage("New tutorial spawn location " + player.toString());
		}
		return false;
	}
}
