package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.BossEventMap;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdatePlayer;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdateTotal;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StatsListener implements Listener {

	protected final String prefix;
    
	public StatsListener(JavaPlugin plugin) {
		this.prefix = new DefaultConfigurationStructure(plugin).getMySQLPrefix();
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		new SQLUpdatePlayer(EventAdministrator.getConnection(), prefix, event.getPlayer().getPlayerListName()).setPlayerData();
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity killer = event.getEntity().getKiller();
		Entity victim = event.getEntity();
		String tablePvE = SQLSchema.TABLE_PVE_STATS_TOTAL;
		String tablePvP = SQLSchema.TABLE_PVP_STATS_TOTAL;
		if (victim instanceof Player) {
			if (killer instanceof Player) {
				updateKillerStats(((Player) killer).getPlayerListName(), tablePvP);
				updateVictimStats(((Player) victim).getPlayerListName(), tablePvP);
			} else {
				updateVictimStats(((Player) victim).getPlayerListName(), tablePvE);
			}
		} else if (event.getEntity().getKiller() instanceof Player) {
			updateKillerStats(((Player) killer).getPlayerListName(), tablePvE);

			BossEventMap bem = new BossEventMap();
			Integer entityId = event.getEntity().getEntityId();
			if(bem.get().containsKey(entityId)) {
				updateKillerBossKillsStat(((Player) killer).getPlayerListName(), tablePvE);
			}
		}
	}
	
	public void updateKillerStats(String player, String table) {
		SQLUpdateTotal killerUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), prefix, player, table);
		killerUpdate.setTotalData(SQLSchema.COLUMN_KILLS, "1", true);
		if(killerUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "1", true)) killerUpdate.setMaxStreakTotalData();
	}
	
	public void updateKillerBossKillsStat(String player, String table) {
		SQLUpdateTotal killerUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), prefix, player, table);
		killerUpdate.setTotalData(SQLSchema.COLUMN_BOSSKILLS, "1", true);
	}
	
	public void updateVictimStats(String player, String table) {
		SQLUpdateTotal victimUpdate = new SQLUpdateTotal(EventAdministrator.getConnection(), prefix, player, table);
		victimUpdate.setTotalData(SQLSchema.COLUMN_DEATHS, "1", true);
		victimUpdate.setTotalData(SQLSchema.COLUMN_STREAK, "0", false);
	}
}
