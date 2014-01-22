package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;
import me.fuzzystatic.EventAdministrator.entities.BossEventMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class Spawning {
	
	private EventAdministrator plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	private final int parentId;
	
	public Spawning(EventAdministrator plugin, int parentId) {
		this.plugin = plugin;
		this.eventName = new SchedulerEventMap().get().get(parentId);
		this.ecs = new EventConfigurationStructure(plugin, eventName);
		this.parentId = parentId;
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
				SchedulerEventMap esm = new SchedulerEventMap();
				esm.set(id, eventName);
			}
		}
	}
	
	public void spawn(SpawnConfigurationStructure scs, int creatureLimit) {
		if(creatureLimit < scs.getAmount()) {
			this.spawnLimit = creatureLimit;
		} else {
			this.spawnLimit = scs.getAmount();
		}
		for(int i = 0; i < this.spawnLimit; i++) {
			Entity entity = scs.getLocation().getWorld().spawnEntity(scs.getLocation(), scs.getMob());
			if(scs.getIsBoss()) {
				BossEventMap esm = new BossEventMap();
				esm.set(entity.getEntityId(), parentId);
			} 
		}
	}	
}
