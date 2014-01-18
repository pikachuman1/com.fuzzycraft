package me.fuzzystatic.EventManager.listeners;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.entities.EventBossMap;
import me.fuzzystatic.EventManager.schedules.StopEvent;

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
		EventBossMap ebm = new EventBossMap();
		Integer enitityId = event.getEntity().getEntityId();
		if(ebm.get().containsKey(enitityId)) {
			String eventName = ebm.get().get(enitityId);
			StopEvent stopEvent = new StopEvent(plugin, eventName);
			stopEvent.stop();
			ebm.get().remove(enitityId);
		}
	}
}