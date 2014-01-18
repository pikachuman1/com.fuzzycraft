package me.fuzzystatic.EventManager.schedules;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.entities.Entities;

import org.bukkit.Bukkit;

public class StopEvent {
	
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public StopEvent(EventManager plugin, String eventName) {
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public boolean stop() {
		EventSchedulerMap esm = new EventSchedulerMap();
		for (Integer integer : esm.getInvert().get(eventName)) {
			Bukkit.getScheduler().cancelTask(integer); 
			esm.get().remove(integer);
		}
		return true;
	}
	
	public boolean stopSubschedules(int id) {
		EventSchedulerMap esm = new EventSchedulerMap();
		for (Integer integer : esm.getInvert().get(eventName)) {
			if (integer != id) {
				Bukkit.getScheduler().cancelTask(integer);
				esm.get().remove(integer);
			}
		}
		return true;
	}
	
	public boolean clearEntities() {
		ecs.createFileStructure();
		if(ecs.existsPasteLocation()) {
			Entities eventEntities = new Entities(ecs.getPasteLocation().getWorld());
			if (ecs.existsExit()) {
				eventEntities.teleportAllPlayers(ecs.getExit());
			}
			eventEntities.removeAllNonPlayerEntities();
			return true;
		} else {
			return false;
		}
	}
}
