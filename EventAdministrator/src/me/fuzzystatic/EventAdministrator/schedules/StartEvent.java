package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.Regeneration;

import org.bukkit.Bukkit;

public class StartEvent {
	
	private EventAdministrator plugin;
	private int id;
	private final String eventName;
	
	public StartEvent(EventAdministrator plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
	}
	
	public boolean start() {
		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, eventName);
		ecs.createFileStructure();
		id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			public void run() {
				SchedulerEventMap sem = new SchedulerEventMap();
				sem.set(id, eventName);
								
				StopEvent stopEvent = new StopEvent(plugin, sem.get().get(id));
				Regeneration regeneration = new Regeneration(plugin, sem.get().get(id));
				Spawning spawning = new Spawning(plugin, id);
				WorldConditions worldConditions = new WorldConditions(plugin, sem.get().get(id));
				Reminder reminder = new Reminder(plugin, sem.get().get(id));
				PlayerItems playerItems = new PlayerItems(plugin, sem.get().get(id));
				
				stopEvent.stopSubschedules(id);
				stopEvent.clearEntities();
				if(regeneration.regen()) {
					spawning.start();
					worldConditions.start();
					reminder.start();
					playerItems.start();
				} 
			}
		}, 0, ecs.getCycleTime() * 20);
		return true;
	}
}
