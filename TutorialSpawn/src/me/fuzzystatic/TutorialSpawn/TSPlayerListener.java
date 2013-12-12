package me.fuzzystatic.TutorialSpawn;

import java.io.File;

import me.fuzzystatic.TutorialSpawn.utilities.ConfigAccessor;
import me.fuzzystatic.TutorialSpawn.utilities.SerializableLocation;
import me.fuzzystatic.TutorialSpawn.utilities.YMLLocation;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public final class TSPlayerListener implements Listener {

	public TutorialSpawn plugin;
    
	public TSPlayerListener(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
	
	private final String completedTutorialYml = "completedTutorial";
	private final boolean defaultCompleteTutorial = false;
	
	private final String joinEventsYml = "joinEvents";
	private final int defaultJoinEvents = 0;
			
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {	// Sets up the delay for the player teleport
		plugin.getConfig();
		int ticks = (int)plugin.config.getDouble(plugin.teleportDelayYml) * 20;
		Player player = event.getPlayer();
		new PlayerTeleport(this.plugin, player).runTaskLater(this.plugin, ticks);
	}
	
	public class PlayerTeleport extends BukkitRunnable {
		 
		private TutorialSpawn plugin;
		private Player player;

		public PlayerTeleport(TutorialSpawn plugin, Player player) {
			this.plugin = plugin;
			this.player = player;
		}
	 
	    public void run() { 	
			String fileName = "userdata" + File.separator + player.getName() + ".yml";
			ConfigAccessor configAccessor = new ConfigAccessor(plugin, fileName);		
			FileConfiguration userConfig = configAccessor.getConfig();
					
			if(!userConfig.contains(completedTutorialYml)) {
				userConfig.set(completedTutorialYml, defaultCompleteTutorial);
			}
			if(!userConfig.contains(joinEventsYml)) {
				userConfig.set(joinEventsYml, defaultJoinEvents);
			}	
			
			int maxJoinEvents = plugin.config.getInt(plugin.maxJoinEventsYml);
			int playerJoinEvents = userConfig.getInt(joinEventsYml);
			boolean completedTutorial = userConfig.getBoolean(completedTutorialYml);
			
			if(playerJoinEvents <= maxJoinEvents) {
				userConfig.set(joinEventsYml, userConfig.getInt(joinEventsYml) + 1);
			}
			if(playerJoinEvents == maxJoinEvents) {
				userConfig.set(completedTutorialYml, true);
			}
			
			configAccessor.saveConfig();
			
			// Determines if teleport is needed and executes the teleport
			plugin.getConfig();	
			if(completedTutorial == false) {
				if(!plugin.config.get(plugin.spawnYml + ".world").equals("")) {
					YMLLocation ymlLocation = new YMLLocation();
					SerializableLocation sl = new SerializableLocation(ymlLocation.getLocation(plugin.config, plugin.spawnYml));
			    	player.teleport(sl.getLocation());
				}
			}
	    }
	}
}