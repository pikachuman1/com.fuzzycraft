package me.fuzzystatic.EventAdministrator.listeners;

import java.sql.Connection;

import me.fuzzystatic.EventAdministrator.entities.Entities;

import org.bukkit.entity.Player;
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
		Player player = event.getEntity();
		Entities entities = new Entities(player.getWorld());
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
	
	}
}
