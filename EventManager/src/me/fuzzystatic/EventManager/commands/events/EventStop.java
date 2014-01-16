package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.entities.EventEntities;
import me.fuzzystatic.EventManager.utilities.ConfigAccessor;
import me.fuzzystatic.EventManager.utilities.SerializableLocation;
import me.fuzzystatic.EventManager.utilities.YMLLocation;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class EventStop implements CommandExecutor {

	private EventManager plugin;
	
	public EventStop(EventManager plugin) {
		this.plugin = plugin;
	}
	
	public void stopEvent() {
		Bukkit.getScheduler().cancelTasks(plugin);
		ConfigAccessor eventAccessor = new ConfigAccessor(plugin, EventName.getFilename());
		FileConfiguration eventConfig = eventAccessor.getConfig();
	   	World world = Bukkit.getWorld(eventConfig.getString(EventConfigurationStructure.PASTE_LOCATION + ".world"));
		if(!(eventConfig.get(EventConfigurationStructure.PASTE_LOCATION) == null) && !(eventConfig.get(EventConfigurationStructure.EXIT) == null)) {
			SerializableLocation slExit = new SerializableLocation(new YMLLocation().getLocation(eventConfig, EventConfigurationStructure.EXIT));
			EventEntities eventEntities = new EventEntities(world);
			eventEntities.teleportAllPlayers(slExit.getLocation());
			eventEntities.removeAllNonPlayerEntities();
		}
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStop")) {						
			stopEvent();
			return true;
		}
		return false;
	}
}
