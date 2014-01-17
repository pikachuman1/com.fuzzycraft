package me.fuzzystatic.EventManager.schedules;

import java.util.ArrayList;
import java.util.List;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.commands.events.EventName;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventManager.entities.EventEntities;
import me.fuzzystatic.EventManager.utilities.ConsoleLogs;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class Spawning {
	
	private EventManager plugin;
	private final EventConfigurationStructure ecs;
	
	public Spawning(EventManager plugin) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(plugin, EventName.getName());
	}
	
	private List<Integer> bossIDs = new ArrayList<Integer>();
	
	private int spawnLimit;
	
	public void start() {
		for (String spawnName : ecs.getSpawns()) {
			final SpawnConfigurationStructure scs = new SpawnConfigurationStructure(this.plugin, spawnName);
			this.bossIDs = new ArrayList<Integer>();
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
				public void run() {
					World world = scs.getLocation().getWorld();
					EventEntities eventEntities = new EventEntities(world);
					if(eventEntities.bossAlive()) {
						spawn(scs, ecs.getCreatureLimit() - eventEntities.getMobs().size());
						for(Integer integer : getBossIDs()) bossIDs.add(integer);
					}			
					ConsoleLogs.message(bossIDs.toString());
				}	
			}, scs.getStartTime() * 20, scs.getCycleTime() * 20);
		}
	}
	
	public void spawn(SpawnConfigurationStructure scs, int creatureLimit) {
		if(ecs.getCreatureLimit() < scs.getAmount()) {
			this.spawnLimit = ecs.getCreatureLimit() - scs.getAmount();
		} else {
			this.spawnLimit = scs.getAmount();
		}
		for(int i = 0; i < spawnLimit; i++) {
			if(scs.getIsBoss() == true) bossIDs.add(scs.getLocation().getWorld().spawnEntity(scs.getLocation(), scs.getMob()).getEntityId());
		}
	}	
	
	public List<Integer> getBossIDs() {
		return this.bossIDs;
	}
}
