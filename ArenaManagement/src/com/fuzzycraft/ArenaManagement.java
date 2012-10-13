package com.fuzzycraft;

import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArenaManagement extends JavaPlugin {

	private ArenaManagementPlayerListener pl = new ArenaManagementPlayerListener(this);
	
	public Location locationLeftClick() {
		return pl.locLC; 
	}
	
	public Location locationRightClick() {
		return pl.locRC;
	}
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		getCommand("ArenaManagement").setExecutor(new ArenaManagementCommandExecutor(this));
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
	
}
