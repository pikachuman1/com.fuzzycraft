package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.utilities.Regeneration;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class StartEvent {
	
	private JavaPlugin plugin;
	private final String eventName;
	
	private int id;
	
	public StartEvent(JavaPlugin plugin, String eventName) {
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
										
				DefaultConfigurationStructure dcs = new DefaultConfigurationStructure(plugin);	

				StopEvent stopEvent = new StopEvent(plugin, sem.get().get(id));
				Regeneration regeneration = new Regeneration(plugin, sem.get().get(id));
				Spawning spawning = new Spawning(plugin, id);
				WorldConditions worldConditions = new WorldConditions(plugin, sem.get().get(id));
				Reminder reminder = new Reminder(plugin, sem.get().get(id));
				PlayerItems playerItems = new PlayerItems(plugin, sem.get().get(id));
				
				stopEvent.stopSubschedules(id);
				stopEvent.clearEntities();
				if (EventAdministrator.getConnection() != null) {
					new SQLSchema(EventAdministrator.getConnection(), dcs.getMySQLPrefix()).createEventPveStatsTable(eventName);
					new SQLSchema(EventAdministrator.getConnection(), dcs.getMySQLPrefix()).createEventPvpStatsTable(eventName);
				}
				if (regeneration.regen()) {
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
