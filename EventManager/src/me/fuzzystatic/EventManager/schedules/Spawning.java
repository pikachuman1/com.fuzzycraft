package me.fuzzystatic.EventManager.schedules;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventManager.entities.Entities;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class Spawning {
	
	private EventManager plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public Spawning(EventManager plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
		
	private int spawnLimit;
	
	public void start() {
		if (ecs.getSpawns() != null) {
			for (String spawnName : ecs.getSpawns()) {
				final SpawnConfigurationStructure scs = new SpawnConfigurationStructure(this.plugin, spawnName);
				int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
					public void run() {
						World world = scs.getLocation().getWorld();
						Entities eventEntities = new Entities(world);
						spawn(scs, ecs.getCreatureLimit() - eventEntities.getMobs().size());	
					}	
				}, scs.getStartTime() * 20, scs.getCycleTime() * 20);
				EventSchedulerMultimap esm = new EventSchedulerMultimap();
				esm.set(eventName, id);
			}
		}
	}
	
	public void spawn(SpawnConfigurationStructure scs, int creatureLimit) {
		if(ecs.getCreatureLimit() < scs.getAmount()) {
			this.spawnLimit = creatureLimit - scs.getAmount();
		} else {
			this.spawnLimit = scs.getAmount();
		}
		for(int i = 0; i < this.spawnLimit; i++) {
			Entity entity = scs.getLocation().getWorld().spawnEntity(scs.getLocation(), scs.getMob());
			if(scs.getIsBoss()) {
				EventSchedulerMultimap esm = new EventSchedulerMultimap();
				esm.set(eventName, entity.getEntityId());
			} 
		}
	}	
}
