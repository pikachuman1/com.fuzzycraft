package me.fuzzystatic.KitPvP;

import java.util.HashMap;

import me.fuzzystatic.KitPvP.KitPvP;
import me.fuzzystatic.KitPvP.kits.*;
import me.fuzzystatic.KitPvP.kits.acquired.*;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

	public KitPvP plugin;
	private PlayerKits playerKit = new PlayerKits();
	
	private long globalCooldown = 1050;
    private final HashMap<Player, Long> lastCast = new HashMap<Player, Long>();
	ItemStack smallFireballWeapon = new ItemStack(Material.BLAZE_ROD);
    
	public PlayerListener(KitPvP plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Fireball || event.getDamager() instanceof SmallFireball) {
			event.setDamage(event.getDamage() * 4);
		}
	} 
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerClick(PlayerInteractEvent event) {		
		Player player = event.getPlayer();
		
		if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			castSmallFireball(player);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRespawn(PlayerRespawnEvent event) {		
		Player player = event.getPlayer();
		
        reapplyKit(player);
	}

	public void castSmallFireball(Player player) {	
		Long lastCastTime = lastCast.get(player);
		
		if (lastCastTime != null && (lastCastTime + globalCooldown) < System.currentTimeMillis() && player.getItemInHand().equals(smallFireballWeapon)) {
			player.launchProjectile(SmallFireball.class);
			lastCast.put(player, System.currentTimeMillis()); 
		} else if(lastCastTime == null && player.getItemInHand().equals(smallFireballWeapon)) {
			player.launchProjectile(SmallFireball.class);
			lastCast.put(player, System.currentTimeMillis());		
		}
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