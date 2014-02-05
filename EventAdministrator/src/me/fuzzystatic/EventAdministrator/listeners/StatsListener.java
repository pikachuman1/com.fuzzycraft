package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.BossEventMap;
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
		new SQLUpdatePlayer(EventAdministrator.getConnection(), dcs.getMySQLPrefix(), event.getPlayer().getPlayerListName()).setPlayerData();
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			if (event.getEntity().getKiller() instanceof Player) {
				updateKillerTotals(event.getEntity().getKiller().getPlayerListName(), SQLSchema.TABLE_PVP_STATS_TOTAL);
				updateVictimTotals(((Player) event.getEntity()).getPlayerListName(), SQLSchema.TABLE_PVP_STATS_TOTAL);
			} else {
				updateVictimTotals(((Player) event.getEntity()).getPlayerListName(), SQLSchema.TABLE_PVE_STATS_TOTAL);
			}
		} else if (event.getEntity().getKiller() instanceof Player) {
			updateKillerTotals(event.getEntity().getKiller().getPlayerListName(), SQLSchema.TABLE_PVE_STATS_TOTAL);

			BossEventMap bem = new BossEventMap();
			Integer entityId = event.getEntity().getEntityId();
			if(bem.get().containsKey(entityId)) {
				updateKillerBossKills(event.getEntity().getKiller().getPlayerListName(), SQLSchema.TABLE_PVE_STATS_TOTAL);
				bem.get().remove(entityId);
			}
		}
	}
	
	public void updateKillerTotals(String player, String table) {
		SQLUpdateTotal killerUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), dcs.getMySQLPrefix(), player, table);
		killerUpdate.setTotalData(SQLSchema.COLUMN_KILLS, "1", true);
		if(killerUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "1", true)) killerUpdate.setMaxStreakTotalData();
	}
	
	public void updateKillerBossKills(String player, String table) {
		SQLUpdateTotal killerUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), dcs.getMySQLPrefix(), player, table);
		killerUpdate.setTotalData(SQLSchema.COLUMN_BOSSKILLS, "1", true);
	}
	
	public void updateVictimTotals(String player, String table) {
		SQLUpdateTotal victimUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), dcs.getMySQLPrefix(), player, table);
		victimUpdate.setTotalData(SQLSchema.COLUMN_DEATHS, "1", true);
		victimUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "0", false);
	}
}
