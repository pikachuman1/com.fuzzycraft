package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.entities.EventEntities;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventStop implements CommandExecutor {

	private EventManager plugin;
	private final EventConfigurationStructure ecs;
	
	public EventStop(EventManager plugin) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(plugin, EventName.getName());
	}
	
	public void stop() {
		Bukkit.getScheduler().cancelTasks(this.plugin); 	
		if(!(ecs.getPasteLocation() == null) && !(ecs.getExit() == null)) {
			EventEntities eventEntities = new EventEntities(ecs.getPasteLocation().getWorld());
			eventEntities.teleportAllPlayers(ecs.getExit());
			eventEntities.removeAllNonPlayerEntities();
		}
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStop")) {						
			stop();
			return true;
		}
		return false;
	}
}
