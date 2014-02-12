package me.fuzzystatic.EventAdministrator.configuration.structure;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;

public class PlayerItemsConfigurationStructure extends EventConfigurationStructure implements FileStructure {

	public static final String PLAYER_ITEMS 		= "playerItems";
	public static final String HELMET 				= PLAYER_ITEMS + "." + "helmet";
	public static final String CHESTPLATE 			= PLAYER_ITEMS + "." + "chestplate";
	public static final String LEGGINGS 			= PLAYER_ITEMS + "." + "leggings";
	public static final String BOOTS 				= PLAYER_ITEMS + "." + "boots";
	public static final String INVENTORY 			= PLAYER_ITEMS + "." + "inventory";
	public static final String INVENTORY_ITEMS 		= INVENTORY + "." + "items";
	public static final String INVENTORY_START_TIME = INVENTORY + "." + "startTime";
	public static final String INVENTORY_CYCLE_TIME = INVENTORY + "." + "cycleTime";
		
	private static final long defaultStartTime 		= 30;
	private static final long defaultCycleTime 		= 0;
		
	public PlayerItemsConfigurationStructure(JavaPlugin plugin, String eventName) {
		super(plugin, eventName);
	}
	
	public void setItem(StringBuilder stringBuilder, String key) {
		this.config.set(key, stringBuilder);
		this.configAccessor.saveConfig();
	}
	public void setInventoryItems(List<String> itemList) {
		this.config.set(INVENTORY_ITEMS, itemList);
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
		if(this.config.get(INVENTORY_START_TIME) == null) {
			setInventoryStartTime(defaultStartTime);
			configAltered = true;
		}
		if(this.config.get(INVENTORY_CYCLE_TIME) == null) {
			setInventoryCycleTime(defaultCycleTime);
			configAltered = true;
		}
		if(this.config.get(INVENTORY_ITEMS) == null) {
			setInventoryItems(null);
			configAltered = true;
		}
		return configAltered;
	}
	
	public boolean hasItem(String key) {
		if (config.getString(key) != null) return true;
		return false;
	}
	
	public String getItem(String key) {
		return config.getString(key);
	}
	
	public List<?> getInventoryItems() {
		return config.getList(INVENTORY_ITEMS);
	}
	
	public long getInventoryStartTime() {
		return config.getLong(INVENTORY_START_TIME);
	}
	
	public long getInventoryCycleTime() {
		return config.getLong(INVENTORY_CYCLE_TIME);
	}
}
