package me.fuzzystatic.EventAdministrator.listeners;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.DefaultConfigurationStructure;
import me.fuzzystatic.EventAdministrator.sql.SQLSchema;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdatePlayer;
import me.fuzzystatic.EventAdministrator.sql.SQLUpdateTotal;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Entity killer = event.getEntity().getKiller();
		if (killer instanceof Player) {
			String table = SQLSchema.TABLE_PVP_STATS_TOTAL;
			
			// Add PvP kill for killer
	    	new SQLUpdateTotal(EventAdministrator.getConnection(), 
	    			dcs.getMySQLPrefix(), 
	    			((Player) killer).getPlayerListName(), 
	    			table).setTotalData(SQLSchema.COLUMN_KILLS);
	    	
	    	// Add PvP death for player
	    	new SQLUpdateTotal(EventAdministrator.getConnection(), 
	    			dcs.getMySQLPrefix(), 
	    			player.getPlayerListName(), 
	    			table).setTotalData(SQLSchema.COLUMN_DEATHS);
		} else {
			String table = SQLSchema.TABLE_PVE_STATS_TOTAL;
			
			// Add PvE death for player
	    	new SQLUpdateTotal(EventAdministrator.getConnection(), 
	    			dcs.getMySQLPrefix(), 
	    			player.getPlayerListName(), 
	    			table).setTotalData(SQLSchema.COLUMN_DEATHS);
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
	
	}
}
