package me.fuzzystatic.EventAdministrator.utilities;

import org.bukkit.Bukkit;

public class ConsoleLogs {	
	public static void message(String message){
	    Bukkit.getConsoleSender().sendMessage(message);
	}
}