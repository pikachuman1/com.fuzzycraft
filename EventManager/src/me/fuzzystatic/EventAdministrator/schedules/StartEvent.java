package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.EventManager;
import me.fuzzystatic.EventAdministrator.commands.events.EventName;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.Regeneration;

import org.bukkit.Bukkit;

public class StartEvent {
	
	private EventManager plugin;
	private int id;
	
	public StartEvent(EventManager plugin) {
		this.plugin = plugin;
	}

	public void start() {
		final String eventName = EventName.getName();
		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, eventName);
		ecs.createFileStructure();
		id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			public void run() {
				EventSchedulerMap esm = new EventSchedulerMap();
				esm.set(id, eventName);
								
				StopEvent stopEvent = new StopEvent(plugin, esm.get().get(id));
				Regeneration regeneration = new Regeneration(plugin, esm.get().get(id));
				Spawning spawning = new Spawning(plugin, esm.get().get(id));
				WorldConditions worldConditions = new WorldConditions(plugin, esm.get().get(id));
				Reminder reminder = new Reminder(plugin, esm.get().get(id));
				PlayerItems playerItems = new PlayerItems(plugin, esm.get().get(id));
				
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
	}
}
