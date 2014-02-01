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
				new SQLUpdateTotal(EventAdministrator.getConnection(), 
						dcs.getMySQLPrefix(), 
						event.getEntity().getKiller().getPlayerListName(), 
						SQLSchema.TABLE_PVP_STATS_TOTAL).setTotalData(SQLSchema.COLUMN_KILLS);

				// Add PvP death for player
				new SQLUpdateTotal(EventAdministrator.getConnection(), 
						dcs.getMySQLPrefix(), 
						((Player) event.getEntity()).getPlayerListName(), 
						SQLSchema.TABLE_PVP_STATS_TOTAL).setTotalData(SQLSchema.COLUMN_DEATHS);
			} else {
				Player player = (Player) event.getEntity();
				// Add PvE death for player
				new SQLUpdateTotal(EventAdministrator.getConnection(), 
						dcs.getMySQLPrefix(), 
						player.getPlayerListName(), 
						SQLSchema.TABLE_PVE_STATS_TOTAL).setTotalData(SQLSchema.COLUMN_DEATHS);
			}
		} else if (event.getEntity().getKiller() instanceof Player) {
			// Add PvE kill for player
			new SQLUpdateTotal(EventAdministrator.getConnection(), 
					dcs.getMySQLPrefix(), 
					event.getEntity().getKiller().getPlayerListName(), 
					SQLSchema.TABLE_PVE_STATS_TOTAL).setTotalData(SQLSchema.COLUMN_KILLS);
		}
	}
}
