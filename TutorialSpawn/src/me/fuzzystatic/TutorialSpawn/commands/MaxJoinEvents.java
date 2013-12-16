package me.fuzzystatic.TutorialSpawn.commands;

import me.fuzzystatic.TutorialSpawn.TutorialSpawn;
import me.fuzzystatic.TutorialSpawn.utilities.ConsoleLog;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MaxJoinEvents implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public MaxJoinEvents(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
		
	private ConsoleLog cl = new ConsoleLog();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tsgetmje")) {
			plugin.getConfig();
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Maximum join events value: " + ChatColor.GREEN + plugin.config.getInt(plugin.maxJoinEventsYml));
		}
		if (cmd.getName().equalsIgnoreCase("tssetmje")) {
			if (args.length > 0) {
			    plugin.getConfig();
			    plugin.config.set(plugin.maxJoinEventsYml, Integer.parseInt(args[0]));
			    plugin.saveConfig();
			    cl.message(plugin.tsMarker + " New maximum join events value set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New maximum join events value set.");
			    return true;
			}
		}
		return false;
	}
}
