package me.fuzzystatic.EventAdministrator.worldedit;

import java.io.IOException;

import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

public class WorldEditSave extends WorldEdit {

	public WorldEditSave(JavaPlugin plugin, String eventName) {
		super(plugin, eventName);
	}
	
	public CuboidClipboard getClipboard(Player player) {
		try {
			return new WorldEditSession(super.plugin, player).getLocalSession().getClipboard();
		} catch (EmptyClipboardException e) {
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Clipboard is empty.");
		}
		return null;
	}
	
	public boolean save(Player player) {
		SerializableLocation sl = new SerializableLocation(player.getLocation());
		try {
			SchematicFormat.MCEDIT.save(getClipboard(player), super.file);
			this.ecs.setPasteLocation(sl.serialize());
			return true;
		} catch (IOException | DataException e) {
			player.sendMessage(ChatColor.DARK_RED + "Could not load schematic.");
			e.printStackTrace();
		}
		return false;
	}
}
