package me.fuzzystatic.EventAdministrator.utilities;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;

public class Regeneration {
	
	private EventAdministrator plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public Regeneration(EventAdministrator plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public boolean regen() {
		if(ecs.existsPasteLocation()) {
			Bukkit.getServer().broadcastMessage(ecs.getStartMessage());
			try {
				WorldEditSession worldEditSession = new WorldEditSession(plugin, ecs.getPasteLocation().getWorld());
			   	CuboidClipboard clipboard = worldEditSession.loadSchematic(eventName);
				clipboard.paste(worldEditSession.getEditSession(), BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir());
			} catch (MaxChangedBlocksException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
