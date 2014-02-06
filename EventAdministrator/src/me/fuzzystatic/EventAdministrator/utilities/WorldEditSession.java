package me.fuzzystatic.EventAdministrator.utilities;

import java.io.File;
import java.io.IOException;

import me.fuzzystatic.EventAdministrator.configurations.DirectoryStructure;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

public class WorldEditSession {
	
	private final Player player;
	private final WorldEditPlugin wep;
	private final LocalPlayer localPlayer;
	private final LocalSession localSession;
	private final CuboidClipboard clipboard;
	private final LocalWorld localWorld;
	private final EditSession editSession;
	private final DirectoryStructure ds;
	
	public WorldEditSession(JavaPlugin plugin, Player player) throws EmptyClipboardException {
		this.ds = new DirectoryStructure(plugin);
		this.player = player;
		this.wep = (WorldEditPlugin) plugin.getServer().getPluginManager().getPlugin("WorldEdit");
		this.localPlayer = this.wep.wrapCommandSender(player);
		this.localSession = this.wep.getSession(player);
		this.clipboard = this.localSession.getClipboard();
		this.localWorld = null;
		this.editSession = this.localSession.createEditSession(this.localPlayer);
	}
	
	public WorldEditSession(JavaPlugin plugin, World world) {
		this.ds = new DirectoryStructure(plugin);
		this.player = null;
		this.wep = null;
		this.localPlayer = null;
		this.localSession = null;
		this.clipboard = null;
		this.localWorld = new BukkitWorld(world);
		this.editSession = new EditSession(this.localWorld, Integer.MAX_VALUE);
	}
	
	public void saveSchematic(String eventName) {
		File file = new File(ds.getSchematicsDirPath() + File.separator + eventName + ".schematic");
		try {
		    SchematicFormat.MCEDIT.save(clipboard, file);	
		    this.player.sendMessage(ChatColor.GREEN + "Schematic saved.");
		} catch (IOException | DataException e) {
			this.player.sendMessage(ChatColor.DARK_RED + "Schematic failed to save.");
			e.printStackTrace();
		}
	}
	
	public CuboidClipboard loadSchematic(String eventName) {
		CuboidClipboard clipboard = null;
		File file = new File(ds.getSchematicsDirPath() + File.separator + eventName + ".schematic");
		try {
			clipboard = SchematicFormat.MCEDIT.load(file);	
		} catch (IOException | DataException e) {
			this.player.sendMessage(ChatColor.DARK_RED + "Schematic failed to load.");
			e.printStackTrace();
		}
		return clipboard;
	}
	
	public LocalPlayer getLocalPlayer() {
    	return this.localPlayer;
    }
	
	public LocalSession getLocalSession() {
    	return this.localSession;
    }
    
    public LocalWorld getLocalWorld() {
    	return this.localWorld;
    }
    
    public EditSession getEditSession() {
    	return this.editSession;
    }
}
