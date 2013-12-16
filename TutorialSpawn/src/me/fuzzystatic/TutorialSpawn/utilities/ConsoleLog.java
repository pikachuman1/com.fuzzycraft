package me.fuzzystatic.TutorialSpawn.utilities;

import org.bukkit.Bukkit;

public class ConsoleLog {	
	public void message(String message){
	    Bukkit.getConsoleSender().sendMessage(message);
	}
}