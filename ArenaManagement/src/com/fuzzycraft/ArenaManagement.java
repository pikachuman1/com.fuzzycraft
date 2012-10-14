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
		this.saveDefaultConfig();
		FileConfiguration config = this.getConfig();
		config.options().header("The Server.PvP node: Set to true if PvP is enabled, and false if PvP is disabled."); 
		config.addDefault("Server.PvP", true);
		config.options().copyDefaults(true);
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
