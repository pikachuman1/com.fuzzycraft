package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.entities.EventBossMap;
import me.fuzzystatic.EventAdministrator.schedules.StopEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public final class BossDeathListener implements Listener {

	public EventAdministrator plugin;
    
	public BossDeathListener(EventAdministrator plugin) {
		this.plugin = plugin;
	}
		
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {	// Sets up the delay for the player teleport
		EventBossMap ebm = new EventBossMap();
		Integer entityId = event.getEntity().getEntityId();
		if(ebm.get().containsKey(entityId)) {
			StopEvent stopEvent = new StopEvent(plugin, ebm.get().get(entityId));
			stopEvent.stopSubschedules(entityId);
			ebm.get().remove(entityId);
		}
	}
}