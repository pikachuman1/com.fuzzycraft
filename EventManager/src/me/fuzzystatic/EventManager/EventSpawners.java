package me.fuzzystatic.EventManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.fuzzystatic.EventManager.commands.events.EventName;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventManager.entities.EventEntities;
import me.fuzzystatic.EventManager.utilities.ConfigAccessor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;

public class EventSpawners {
	
	private EventManager plugin;
	private final EventConfigurationStructure ecs;
	
	public EventSpawners(EventManager plugin, ConfigAccessor configAccessor) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(configAccessor);
	}
	
	private final List<Integer> bossIDs = new ArrayList<Integer>();
	
	private int spawnLimit;
	
	public void startSpawns() {
		final EventSpawners mobSpawn = new EventSpawners(spawnConfig);
		long startTick = scs.getStartTime() * 20;
		long cycleTick = scs.getCycleTime() * 20;
		bossIDs = new ArrayList<Integer>();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				ConfigAccessor eventAccessor = new ConfigAccessor(plugin, Strings.DIR_EVENTS + File.separator + EventName.getName() + File.separator + Strings.EVENT_FILE_NAME);
				FileConfiguration eventConfig = eventAccessor.getConfig();
				World world = Bukkit.getWorld(eventConfig.getString(Strings.EC_PASTE_LOCATION.toString() + ".world"));
				EventEntities eventEntities = new EventEntities(world);
				if(eventEntities.bossAlive()) {
					mobSpawn.spawn(eventConfig.getInt(Strings.EC_CREATURE_LIMIT.toString()) - eventEntities.getMobs().size());
					for(Integer integer : mobSpawn.getBossIDs()) {
						bossIDs.add(integer);
					}
				}			
				sc.logMessage(bossIDs.toString());
			}	
		}, startTick, cycleTick);
	}
	
	public void getSpawners() {
		
	}
	
	public void spawn(int creatureLimit) {
		if(ecs.getCreatureLimit() < scs.getAmount()) {
			spawnLimit = ecs.getCreatureLimit() - scs.getAmount();
		} else {
			spawnLimit = scs.getAmount();
		}
		for(int i = 0; i < spawnLimit; i++) {
			Entity entity = scs.getLocation().getWorld().spawnEntity(scs.getLocation(), scs.getMob());
			if(scs.getIsBoss() == true) bossIDs.add(entity.getEntityId());
		}
	}	
	
	public List<Integer> getBossIDs() {
		return bossIDs;
	}
}
