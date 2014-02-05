package me.fuzzystatic.EventAdministrator.utilities;

import java.io.File;
import java.io.IOException;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

public class Regeneration {
	
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public Regeneration(JavaPlugin plugin, String eventName) {
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public boolean regen() {
		if(ecs.existsPasteLocation()) {
			Bukkit.getServer().broadcastMessage(ecs.getStartMessage());
			try {
		        EditSession es = new EditSession(new BukkitWorld(ecs.getPasteLocation().getWorld()), Integer.MAX_VALUE);
				CuboidClipboard cc = SchematicFormat.MCEDIT.load(new File(eventName + ".schematic"));
				cc.paste(es, BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir(), true);
			} catch (MaxChangedBlocksException | IOException | DataException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
