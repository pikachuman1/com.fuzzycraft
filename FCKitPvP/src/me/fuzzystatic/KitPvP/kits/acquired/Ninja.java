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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ninja extends Kit implements CommandExecutor {

	public Ninja(KitPvP plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}
	
	private String kit = "ninja";
	
	private PlayerKits playerKit = new PlayerKits();
	
	private static Color color = Color.fromBGR(0, 0, 0); //black

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase(kit) && sender.hasPermission("KitPvP." + kit)) {
			Player player = (Player) sender;
		    player.sendMessage(ChatColor.DARK_AQUA + "[FuzzyCraft KitPvP] " + ChatColor.RED + "You are a flippin' Ninja!");
		    
		    setPotionEffects(player);
		    setInventory(player);
		    
		    playerKit.setKit(player.toString(), kit);
		}
		return false;
	}
	
	public static void setPotionEffects(Player player) {
		new RemovePotionEffects().removeAll(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000000, 1));
	    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 1));
	}
	
	public static void setInventory(Player player) {
		PlayerInventory inventory = player.getInventory();
	    inventory.clear();
	
	    ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 16);
	    ItemStack weapon1 = new ItemStack(Material.IRON_SWORD, 1);
	    ItemStack weapon2 = new ItemStack(Material.BOW, 1);
	    ItemStack potion = new ItemStack(Material.POTION, 3, (short)16420);
	    ItemStack food = new ItemStack(Material.MUSHROOM_SOUP, 1);
	    ItemStack arrows = new ItemStack(Material.ARROW, 64);

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
	    
    	weapon1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    weapon2.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
	    weapon2.addEnchantment(Enchantment.ARROW_INFINITE, 1);
	    
    	helm.addEnchantment(Enchantment.DURABILITY, 3);
    	chest.addEnchantment(Enchantment.DURABILITY, 3);
    	legs.addEnchantment(Enchantment.DURABILITY, 3);
    	boots.addEnchantment(Enchantment.DURABILITY, 3);
	    
    	helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    	
    	boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
	    
	    inventory.addItem(enderpearl);
	    inventory.addItem(weapon1);
	    inventory.addItem(potion);
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
	    inventory.addItem(weapon2);
	    inventory.addItem(arrows);
	    
	    inventory.setBoots(boots);
	    inventory.setHelmet(helm);
	    inventory.setChestplate(chest);
	    inventory.setLeggings(legs);
	}
}
