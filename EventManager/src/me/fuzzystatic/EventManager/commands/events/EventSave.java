package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.utilities.SerializableLocation;
import me.fuzzystatic.EventManager.utilities.WorldEditSession;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EmptyClipboardException;

public class EventSave implements CommandExecutor {
	
	private EventManager plugin;
		
	public EventSave(EventManager plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emSave")) {
			if(!(EventName.getName() == null)) {
				Player player = (Player) sender;
	    		SerializableLocation sl = new SerializableLocation(player.getLocation());
				try {
					WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
					worldEditSession.saveSchematic(EventName.getName());
				} catch (EmptyClipboardException e) {
					player.sendMessage(ChatColor.LIGHT_PURPLE + "Clipboard is empty.");
				}
	    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
	    		ecs.setPasteLocation(sl.serialize());
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Event name empty. Use /emname [name] to set event name.");
				return true;
			}
		}
		return false;
	}
}
