package me.fuzzystatic.EventManager.listeners;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.commands.events.EventStop;
import me.fuzzystatic.EventManager.entities.EventBossMultimap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public final class BossDeathListener implements Listener {

	public EventManager plugin;
    
	public BossDeathListener(EventManager plugin) {
		this.plugin = plugin;
	}
		
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {	// Sets up the delay for the player teleport
		if(EventBossMultimap.getBosses().containsValue(event.getEntity())) {
			EventStop es = new EventStop(plugin);
			es.stop();
		}
	}
}