package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class StopEvent {
	
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public StopEvent(JavaPlugin plugin, String eventName) {
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public boolean stop() {
		SchedulerEventMap sem = new SchedulerEventMap();
		for (Integer integer : sem.getInvert().get(eventName)) {
			Bukkit.getScheduler().cancelTask(integer); 
			sem.get().remove(integer);
		}
		return true;
	}
	
	public boolean stopSubschedules(int id) {
		SchedulerEventMap sem = new SchedulerEventMap();
		for (Integer integer : sem.getInvert().get(eventName)) {
			if (integer != id) {
				Bukkit.getScheduler().cancelTask(integer);
				sem.get().remove(integer);
			}
		}
		return true;
	}
	
	public boolean clearEntities() {
		ecs.createFileStructure();
		if(ecs.existsPasteLocation()) {
			Entities eventEntities = new Entities(ecs.getPasteLocation().getWorld());
			if (ecs.hasExit()) eventEntities.teleportAllPlayers(ecs.getExit());
			eventEntities.removeAllNonPlayerEntities();
			return true;
		} else {
			return false;
		}
	}
}
