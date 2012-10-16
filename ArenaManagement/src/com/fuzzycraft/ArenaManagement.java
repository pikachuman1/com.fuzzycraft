package com.fuzzycraft;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArenaManagement extends JavaPlugin {

	private ArenaManagementPlayerListener ampl = new ArenaManagementPlayerListener(this);
	
	public Location locationLeftClick() {
		return ampl.locLC;
	}
	public Location locationRightClick() {
		return ampl.locRC;
	}
	
	@Override
	public void onEnable() {
		this.reloadConfig();
		FileConfiguration config = this.getConfig();
		config.options().header("ArenaManagement configuration file."); 
		this.saveConfig();	
		getCommand("ArenaManagement").setExecutor(new ArenaManagementCommandExecutor(this));	
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.ampl, this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
	
}
