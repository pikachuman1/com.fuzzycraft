package me.fuzzystatic.KitPvP.kits;

import me.fuzzystatic.KitPvP.Kit;
import me.fuzzystatic.KitPvP.KitPvP;
import me.fuzzystatic.KitPvP.PlayerKits;
import me.fuzzystatic.KitPvP.RemovePotionEffects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Streaker extends Kit implements CommandExecutor {

	public Streaker(KitPvP plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}
	
	private String kit = "streaker";
	
	private PlayerKits playerKit = new PlayerKits();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase(kit) && sender.hasPermission("KitPvP." + kit)) {
			Player player = (Player) sender; 
		    player.sendMessage(ChatColor.DARK_AQUA + "[FuzzyCraft KitPvP] " + ChatColor.RED + "You are either courageous or foolish!");
		    
		    setPotionEffects(player);
		    setInventory(player);
		    
		    playerKit.setKit(player.toString(), kit);
		}
		return false;
	}
	
	public static void setPotionEffects(Player player) {
		new RemovePotionEffects().removeAll(player);
	    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 4));
	}
	
	public static void setInventory(Player player) {	
		PlayerInventory inventory = player.getInventory();
	    inventory.clear();
	    
	    ItemStack air = new ItemStack(Material.AIR, 1);

	    inventory.setBoots(air);
	    inventory.setHelmet(air);
	    inventory.setChestplate(air);
	    inventory.setLeggings(air);
	}
}