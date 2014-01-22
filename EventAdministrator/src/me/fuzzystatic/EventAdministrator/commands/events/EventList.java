package me.fuzzystatic.EventAdministrator.commands.events;

import java.io.File;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DirectoryStructure;
import net.minecraft.util.org.apache.commons.io.FilenameUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventList implements CommandExecutor {
	
	private EventAdministrator plugin;
	
	public EventList(EventAdministrator plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emEventlist")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				DirectoryStructure ds = new DirectoryStructure(plugin);
				
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "Events On This Server:");
				
				for (File file : ds.eventFiles()) {
					if (file.isFile()) sender.sendMessage(ChatColor.DARK_AQUA + FilenameUtils.removeExtension(file.getName()));
				}
				return true;
			}
		}
		return false;
	}
}
