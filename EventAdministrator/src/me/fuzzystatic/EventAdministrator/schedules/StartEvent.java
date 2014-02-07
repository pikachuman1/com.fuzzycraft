package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.listeners.EventStatsListener;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.worldedit.WorldEditLoad;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StartEvent {
	
	private JavaPlugin plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	private int id;
	
	public StartEvent(JavaPlugin plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(this.plugin, eventName);
	}
	
	public boolean start() {
		ecs.createFileStructure();
		id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			public void run() {
				SchedulerEventMap sem = new SchedulerEventMap();
				sem.set(id, eventName);
										
				StopEvent se = new StopEvent(plugin, sem.get().get(id));
				WorldEditLoad wel = new WorldEditLoad(plugin, sem.get().get(id));
				Spawning s = new Spawning(plugin, id);
				WorldConditions wc = new WorldConditions(plugin, sem.get().get(id));
				Reminder r = new Reminder(plugin, sem.get().get(id));
				PlayerItems pi = new PlayerItems(plugin, sem.get().get(id));
				
				se.stopSubschedules(id);
				
				if (EventAdministrator.getConnection() != null) {
					// Create event databases
					DefaultConfigurationStructure dcs = new DefaultConfigurationStructure(plugin);	
					SQLSchema ss = new SQLSchema(EventAdministrator.getConnection(), dcs.getMySQLPrefix());
					ss.createEventPveStatsTable(eventName);
					ss.createEventPvpStatsTable(eventName);
					
					// Start stats listener
					EventStatsListener esl = new EventStatsListener(plugin, eventName, id);
					PluginManager pm = plugin.getServer().getPluginManager();
					pm.registerEvents(esl, plugin);
				}
				
				// Start event
				if (wel.paste()) {
					se.clearEntities();
					s.start();
					wc.start();
					r.start();
					pi.start();
				} 
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN + ecs.getStartMessage());
			}
		}, 0, ecs.getCycleTime() * 20);
		return true;
	}
}
