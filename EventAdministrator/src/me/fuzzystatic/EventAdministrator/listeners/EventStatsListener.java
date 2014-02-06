package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;
import me.fuzzystatic.EventAdministrator.maps.BossEventMap;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdatePlayer;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdateTotal;
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

	private final String eventName;
	private final World world;
	private final Vector origin;
	private final Vector size;
    
	public EventStatsListener(JavaPlugin plugin, String eventName) {
		super(plugin);
		this.eventName = eventName;
		this.world = new EventConfigurationStructure(plugin, eventName).getPasteLocation().getWorld();
		this.origin = new WorldEditLoad(plugin, eventName).getClipboard().getOrigin();
		this.size = new WorldEditLoad(plugin, eventName).getClipboard().getOrigin();
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entities eventEntities = new Entities(world, origin, size);
		Entity killer = event.getEntity().getKiller();
		Entity victim = event.getEntity();
		if (eventEntities.getEntities().contains(killer) && eventEntities.getEntities().contains(victim)) {
			if (victim instanceof Player) {
				if (killer instanceof Player) {
					super.updateKillerStats(((Player) killer).getPlayerListName(), SQLSchema.TABLE_PVP_STATS_TOTAL + "_" + eventName);
					super.updateVictimStats(((Player) victim).getPlayerListName(), SQLSchema.TABLE_PVP_STATS_TOTAL + "_" + eventName);
				} else {
					super.updateVictimStats(((Player) victim).getPlayerListName(), SQLSchema.TABLE_PVE_STATS_TOTAL + "_" + eventName);
				}
			} else if (event.getEntity().getKiller() instanceof Player) {
				super.updateKillerStats(((Player) killer).getPlayerListName(), SQLSchema.TABLE_PVE_STATS_TOTAL + "_" + eventName);

				BossEventMap bem = new BossEventMap();
				Integer entityId = event.getEntity().getEntityId();
				if(bem.get().containsKey(entityId)) {
					super.updateKillerBossKillsStat(((Player) killer).getPlayerListName(), SQLSchema.TABLE_PVE_STATS_TOTAL + "_" + eventName);
					bem.get().remove(entityId);
				}
			}
		}
	}
}
