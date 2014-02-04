package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdatePlayer;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdateTotal;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StatsListener implements Listener {

	private final DefaultConfigurationStructure dcs;
    
	public StatsListener(JavaPlugin plugin) {
		this.dcs = new DefaultConfigurationStructure(plugin);
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		new SQLUpdatePlayer(EventAdministrator.getConnection(), dcs.getMySQLPrefix(), player.getPlayerListName()).setPlayerData();
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			if (event.getEntity().getKiller() instanceof Player) {
				// Add PvP kill for killer
				SQLUpdateTotal killerUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), 
						dcs.getMySQLPrefix(), 
						event.getEntity().getKiller().getPlayerListName(), 
						SQLSchema.TABLE_PVP_STATS_TOTAL);
				killerUpdate.setTotalData(SQLSchema.COLUMN_KILLS, "1", true);
				if(killerUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "1", true)) killerUpdate.setMaxStreakTotalData();
				
				// Add PvP death for player
				SQLUpdateTotal victumUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), 
						dcs.getMySQLPrefix(), 
						((Player) event.getEntity()).getPlayerListName(), 
						SQLSchema.TABLE_PVP_STATS_TOTAL);
				victumUpdate.setTotalData(SQLSchema.COLUMN_DEATHS, "1", true);
				victumUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "0", false);

			} else {
				// Add PvE death for player
				SQLUpdateTotal victumUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), 
						dcs.getMySQLPrefix(), 
						((Player) event.getEntity()).getPlayerListName(), 
						SQLSchema.TABLE_PVE_STATS_TOTAL);
				victumUpdate.setTotalData(SQLSchema.COLUMN_DEATHS, "1", true);
				victumUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "0", false);
			}
		} else if (event.getEntity().getKiller() instanceof Player) {
			// Add PvE kill for player
			SQLUpdateTotal killerUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), 
					dcs.getMySQLPrefix(), 
					event.getEntity().getKiller().getPlayerListName(), 
					SQLSchema.TABLE_PVE_STATS_TOTAL);
			killerUpdate.setTotalData(SQLSchema.COLUMN_KILLS, "1", true);
			if(killerUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "1", true)) killerUpdate.setMaxStreakTotalData();
		}
	}
}
