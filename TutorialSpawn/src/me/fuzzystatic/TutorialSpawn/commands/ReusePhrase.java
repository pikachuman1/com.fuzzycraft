package me.fuzzystatic.TutorialSpawn.commands;

import me.fuzzystatic.TutorialSpawn.TutorialSpawn;
import me.fuzzystatic.TutorialSpawn.utilities.SimpleClasses;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReusePhrase implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public ReusePhrase(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
		
	private SimpleClasses sc = new SimpleClasses();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tsgetrp")) {
			plugin.getConfig();
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Reuse passphrase setting: " + ChatColor.GREEN + plugin.config.getBoolean(plugin.reusePassphraseYml));
		}
		if (cmd.getName().equalsIgnoreCase("tssetrp")) {
			if (args.length > 0) {
			    plugin.getConfig();
			    plugin.config.set(plugin.reusePassphraseYml, Boolean.parseBoolean(args[0]));
			    plugin.saveConfig();
			    sc.logMessage(plugin.tsMarker + " New reuse passphrase setting set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New reuse passphrase setting set.");
			    return true;
			}
		}
		return false;
	}
}
