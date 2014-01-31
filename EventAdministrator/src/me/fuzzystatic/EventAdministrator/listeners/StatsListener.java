package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdate;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StatsListener implements Listener {

	private final DefaultConfigurationStructure dcs;
    
	public StatsListener(JavaPlugin plugin) {
		this.dcs = new DefaultConfigurationStructure(plugin);
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		new SQLUpdate(EventAdministrator.getConnection(), dcs.getMySQLPrefix(), player.getPlayerListName()).setPlayerData();
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Entity killer = event.getEntity().getKiller();
		if (killer instanceof Player) {
			// Insert into PvP record
			
		} else {
			// Insert into PvE record
			
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
	
	}
}
