package me.fuzzystatic.KitPvP;

import me.fuzzystatic.KitPvP.KitPvP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Kit implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private KitPvP plugin;
	public Kit(KitPvP plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void setPotionEffects(Player player) {
		new RemovePotionEffects().removeAll(player);
	}
	
	public static void setInventory(Player player) {
		PlayerInventory inventory = player.getInventory();
		inventory.clear();
	}
}