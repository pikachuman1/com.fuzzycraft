package me.fuzzystatic.TutorialSpawn.commands;

import java.io.File;

import me.fuzzystatic.TutorialSpawn.TutorialSpawn;
import me.fuzzystatic.TutorialSpawn.utilities.ConfigManagement;
import me.fuzzystatic.TutorialSpawn.utilities.SerializableLocation;
import me.fuzzystatic.TutorialSpawn.utilities.SimpleClasses;
import me.fuzzystatic.TutorialSpawn.utilities.YMLLocation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Phrase implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public Phrase(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
		
	private SimpleClasses sc = new SimpleClasses();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tsphrase")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if (args.length > 0) {
				    if(args[0].equalsIgnoreCase(plugin.config.get(plugin.phraseYml).toString())) {
				    	Player player = (Player) sender;
				    	String user = File.separator + "userdata" + File.separator + player.getName() + ".yml";
						FileConfiguration userdata = new ConfigManagement(plugin).getConfig(user);
						if(userdata.getBoolean("completedTutorial") == false || plugin.config.getBoolean(plugin.reusePassphraseYml) == true) {
							plugin.getConfig();
							if(!plugin.config.get(plugin.exitYml + ".world").equals("")) {
								YMLLocation ymlLocation = new YMLLocation();
								SerializableLocation sl = new SerializableLocation(ymlLocation.getLocation(plugin.config, plugin.exitYml));
						        player.teleport(sl.getLocation());
						    	sender.sendMessage(ChatColor.GREEN + "Correct! Teleporting out...");
							}
							userdata.set("completedTutorial", true);
							try {
								userdata.save(plugin.getDataFolder() + File.separator + user);
							} catch(Exception e1){
								e1.printStackTrace();
							}
						} else {
							sender.sendMessage(ChatColor.LIGHT_PURPLE + "You have already completed the tutorial.");
						}
				    } else {
				    	sender.sendMessage(ChatColor.DARK_RED + "Incorrect passphrase.");
				    }
				    return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("tsgetphrase")) {
			plugin.getConfig();
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Passphrase: " + ChatColor.GREEN + plugin.config.get(plugin.phraseYml));
		}	
		if (cmd.getName().equalsIgnoreCase("tssetphrase")) {
			if (args.length > 0) {
				plugin.getConfig();
				plugin.config.set(plugin.phraseYml, args[0]);
				plugin.saveConfig();
				sc.logMessage(plugin.tsMarker + " New tutorial passphrase set.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "New tutorial passphrase set.");
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Please enter a passphrase.");
			}
		}
		return false;
	}
}
