package me.fuzzystatic.TutorialSpawn;

import java.io.File;

import me.fuzzystatic.TutorialSpawn.TSConfigManagement;
import me.fuzzystatic.TutorialSpawn.TSSimpleClasses;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TSPlayerListener implements Listener {

	public TutorialSpawn plugin;
    
	public TSPlayerListener(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
	
	TSConfigManagement tscm = new TSConfigManagement(plugin);
	TSSimpleClasses tssc = new TSSimpleClasses(plugin);
	
	//FileConfiguration config = plugin.getConfig();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {		
		Player player = event.getPlayer();
		
		FileConfiguration userdata = tscm.getConfig("plugins" + File.separator + "TutorialSpawn" + File.separator + "userdata" + File.separator + player.getName());
		
		if(!userdata.contains("completedTutorial")) {
			userdata.set("completedTutorial", "false");
		}
		try {
			userdata.save("plugins" + File.separator + "TutorialSpawn" + File.separator + "userdata" + File.separator + player.getName() + ".yml");
			tssc.logMessage("New config created for player " + player.toString());
		} catch(Exception e1){
			e1.printStackTrace();
		}
	}
}