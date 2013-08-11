package me.fuzzystatic.TutorialSpawn;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class TSPlayerListener implements Listener {

	public TutorialSpawn plugin;
    
	public TSPlayerListener(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
		
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {		
		Player player = event.getPlayer();
		BukkitTask task = new PlayerTeleport(this.plugin, player).runTaskLater(this.plugin, 10);
	}
	
	public class PlayerTeleport extends BukkitRunnable {
		 
		private TutorialSpawn plugin;
		private Player player;

		public PlayerTeleport(TutorialSpawn plugin, Player player) {
			this.plugin = plugin;
			this.player = player;
		}
	 
	    public void run() { 		    	
			String user = File.separator + "userdata" + File.separator + player.getName() + ".yml";
			FileConfiguration userdata = new TSConfigManagement(plugin).getConfig(user);

			if(!userdata.contains("completedTutorial")) {
				userdata.set("completedTutorial", "false");
			}
			try {
				userdata.save(plugin.getDataFolder() + File.separator + user);
			} catch(Exception e1){
				e1.printStackTrace();
			}	
			plugin.getConfig();	
			if(userdata.get("completedTutorial").toString().equalsIgnoreCase("false")) {
				if(!plugin.config.get(plugin.spawnYml + ".world").equals("")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("world", plugin.config.get(plugin.spawnYml + ".world"));
				    map.put("x", plugin.config.get(plugin.spawnYml + ".x"));
					map.put("y", plugin.config.get(plugin.spawnYml + ".y"));
				  	map.put("z", plugin.config.get(plugin.spawnYml + ".z"));
				  	map.put("yaw", plugin.config.get(plugin.spawnYml + ".yaw"));
				   	map.put("pitch", plugin.config.get(plugin.spawnYml + ".pitch"));
					TSSerializableLocation tssl = new TSSerializableLocation(map);
			    	player.teleport(tssl.getLocation());
				}
			}
	    }
	}
}