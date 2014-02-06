package me.fuzzystatic.EventAdministrator.worldedit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class WorldEditSession {
	
	private final WorldEditPlugin wep;
	private final LocalPlayer localPlayer;
	private final LocalSession localSession;
	
	public WorldEditSession(JavaPlugin plugin, Player player) {
		this.wep = (WorldEditPlugin) plugin.getServer().getPluginManager().getPlugin("WorldEdit");
		this.localPlayer = this.wep.wrapCommandSender(player);
		this.localSession = this.wep.getSession(player);
	}
	
	public LocalPlayer getLocalPlayer() {
    	return this.localPlayer;
    }
	
	public LocalSession getLocalSession() {
    	return this.localSession;
    }
}
