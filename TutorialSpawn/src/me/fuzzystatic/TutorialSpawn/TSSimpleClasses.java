package me.fuzzystatic.TutorialSpawn;

import org.bukkit.Bukkit;

public class TSSimpleClasses {
	
	public TutorialSpawn plugin;
    
	public TSSimpleClasses(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
	
	public void logMessage(String message){
	    Bukkit.getConsoleSender().sendMessage(message);
	}
}
