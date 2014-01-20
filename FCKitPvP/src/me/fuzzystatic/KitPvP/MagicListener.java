package me.fuzzystatic.KitPvP;

import java.util.HashMap;

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
import org.bukkit.inventory.ItemStack;

public class MagicListener implements Listener {
	
	public KitPvP plugin;
	
	public MagicListener(KitPvP plugin) {
		this.plugin = plugin;
	}
	
	private long globalCooldown = 1050;
    private final HashMap<Player, Long> lastCast = new HashMap<Player, Long>();
	ItemStack smallFireballWeapon = new ItemStack(Material.BLAZE_ROD);
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerClick(PlayerInteractEvent event) {		
		Player player = event.getPlayer();
		
		if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			castSmallFireball(player);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Fireball || event.getDamager() instanceof SmallFireball) {
			event.setDamage((event.getDamage() * 3) + 1);
		}
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
}
