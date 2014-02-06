package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;
import me.fuzzystatic.EventAdministrator.worldedit.WorldEditLoad;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class StopEvent {
	
	private JavaPlugin plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public StopEvent(JavaPlugin plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	public void stop() {
		SchedulerEventMap sem = new SchedulerEventMap();
		for (Integer integer : sem.getInvert().get(eventName)) {
			Bukkit.getScheduler().cancelTask(integer); 
			sem.get().remove(integer);
		}
	}
	
	public void stopSubschedules(int id) {
		SchedulerEventMap sem = new SchedulerEventMap();
		for (Integer integer : sem.getInvert().get(eventName)) {
			if (integer != id) {
				Bukkit.getScheduler().cancelTask(integer);
				sem.get().remove(integer);
			}
		}
	}
		
	public boolean clearEntities() {
		ecs.createFileStructure();
		if(ecs.existsPasteLocation()) {
			WorldEditLoad wel = new WorldEditLoad(plugin, eventName);
			Entities eventEntities = new Entities(ecs.getPasteLocation().getWorld(), wel.getClipboard().getOrigin(), wel.getClipboard().getSize());
			if (ecs.hasExit()) eventEntities.teleportAllPlayers(ecs.getExit());
			eventEntities.removeAllNonPlayerEntities();
			return true;
		} else {
			return false;
		}
	}
}
