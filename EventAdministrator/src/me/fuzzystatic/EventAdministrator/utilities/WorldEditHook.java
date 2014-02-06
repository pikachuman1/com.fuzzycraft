package me.fuzzystatic.EventAdministrator.utilities;

import java.io.File;
import java.io.IOException;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

public class WorldEditHook {
	
	private JavaPlugin plugin;
	private String eventName;
	private EventConfigurationStructure ecs;
	
	public WorldEditHook(JavaPlugin plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public boolean save(Player player) {
		SerializableLocation sl = new SerializableLocation(player.getLocation());
		try {
			WorldEditSession wes = new WorldEditSession(plugin, player);
			CuboidClipboard cc = wes.getLocalSession().getClipboard();
			SchematicFormat.MCEDIT.save(cc, new File(eventName + ".schematic"));
			ecs.setPasteLocation(sl.serialize());
			return true;
		} catch (EmptyClipboardException e) {
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Clipboard is empty.");
		} catch (IOException | DataException e) {
			player.sendMessage(ChatColor.DARK_RED + "Could not load schematic.");
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean load() {
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
