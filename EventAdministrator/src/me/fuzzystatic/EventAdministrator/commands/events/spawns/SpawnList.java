package me.fuzzystatic.EventAdministrator.commands.events.spawns;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.events.EventName;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnList implements CommandExecutor {
	
	private EventAdministrator plugin;
	
	public SpawnList(EventAdministrator plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSpawnList")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				
				EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
				
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "Spawns in Event:");
				
				if (!ecs.getSpawns().isEmpty()) {
					for (String spawnName : ecs.getSpawns()) {
						sender.sendMessage(ChatColor.DARK_AQUA + spawnName);
					}
				}else {
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "The " + ChatColor.DARK_AQUA + "event" + ChatColor.LIGHT_PURPLE + "you have selected has no " + ChatColor.DARK_AQUA + "spawns" + ChatColor.LIGHT_PURPLE + ".");
				}
				return true;
			}
		}
		return false;
	}
}
