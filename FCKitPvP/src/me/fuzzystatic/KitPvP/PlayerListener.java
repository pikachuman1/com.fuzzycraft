package me.fuzzystatic.KitPvP;

import me.fuzzystatic.KitPvP.KitPvP;
import me.fuzzystatic.KitPvP.kits.*;
import me.fuzzystatic.KitPvP.kits.acquired.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener {

	public KitPvP plugin;
    
	public PlayerListener(KitPvP plugin) {
		this.plugin = plugin;
	}
	
	private PlayerKits playerKit = new PlayerKits();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRespawn(PlayerRespawnEvent event) {		
		Player player = event.getPlayer();
		
        reapplyKit(player);
	}
	
	public void reapplyKit(final Player player) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
        		if(!(playerKit.getKit(player.toString()) == null)) {
        			switch(playerKit.getKit(player.toString())) {
        				case "squire": Squire.setPotionEffects(player); Squire.setInventory(player); break;
        				case "archer": Archer.setPotionEffects(player); Archer.setInventory(player); break;
        				case "streaker": Streaker.setPotionEffects(player); Streaker.setInventory(player); break;
        				case "knight": Knight.setPotionEffects(player); Knight.setInventory(player); break;
        				case "chemist": Chemist.setPotionEffects(player); Chemist.setInventory(player); break;
        				case "lumberjack": Lumberjack.setPotionEffects(player); Lumberjack.setInventory(player); break;
        				case "ninja": Ninja.setPotionEffects(player); Ninja.setInventory(player); break;
        				case "mystic": Mystic.setPotionEffects(player); Mystic.setInventory(player); break;
        				case "king": King.setPotionEffects(player); King.setInventory(player); break;
        				case "firemage": FireMage.setPotionEffects(player); FireMage.setInventory(player); break;
        				case "reaper": Reaper.setPotionEffects(player); Reaper.setInventory(player); break;
        				case "fireomen": FireOmen.setPotionEffects(player); FireOmen.setInventory(player); break;
        				case "monk": Monk.setPotionEffects(player); Monk.setInventory(player); break;
        			}
        		}
            }
		}, 1);
	}
}