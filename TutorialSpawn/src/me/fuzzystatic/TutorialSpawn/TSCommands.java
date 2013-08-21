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
		if (cmd.getName().equalsIgnoreCase("tsphrase")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if (args.length > 0) {
				    if(args[0].equalsIgnoreCase(plugin.config.get(plugin.phraseYml).toString())) {
				    	Player player = (Player) sender;
				    	String user = File.separator + "userdata" + File.separator + player.getName() + ".yml";
						FileConfiguration userdata = new TSConfigManagement(plugin).getConfig(user);
						if(userdata.getBoolean("completedTutorial") == false) {
							plugin.getConfig();
							if(!plugin.config.get(plugin.exitYml + ".world").equals("")) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("world", plugin.config.get(plugin.exitYml + ".world"));
							    map.put("x", plugin.config.get(plugin.exitYml + ".x"));
								map.put("y", plugin.config.get(plugin.exitYml + ".y"));
							  	map.put("z", plugin.config.get(plugin.exitYml + ".z"));
							  	map.put("yaw", plugin.config.get(plugin.exitYml + ".yaw"));
							   	map.put("pitch", plugin.config.get(plugin.exitYml + ".pitch"));
								TSSerializableLocation tssl = new TSSerializableLocation(map);
						        player.teleport(tssl.getLocation());
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
				tssc.logMessage(plugin.tsMarker + " New tutorial passphrase set.");
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "New tutorial passphrase set.");
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Please enter a passphrase.");
			}
		}
		if (cmd.getName().equalsIgnoreCase("tsspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
		    	plugin.getConfig();
				if(!plugin.config.get(plugin.spawnYml + ".world").equals("")) {
					Player player = (Player) sender;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("world", plugin.config.get(plugin.spawnYml + ".world"));
				    map.put("x", plugin.config.get(plugin.spawnYml + ".x"));
					map.put("y", plugin.config.get(plugin.spawnYml + ".y"));
				  	map.put("z", plugin.config.get(plugin.spawnYml + ".z"));
				  	map.put("yaw", plugin.config.get(plugin.spawnYml + ".yaw"));
				   	map.put("pitch", plugin.config.get(plugin.spawnYml + ".pitch"));
					TSSerializableLocation tssl = new TSSerializableLocation(map);
			    	player.teleport(tssl.getLocation());
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
		}
		if (cmd.getName().equalsIgnoreCase("tsexit")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {	
				plugin.getConfig();
				if(!plugin.config.get(plugin.exitYml + ".world").equals("")) {
					Player player = (Player) sender;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("world", plugin.config.get(plugin.exitYml + ".world"));
				    map.put("x", plugin.config.get(plugin.exitYml + ".x"));
					map.put("y", plugin.config.get(plugin.exitYml + ".y"));
				  	map.put("z", plugin.config.get(plugin.exitYml + ".z"));
				  	map.put("yaw", plugin.config.get(plugin.exitYml + ".yaw"));
				   	map.put("pitch", plugin.config.get(plugin.exitYml + ".pitch"));
					TSSerializableLocation tssl = new TSSerializableLocation(map);
			    	player.teleport(tssl.getLocation());
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "Exit not set");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("tssetexit")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {	
				Player player = (Player) sender;
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
		}
		if (cmd.getName().equalsIgnoreCase("tsgetmje")) {
			plugin.getConfig();
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Maximum join events value: " + ChatColor.GREEN + plugin.config.getInt(plugin.maxJoinEventsYml));
		}
		if (cmd.getName().equalsIgnoreCase("tssetmje")) {
			if (args.length > 0) {
			    plugin.getConfig();
			    plugin.config.set(plugin.maxJoinEventsYml, Integer.parseInt(args[0]));
			    plugin.saveConfig();
			    tssc.logMessage(plugin.tsMarker + " New maximum join events value set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New maximum join events value set.");
			    return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("tsgettd")) {
			plugin.getConfig();
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Teleport delay value: " + ChatColor.GREEN + plugin.config.getDouble(plugin.teleportDelayYml));
		}
		if (cmd.getName().equalsIgnoreCase("tssettd")) {
			if (args.length > 0) {
			    plugin.getConfig();
			    plugin.config.set(plugin.teleportDelayYml, Double.parseDouble(args[0]));
			    plugin.saveConfig();
			    tssc.logMessage(plugin.tsMarker + " New teleport delay value set.");
			    sender.sendMessage(ChatColor.LIGHT_PURPLE + "New teleport delay value set.");
			    return true;
			}
		}
		return false;
	}
}
