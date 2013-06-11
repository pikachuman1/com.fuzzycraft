package me.fuzzystatic.KitPvP;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class RemovePotionEffects {

	public void removeAll (Player player) {
	    player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    player.removePotionEffect(PotionEffectType.JUMP);
	    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
	    player.removePotionEffect(PotionEffectType.SLOW);
	    player.removePotionEffect(PotionEffectType.SPEED);
	    player.removePotionEffect(PotionEffectType.WATER_BREATHING);
	}
}
