package me.fuzzystatic.EventAdministrator.listeners;

import java.sql.Connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StatsListener implements Listener {

	private JavaPlugin plugin;
	private Connection connection;
    
	public StatsListener(JavaPlugin plugin, Connection connection) {
		this.plugin = plugin;
		this.connection = connection;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
	
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
	
	}
}
