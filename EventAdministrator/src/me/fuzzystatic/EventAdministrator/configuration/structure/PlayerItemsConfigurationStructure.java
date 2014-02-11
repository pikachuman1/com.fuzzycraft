package me.fuzzystatic.EventAdministrator.configuration.structure;

import java.util.List;
import java.util.Map;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;

public class PlayerItemsConfigurationStructure extends EventConfigurationStructure implements FileStructure {

	public static final String PLAYER_ITEMS 		= "playerItems";
	public static final String HELMET 				= PLAYER_ITEMS + "." + "helmet";
	public static final String CHESTPLATE 			= PLAYER_ITEMS + "." + "chestplate";
	public static final String LEGGINGS 			= PLAYER_ITEMS + "." + "leggings";
	public static final String BOOTS 				= PLAYER_ITEMS + "." + "boots";
	public static final String INVENTORY 			= PLAYER_ITEMS + "." + "inventory";
	public static final String INVENTORY_START_TIME = PLAYER_ITEMS + "." + INVENTORY + "." + "startTime";
	public static final String INVENTORY_CYCLE_TIME = PLAYER_ITEMS + "." + INVENTORY + "." + "cycleTime";
		
	private static final long defaultStartTime 		= 30;
	private static final long defaultCycleTime 		= 0;
		
	public PlayerItemsConfigurationStructure(JavaPlugin plugin, String eventName) {
		super(plugin, eventName);
	}
	
	public void setHelmet(String string) {
		this.config.set(HELMET, string);
		this.configAccessor.saveConfig();
	}
	
	public void setChestplate(String string) {
		this.config.set(CHESTPLATE, string);
		this.configAccessor.saveConfig();
	}
	
	public void setLeggings(String string) {
		this.config.set(LEGGINGS, string);
		this.configAccessor.saveConfig();
	}
	
	public void setBoots(String string) {
		this.config.set(BOOTS, string);
		this.configAccessor.saveConfig();
	}
	
	public void setInventory(Map<String, Object> map) {
		this.config.set(INVENTORY, map);
		this.configAccessor.saveConfig();
	}
	
	public void setInventoryStartTime(long startTime) {
		this.config.set(INVENTORY_START_TIME, startTime);
		this.configAccessor.saveConfig();
	}
	
	public void setInventoryCycleTime(long cycleTime) {
		this.config.set(INVENTORY_CYCLE_TIME, cycleTime);
		this.configAccessor.saveConfig();
	}

	@Override
	public boolean createFileStructure() {
		boolean configAltered = false;
		if(this.config.get(HELMET) == null) {
			setHelmet(null);
			configAltered = true;
		}
		if(this.config.get(CHESTPLATE) == null) {
			setChestplate(null);
			configAltered = true;
		}
		if(this.config.get(LEGGINGS) == null) {
			setLeggings(null);
			configAltered = true;
		}
		if(this.config.get(BOOTS) == null) {
			setBoots(null);
			configAltered = true;
		}
		if(this.config.get(INVENTORY) == null) {
			setInventory(null);
			configAltered = true;
		}
		if(this.config.get(INVENTORY_START_TIME) == null) {
			setInventoryStartTime(defaultStartTime);
			configAltered = true;
		}
		if(this.config.get(INVENTORY_CYCLE_TIME) == null) {
			setInventoryCycleTime(defaultCycleTime);
			configAltered = true;
		}
		return configAltered;
	}
	
	public String getHelmet() {
		return config.getString(HELMET);
	}
	
	public String getChestplate() {
		return config.getString(CHESTPLATE);
	}
	
	public String getLeggings() {
		return config.getString(LEGGINGS);
	}
	
	public String getBoots() {
		return config.getString(BOOTS);
	}
	
	public List<?> getInventory() {
		return config.getList(INVENTORY);
	}
	
	public long getInventoryStartTime() {
		return config.getLong(INVENTORY_START_TIME);
	}
	
	public long getInventoryCycleTime() {
		return config.getLong(INVENTORY_CYCLE_TIME);
	}
}
