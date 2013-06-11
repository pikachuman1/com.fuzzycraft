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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class King extends Kit implements CommandExecutor {

	public King(KitPvP plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}
	
	private String kit = "king";
	
	private PlayerKits playerKit = new PlayerKits();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase(kit) && sender.hasPermission("KitPvP." + kit)) {
			Player player = (Player) sender;
		    player.sendMessage(ChatColor.DARK_AQUA + "[FuzzyCraft KitPvP] " + ChatColor.RED + "You are a mighty King!");
	    	
		    setPotionEffects(player);
		    setInventory(player);
		    
		    playerKit.setKit(player.toString(), kit);
		}
		return false;
	}
	
	public static void setPotionEffects(Player player) {
		new RemovePotionEffects().removeAll(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000000, 1));
	    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000000, 4));
	}
	
	public static void setInventory(Player player) {
		PlayerInventory inventory = player.getInventory();
	    inventory.clear();
    
	    ItemStack weapon = new ItemStack(Material.STONE_SWORD, 1);
	    ItemStack food = new ItemStack(Material.MUSHROOM_SOUP, 1);
	   
    	ItemStack helm = new ItemStack(Material.GOLD_HELMET, 1);
    	ItemStack chest = new ItemStack(Material.GOLD_CHESTPLATE, 1);
    	ItemStack legs = new ItemStack(Material.GOLD_LEGGINGS, 1);
	    ItemStack boots = new ItemStack(Material.GOLD_BOOTS, 1);

	    weapon.addEnchantment(Enchantment.DURABILITY, 3);
	    
    	helm.addEnchantment(Enchantment.DURABILITY, 3);
    	chest.addEnchantment(Enchantment.DURABILITY, 3);
    	legs.addEnchantment(Enchantment.DURABILITY, 3);
    	boots.addEnchantment(Enchantment.DURABILITY, 3);
	    
    	helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    	chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    	legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    	boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    	
    	helm.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
    	chest.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
    	legs.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
    	boots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
    	
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