package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;
import me.fuzzystatic.EventAdministrator.sql.SQLInsert;

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
		SQLInsert.insertPlayerData(EventAdministrator.getConnection(), dcs.getMySQLPrefix(), player.getPlayerListName());
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Entities entities = new Entities(player.getWorld());
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
	
	}
}
