package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;
import me.fuzzystatic.EventAdministrator.maps.BossEventMap;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.worldedit.WorldEditLoad;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.Vector;

public class EventStatsListener extends StatsListener implements Listener {

	private final int eventID;
	private final World world;
	private final Vector origin, size;
	private final String tablePvE, tablePvP;
    
	public EventStatsListener(JavaPlugin plugin, String eventName, int eventID) {
		super(plugin);
		this.eventID = eventID;
		this.world = new EventConfigurationStructure(plugin, eventName).getPasteLocation().getWorld();
		WorldEditLoad wel = new WorldEditLoad(plugin, eventName);
		this.origin = wel.getClipboard().getOrigin();
		this.size = wel.getClipboard().getSize();
		this.tablePvE = SQLSchema.TABLE_PVE_STATS + "_" + eventName;
		this.tablePvP = SQLSchema.TABLE_PVP_STATS + "_" + eventName;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (new SchedulerEventMap().get().containsKey(eventID)) {
			Entities eventEntities = new Entities(world, origin, size);
			Entity killer = event.getEntity().getKiller();
			Entity victim = event.getEntity();
			if (victim instanceof Player && eventEntities.getLivingEntities().contains(victim.getEntityId())) {
				if (killer instanceof Player && eventEntities.getLivingEntities().contains(killer.getEntityId())) {
					super.updateKillerStats(((Player) killer).getPlayerListName(), tablePvP);
					super.updateVictimStats(((Player) victim).getPlayerListName(), tablePvP);
				} else {
					super.updateVictimStats(((Player) victim).getPlayerListName(), tablePvE);
				}
			} else if (killer instanceof Player && eventEntities.getLivingEntities().contains(victim.getEntityId())) {
				super.updateKillerStats(((Player) killer).getPlayerListName(), tablePvE);

				BossEventMap bem = new BossEventMap();
				Integer entityID = victim.getEntityId();
				if(bem.get().containsKey(entityID)) {
					super.updateKillerBossKillsStat(((Player) killer).getPlayerListName(), tablePvE);
					bem.get().remove(entityID);
				}
			}

		}
	}
}
