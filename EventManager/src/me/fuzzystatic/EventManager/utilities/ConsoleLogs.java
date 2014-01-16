package me.fuzzystatic.EventManager.utilities;

import org.bukkit.Bukkit;

public class ConsoleLogs {	
	public static void logMessage(String message){
	    Bukkit.getConsoleSender().sendMessage(message);
	}
}