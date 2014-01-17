package me.fuzzystatic.EventManager.utilities;

import org.bukkit.Bukkit;

public class ConsoleLogs {	
	public static void message(String message){
	    Bukkit.getConsoleSender().sendMessage(message);
	}
}