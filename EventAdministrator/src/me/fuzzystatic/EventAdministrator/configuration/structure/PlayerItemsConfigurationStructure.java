package me.fuzzystatic.EventAdministrator.configuration.structure;

import java.util.List;
import java.util.Map;

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
		
	private static final long defaultStartTime 		= 30;
	private static final long defaultCycleTime 		= 0;
		
	public PlayerItemsConfigurationStructure(JavaPlugin plugin, String eventName) {
		super(plugin, eventName);
	}
	
	public void setHelm(Map<String, Object> map) {
		this.config.set(HELM, map);
		this.configAccessor.saveConfig();
	}
	
	public void setChest(Map<String, Object> map) {
		this.config.set(CHEST, map);
		this.configAccessor.saveConfig();
	}
	
	public void setLegs(Map<String, Object> map) {
		this.config.set(LEGS, map);
		this.configAccessor.saveConfig();
	}
	
	public void setBoots(Map<String, Object> map) {
		this.config.set(BOOTS, map);
		this.configAccessor.saveConfig();
	}
	
	public void setInventory(Map<String, Object> map) {
		this.config.set(INVENTORY, map);
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
