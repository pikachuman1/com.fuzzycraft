package me.fuzzystatic.KitPvP.kits.acquired;

import me.fuzzystatic.KitPvP.Kit;
import me.fuzzystatic.KitPvP.KitPvP;
import me.fuzzystatic.KitPvP.PlayerKits;
import me.fuzzystatic.KitPvP.RemovePotionEffects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class FireOmen extends Kit implements CommandExecutor {

	public FireOmen(KitPvP plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}
	
	private String kit = "fireomen";
	
	private PlayerKits playerKit = new PlayerKits();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if((commandLabel.equalsIgnoreCase(kit) || commandLabel.equalsIgnoreCase("fo")) && sender.hasPermission("KitPvP." + kit)) {
			Player player = (Player) sender;
		    player.sendMessage(ChatColor.DARK_AQUA + "[FuzzyCraft KitPvP] " + ChatColor.RED + "You are a arsonistic Fire Omen!");
	    	
		    setPotionEffects(player);
		    setInventory(player);
		    
		    playerKit.setKit(player.toString(), kit);
		}
		return false;
	}
	
	public static void setPotionEffects(Player player) {
		new RemovePotionEffects().removeAll(player);
	}
	
	public static void setInventory(Player player) {
		PlayerInventory inventory = player.getInventory();
	    inventory.clear();
	    		    
	    ItemStack weapon = new ItemStack(Material.IRON_SWORD, 1);
	    ItemStack food = new ItemStack(Material.MUSHROOM_SOUP, 1);
	   
    	ItemStack helm = new ItemStack(Material.IRON_HELMET, 1);
    	ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
    	ItemStack legs = new ItemStack(Material.IRON_LEGGINGS, 1);
	    ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);

	    weapon.addEnchantment(Enchantment.FIRE_ASPECT, 2);
    	
    	helm.addEnchantment(Enchantment.PROTECTION_FIRE, 1);
    	chest.addEnchantment(Enchantment.PROTECTION_FIRE, 1);
    	legs.addEnchantment(Enchantment.PROTECTION_FIRE, 1);
    	boots.addEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    
	    inventory.addItem(weapon);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    inventory.addItem(food);
	    
	    inventory.setBoots(boots);
	    inventory.setHelmet(helm);
	    inventory.setChestplate(chest);
	    inventory.setLeggings(legs);
	}
}