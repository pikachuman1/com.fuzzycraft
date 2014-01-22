package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.entities.BossEventMap;
import me.fuzzystatic.EventAdministrator.schedules.SchedulerEventMap;
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
		BossEventMap bem = new BossEventMap();
		SchedulerEventMap sem = new SchedulerEventMap();
		Integer entityId = event.getEntity().getEntityId();
		if(bem.get().containsKey(entityId)) {
			StopEvent stopEvent = new StopEvent(plugin, sem.get().get(bem.get().get(entityId)));
			stopEvent.stopSubschedules(bem.get().get(entityId));
			bem.get().remove(entityId);
		}
	}
}