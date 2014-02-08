package me.fuzzystatic.EventAdministrator.configurations;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;

public class PlayerItemsConfigurationStructure extends EventConfigurationStructure implements FileStructure {

	public static final String PLAYER_ITEMS 		= "playerItems";
	public static final String HELM 				= PLAYER_ITEMS + "." + "helm";
	public static final String CHEST 				= PLAYER_ITEMS + "." + "chest";
	public static final String LEGS 				= PLAYER_ITEMS + "." + "legs";
	public static final String BOOTS 				= PLAYER_ITEMS + "." + "boots";
	public static final String INVENTORY 			= PLAYER_ITEMS + "." + "inventory";
	public static final String START_TIME 			= PLAYER_ITEMS + "." + "startTime";
	public static final String CYCLE_TIME 			= PLAYER_ITEMS + "." + "cycleTime";
	
	private static final ItemStack bow = new ItemStack(Material.BOW, 1);
	private static final ItemStack arrows = new ItemStack(Material.ARROW, 64);
	private static final ItemStack food = new ItemStack(Material.BREAD, 6);
	
	private static final List<ItemStack> defaultInventory = new ArrayList<ItemStack>();
	
	private static final long defaultStartTime 		= 30;
	private static final long defaultCycleTime 		= 0;
	
	public PlayerItemsConfigurationStructure(JavaPlugin plugin, String eventName) {
		super(plugin, eventName);
		defaultInventory.add(bow);
		defaultInventory.add(arrows);
		defaultInventory.add(food);
	}
	
	public void setHelm(ItemStack helm) {
		this.config.set(HELM, helm);
		this.configAccessor.saveConfig();
	}
	
	public void setChest(ItemStack chest) {
		this.config.set(CHEST, chest);
		this.configAccessor.saveConfig();
	}
	
	public void setLegs(ItemStack legs) {
		this.config.set(LEGS, legs);
		this.configAccessor.saveConfig();
	}
	
	public void setBoots(ItemStack boots) {
		this.config.set(BOOTS, boots);
		this.configAccessor.saveConfig();
	}
	
	public void setInventory(List<ItemStack> inventory) {
		this.config.set(INVENTORY, inventory);
		this.configAccessor.saveConfig();
	}
	
	public void setStartTime(long startTime) {
		this.config.set(START_TIME, startTime);
		this.configAccessor.saveConfig();
	}
	
	@Override
	public void setCycleTime(long cycleTime) {
		this.config.set(CYCLE_TIME, cycleTime);
		this.configAccessor.saveConfig();
	}

	@Override
	public boolean createFileStructure() {
		boolean configAltered = false;
		if(this.config.get(HELM) == null) {
			setHelm(null);
			configAltered = true;
		}
		if(this.config.get(CHEST) == null) {
			setChest(null);
			configAltered = true;
		}
		if(this.config.get(LEGS) == null) {
			setLegs(null);
			configAltered = true;
		}
		if(this.config.get(BOOTS) == null) {
			setBoots(null);
			configAltered = true;
		}
		if(this.config.get(INVENTORY) == null) {
			setInventory(defaultInventory);
			configAltered = true;
		}
		if(this.config.get(START_TIME) == null) {
			setStartTime(defaultStartTime);
			configAltered = true;
		}
		if(this.config.get(CYCLE_TIME) == null) {
			setCycleTime(defaultCycleTime);
			configAltered = true;
		}
		return configAltered;
	}
	
	public ItemStack getHelm() {
		return config.getItemStack(HELM);
	}
	
	public ItemStack getChest() {
		return config.getItemStack(CHEST);
	}
	
	public ItemStack getLegs() {
		return config.getItemStack(LEGS);
	}
	
	public ItemStack getBoots() {
		return config.getItemStack(BOOTS);
	}
	
	public List<?> getInventory() {
		return config.getList(INVENTORY);
	}
	
	public long getStartTime() {
		return config.getLong(START_TIME);
	}
	
	public long getCycleTime() {
		return config.getLong(CYCLE_TIME);
	}
}
