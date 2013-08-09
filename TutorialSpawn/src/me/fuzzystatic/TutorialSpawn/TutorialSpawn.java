package me.fuzzystatic.TutorialSpawn;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TutorialSpawn extends JavaPlugin {
	
	private TSPlayerListener tspl = new TSPlayerListener(this);
	
	public void onEnable() {
		 PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.tspl, this);
		
		FileConfiguration config;
		
		try {
			config = getConfig();
			
			File TutorialSpawn = new File("plugins" + File.separator + "TutorialSpawn" + File.separator + "config.yml");
			TutorialSpawn.mkdir();
			
			if(!config.contains("tutorialspawn.location.world")) {
				config.set("tutorialspawn.location.world", "");
			}
			
			if(!config.contains("tutorialspawn.location.x")) {
				config.set("tutorialspawn.location.x", "");
			}
			
			if(!config.contains("tutorialspawn.location.y")) {
				config.set("tutorialspawn.location.y", "");
			}
			
			if(!config.contains("tutorialspawn.location.z")) {
				config.set("tutorialspawn.location.z", "");
			}
			
			if(!config.contains("tutorialspawn.location.pitch")) {
				config.set("tutorialspawn.location.pitch", "");
			}
			
			if(!config.contains("tutorialspawn.location.yaw")) {
				config.set("tutorialspawn.location.yaw", "");
			}
	        
			saveConfig();
		} catch(Exception e1){
			e1.printStackTrace();
		}

	}
	

}

