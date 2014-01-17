package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.entities.EventEntities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventStop implements CommandExecutor {

	private EventManager plugin;
	public EventStop(EventManager plugin) {
		this.plugin = plugin;
	}
	
	public boolean stop() {
		Bukkit.getScheduler().cancelTasks(this.plugin); 
		return true;
	}
	
	public boolean clearEntities() {
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, EventName.getName());
		ecs.createFileStructure();
		if(ecs.existsPasteLocation()) {
			EventEntities eventEntities = new EventEntities(ecs.getPasteLocation().getWorld());
			if (ecs.existsExit()) {
				eventEntities.teleportAllPlayers(ecs.getExit());
			}
			eventEntities.removeAllNonPlayerEntities();
			return true;
		} else {
			return false;
		}
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStop")) {						
			if(stop()) {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "All events stopped.");
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Error stopping event.");	
			}
			if(clearEntities()) {
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "Event cleared.");
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Paste location does not exist. Event not cleared.");	
			}
			return true;
		}
		return false;
	}
}
