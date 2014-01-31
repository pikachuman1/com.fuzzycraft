package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.maps.BossEventMap;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;
import me.fuzzystatic.EventAdministrator.schedules.StopEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BossDeathListener implements Listener {

	private JavaPlugin plugin;
    
	public BossDeathListener(JavaPlugin plugin) {
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