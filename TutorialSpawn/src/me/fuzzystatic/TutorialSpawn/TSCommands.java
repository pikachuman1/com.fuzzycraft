package me.fuzzystatic.TutorialSpawn;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TSCommands implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public TSCommands(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
		
	private TSSimpleClasses tssc = new TSSimpleClasses();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ts") || cmd.getName().equalsIgnoreCase("tutorialspawn")) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("setspawn") && sender.hasPermission("tutorialspawn.setspawn")) {
					TSSerializableLocation tssl = new TSSerializableLocation(player.getLocation());
				    Map<String, Object> map = tssl.serialize();
					plugin.getConfig();
				    plugin.config.set(plugin.spawnYml + ".world", map.get("world"));
				    plugin.config.set(plugin.spawnYml + ".x", map.get("x"));
				    plugin.config.set(plugin.spawnYml + ".y", map.get("y"));
				    plugin.config.set(plugin.spawnYml + ".z", map.get("z"));
				    plugin.config.set(plugin.spawnYml + ".yaw", map.get("yaw"));
				    plugin.config.set(plugin.spawnYml + ".pitch", map.get("pitch"));
				    plugin.saveConfig();
				    tssc.logMessage(plugin.tsMarker + " New tutorial spawn set.");
				    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New tutorial spawn set.");
				}
				if (args[0].equalsIgnoreCase("setexit") && sender.hasPermission("tutorialspawn.exit")) {
					TSSerializableLocation tssl = new TSSerializableLocation(player.getLocation());
				    Map<String, Object> map = tssl.serialize();
					plugin.getConfig();
				    plugin.config.set(plugin.exitYml + ".world", map.get("world"));
				    plugin.config.set(plugin.exitYml + ".x", map.get("x"));
				    plugin.config.set(plugin.exitYml + ".y", map.get("y"));
				    plugin.config.set(plugin.exitYml + ".z", map.get("z"));
				    plugin.config.set(plugin.exitYml + ".yaw", map.get("yaw"));
				    plugin.config.set(plugin.exitYml + ".pitch", map.get("pitch"));
				    plugin.saveConfig();
				    tssc.logMessage(plugin.tsMarker + " New tutorial exit set.");
				    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New tutorial exit set.");
				}
				if (args[0].equalsIgnoreCase("setphrase") && sender.hasPermission("tutorialspawn.setphrase")) {	
					if (args.length > 1) {
					    plugin.getConfig();
					    plugin.config.set(plugin.phraseYml, args[1]);
					    plugin.saveConfig();
					    tssc.logMessage(plugin.tsMarker + " New tutorial passphrase set.");
					    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New tutorial passphrase set.");
					}
					else {
						sender.sendMessage(ChatColor.DARK_RED + "Please enter a passphrase.");
					}
				}
				if (args[0].equalsIgnoreCase("phrase") && sender.hasPermission("tutorialspawn.phrase")) {	
					if (args.length > 1) {
					    plugin.getConfig();
					    if(args[1].equalsIgnoreCase(plugin.config.get(plugin.phraseYml).toString())) {
					    	String user = File.separator + "userdata" + File.separator + player.getName() + ".yml";
							FileConfiguration userdata = new TSConfigManagement(plugin).getConfig(user);

							userdata.set("completedTutorial", "true");
							try {
								userdata.save(plugin.getDataFolder() + File.separator + user);
							} catch(Exception e1){
								e1.printStackTrace();
							}
							if(!userdata.get("completedTutorial").toString().equalsIgnoreCase("false")) {
								if(!plugin.config.get(plugin.exitYml + ".world").equals("")) {
									plugin.getConfig();
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("world", plugin.config.get(plugin.exitYml + ".world"));
								    map.put("x", plugin.config.get(plugin.exitYml + ".x"));
									map.put("y", plugin.config.get(plugin.exitYml + ".y"));
								  	map.put("z", plugin.config.get(plugin.exitYml + ".z"));
								  	map.put("yaw", plugin.config.get(plugin.exitYml + ".yaw"));
								   	map.put("pitch", plugin.config.get(plugin.exitYml + ".pitch"));
									TSSerializableLocation tssl = new TSSerializableLocation(map);
							        player.teleport(tssl.getLocation());
								}
							}
					    	sender.sendMessage(ChatColor.GREEN + "Correct! Teleporting out...");
					    } else {
					    	sender.sendMessage(ChatColor.DARK_RED + "Incorrect passphrase.");
					    }
					}
					else {
						sender.sendMessage(ChatColor.DARK_RED + "Please enter a passphrase.");
					}
				}
			} else {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ts setspawn - Set tutorial spawn location");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ts setexit - Set location to teleport once phrase is entered");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ts setphrase <passphrase> - Set passphrase");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ts phrase <passphrase> - Confirm phrase");
			}
		}
		return false;
	}
}
