package me.fuzzystatic.KitPvP.kits.acquired;

import me.fuzzystatic.KitPvP.Kit;
import me.fuzzystatic.KitPvP.KitPvP;
import me.fuzzystatic.KitPvP.PlayerKits;
import me.fuzzystatic.KitPvP.RemovePotionEffects;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class FireMage extends Kit implements CommandExecutor {

	public FireMage(KitPvP plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}
	
	private String kit = "firemage";
	
	private PlayerKits playerKit = new PlayerKits();
	
	private static Color color = Color.fromBGR(0, 0, 150); //dark red

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if((commandLabel.equalsIgnoreCase(kit) || commandLabel.equalsIgnoreCase("fm")) && sender.hasPermission("KitPvP." + kit)) {
			Player player = (Player) sender;
		    player.sendMessage(ChatColor.DARK_AQUA + "[FuzzyCraft KitPvP] " + ChatColor.RED + "You are a burning Fire Mage!");
		    
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
	    
	    ItemStack weapon = new ItemStack(Material.BLAZE_ROD, 1);
	    ItemStack food = new ItemStack(Material.MUSHROOM_SOUP, 1);

    	ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
	    LeatherArmorMeta helmColor = (LeatherArmorMeta)helm.getItemMeta();
	    helmColor.setColor(color); // Set color
	    helm.setItemMeta(helmColor); // Give the armor the actual color we made
    	
    	ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	    LeatherArmorMeta chestColor = (LeatherArmorMeta)chest.getItemMeta();
	    chestColor.setColor(color); // Set color
	    chest.setItemMeta(chestColor); // Give the armor the actual color we made
	    
    	ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
	    LeatherArmorMeta legsColor = (LeatherArmorMeta)legs.getItemMeta();
	    legsColor.setColor(color); // Set color
	    legs.setItemMeta(legsColor); // Give the armor the actual color we made
	    
	    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
	    LeatherArmorMeta bootColor = (LeatherArmorMeta)boots.getItemMeta();
	    bootColor.setColor(color); // Set color
    	boots.setItemMeta(bootColor); // Give the armor the actual color we made
	    		    
    	helm.addEnchantment(Enchantment.DURABILITY, 3);
    	chest.addEnchantment(Enchantment.DURABILITY, 3);
    	legs.addEnchantment(Enchantment.DURABILITY, 3);
    	boots.addEnchantment(Enchantment.DURABILITY, 3);
	    
    	helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	
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