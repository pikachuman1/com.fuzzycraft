package me.fuzzystatic.EventManager.listeners;

import java.util.Collection;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.schedules.EventSchedulerMultimap;
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
		EventSchedulerMultimap esm = new EventSchedulerMultimap();
		if(esm.get().containsValue(event.getEntity())) {
			Collection<String> eventNames = esm.getInvert().get(event.getEntity().getEntityId());
			for (String eventName : eventNames) {
				StopEvent stopEvent = new StopEvent(plugin, eventName);
				stopEvent.stop();
			}
		}
	}
}