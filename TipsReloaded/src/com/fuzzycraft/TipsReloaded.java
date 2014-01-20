package com.fuzzycraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class TipsReloaded extends JavaPlugin {
    private Random rng;
    
    public void loadConfigDefaults() {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("name", "demo");
    	map.put("fname", "fdemo");
        this.getConfig().addDefault("global", map);
        this.getConfig().options().copyDefaults(true);
        this.getConfig().options().header("Tips Reloaded configuration file");
        this.saveConfig();
    }
    
    public void onEnable() {
        Logger logger = getServer().getLogger();
        rng = new Random(System.currentTimeMillis());

		loadConfigDefaults();
		
        // commands
        getCommand("tips").setExecutor(new TipsReloadedCommandExecutor(this));
        
        PluginDescriptionFile pdfFile = this.getDescription();
        logger.log(Level.INFO, "[Tips] version " + pdfFile.getVersion() + " enabled");
    }
    
    public void onDisable() {
        // unschedule current tips
        getServer().getScheduler().cancelTasks(this);
    }
}
