package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.entities.EventBossMap;
import me.fuzzystatic.EventAdministrator.schedules.EventSchedulerMap;
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
	public void onEntityDeath(EntityDeathEvent event) {
		EventBossMap ebm = new EventBossMap();
		EventSchedulerMap esm = new EventSchedulerMap();
		Integer entityId = event.getEntity().getEntityId();
		if(ebm.get().containsKey(entityId)) {
			StopEvent stopEvent = new StopEvent(plugin, esm.get().get(ebm.get().get(entityId)));
			stopEvent.stopSubschedules(ebm.get().get(entityId));
			ebm.get().remove(entityId);
		}
	}
}