package me.fuzzystatic.EventAdministrator.worldedit;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

public class WorldEditLoad extends WorldEdit {

	public WorldEditLoad(JavaPlugin plugin, String eventName) {
		super(plugin, eventName);
	}
	
	public CuboidClipboard getClipboard() {
		if(super.ecs.existsPasteLocation()) {
			try {
				return SchematicFormat.MCEDIT.load(file);
			} catch (IOException | DataException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean paste() {
        EditSession es = new EditSession(new BukkitWorld(super.ecs.getPasteLocation().getWorld()), Integer.MAX_VALUE);
		try {
			getClipboard().paste(es, BukkitUtil.toVector(super.ecs.getPasteLocation()), super.ecs.getNoAir(), false);
			return true;
		} catch (MaxChangedBlocksException e) {
			e.printStackTrace();
		}
		return false;
	}
}
