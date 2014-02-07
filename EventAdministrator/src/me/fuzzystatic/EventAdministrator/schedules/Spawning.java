package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;
import me.fuzzystatic.EventAdministrator.maps.BossEventMap;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public class Spawning {
	
	private JavaPlugin plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	private final int eventID;
	
	public Spawning(JavaPlugin plugin, int eventID) {
		this.plugin = plugin;
		this.eventName = new SchedulerEventMap().get().get(eventID);
		this.ecs = new EventConfigurationStructure(plugin, eventName);
		this.eventID = eventID;
	}
		
	private int spawnLimit;
	
	public void start() {
		if (ecs.getSpawns() != null) {
			for (String spawnName : ecs.getSpawns()) {
				final SpawnConfigurationStructure scs = new SpawnConfigurationStructure(this.plugin, this.eventName, spawnName);
				int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
					public void run() {
						World world = scs.getLocation().getWorld();
						Entities eventEntities = new Entities(world);
						spawn(scs, ecs.getCreatureLimit() - eventEntities.getMobs().size());	
					}	
				}, scs.getStartTime() * 20, scs.getCycleTime() * 20);
				SchedulerEventMap esm = new SchedulerEventMap();
				esm.set(id, this.eventName);
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
				BossEventMap bem = new BossEventMap();
				bem.set(entity.getEntityId(), this.eventID);
			} 
		}
	}	
}
