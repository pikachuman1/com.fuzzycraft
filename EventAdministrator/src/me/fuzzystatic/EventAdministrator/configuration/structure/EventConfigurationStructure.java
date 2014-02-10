package me.fuzzystatic.EventAdministrator.configuration.structure;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.configuration.ConfigAccessor;
import me.fuzzystatic.EventAdministrator.configuration.SerializableLocation;
import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;
import me.fuzzystatic.EventAdministrator.utilities.YMLLocation;

public class EventConfigurationStructure implements FileStructure {
	
	public static final String PASTE_LOCATION 						= "pasteLocation";
	public static final String ENTRANCE 							= "entrance";
	public static final String EXIT 								= "exit";
	public static final String CREATURE_LIMIT 						= "creatureLimit";
	public static final String CYCLE_TIME 							= "cycleTime";
	public static final String NO_AIR 								= "noAir";
	public static final String WORLD_CONDITIONS 					= "worldConditions";
	public static final String WORLD_CONDITIONS_TIME 				= WORLD_CONDITIONS + "." + "time";
	public static final String WORLD_CONDITIONS_TIME_CYCLE_TIME 	= WORLD_CONDITIONS + "." + "timeCycleTime";
	public static final String START_MESSAGE 						= "startMessage";
	public static final String REMINDER 							= "reminder";
	public static final String REMINDER_MESSAGE 					= REMINDER + "." + "message";
	public static final String REMINDER_CYCLE_TIME 					= REMINDER + '.' + "cycleTime";
	public static final String AUTO_START 							= "autoStart";
	
	private static final int defaultCreatureLimit 					= 201;
	private static final long defaultCycle 							= 14400;
	private static final boolean defaultNoAir 						= false;
	private static final long defaultWorldConditionsTime 			= 18000;
	private static final long defaultWorldConditionsTimeCycleTime 	= 60;
	private static final String defaultStartMessage 				= "";
	private static final String defaultReminderMessage 				= "";
	private static final long defaultReminderCycleTime 				= -1;
	private static final boolean defaultAutoStart 					= false;
	
	protected final ConfigAccessor configAccessor;
	protected final FileConfiguration config;
	
	public EventConfigurationStructure(JavaPlugin plugin, String eventName) {
		if (!plugin.isInitialized()) throw new IllegalArgumentException("Plugin must be initialized!");
		DirectoryStructure ds = new DirectoryStructure(plugin);
		this.configAccessor = new ConfigAccessor(plugin, ds.getEventsDirPath() + File.separator + eventName + ".yml");
		this.config = configAccessor.getConfig();
	}
	
	public void setPasteLocation(Map<String, Object> map) {
		new YMLLocation().setLocation(map, this.config, PASTE_LOCATION);
		this.configAccessor.saveConfig();
	}
	
	public void setEntrance(Map<String, Object> map) {
		new YMLLocation().setLocation(map, this.config, ENTRANCE);
		this.configAccessor.saveConfig();
	}
	
	public void setExit(Map<String, Object> map) {
		new YMLLocation().setLocation(map, this.config, EXIT);
		this.configAccessor.saveConfig();
	}
	
	public void setCreatureLimit(int creatureLimit) {
		this.config.set(CREATURE_LIMIT, creatureLimit);
		this.configAccessor.saveConfig();
	}
	
	public void setCycleTime(long seconds) {
		this.config.set(CYCLE_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setNoAir(boolean noAir) {
		this.config.set(NO_AIR, noAir);
		this.configAccessor.saveConfig();
	}
	
	public void setWorldConditionsTime(long seconds) {
		this.config.set(WORLD_CONDITIONS_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setWorldConditionsTimeCycleTime(long seconds) {
		this.config.set(WORLD_CONDITIONS_TIME_CYCLE_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setStartMessage(String message) {
		this.config.set(START_MESSAGE, message);
		this.configAccessor.saveConfig();
	}
	
	public void setReminderMessage(String message) {
		this.config.set(REMINDER_MESSAGE, message);
		this.configAccessor.saveConfig();
	}
	
	public void setReminderCycleTime(long seconds) {
		this.config.set(REMINDER_CYCLE_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setAutoStart(boolean autoStart) {
		this.config.set(AUTO_START, autoStart);
		this.configAccessor.saveConfig();
	}
	
	@Override
	public boolean createFileStructure() {
		boolean configAltered = false;
		if(this.config.get(CREATURE_LIMIT) == null) {
			setCreatureLimit(defaultCreatureLimit);
			configAltered = true;
		}
		if(this.config.get(CYCLE_TIME) == null) {
			setCycleTime(defaultCycle);
			configAltered = true;
		}
		if(this.config.get(NO_AIR) == null) {
			setNoAir(defaultNoAir);
			configAltered = true;
		}
		if(this.config.get(WORLD_CONDITIONS_TIME) == null) {
			setWorldConditionsTime(defaultWorldConditionsTime);
			configAltered = true;
		}
		if(this.config.get(WORLD_CONDITIONS_TIME_CYCLE_TIME) == null) {
			setWorldConditionsTimeCycleTime(defaultWorldConditionsTimeCycleTime);
			configAltered = true;
		}
		if(this.config.get(START_MESSAGE) == null) {
			setStartMessage(defaultStartMessage);
			configAltered = true;
		}
		if(this.config.get(REMINDER_MESSAGE) == null) {
			setReminderMessage(defaultReminderMessage);
			configAltered = true;
		} 
		if(this.config.get(REMINDER_CYCLE_TIME) == null) {
			setReminderCycleTime(defaultReminderCycleTime);
			configAltered = true;
		}
		if(this.config.get(AUTO_START) == null) {
			setAutoStart(defaultAutoStart);
			configAltered = true;
		}
		if (configAltered) {
			this.configAccessor.saveConfig();
		}
		return configAltered;
	}
	
	public Location getPasteLocation() {
		Map<String, Object> map = new YMLLocation().getLocation(config, PASTE_LOCATION);
		return new SerializableLocation(map).getLocation();
	}
	
	public boolean existsPasteLocation() {
		if(this.config.get(PASTE_LOCATION) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Location getEntrance() {
		Map<String, Object> map = new YMLLocation().getLocation(config, ENTRANCE);
		return new SerializableLocation(map).getLocation();
	}
	
	public Location getExit() {
		Map<String, Object> map = new YMLLocation().getLocation(config, EXIT);
		return new SerializableLocation(map).getLocation();
	}
	
	public int getCreatureLimit() {
		return config.getInt(CREATURE_LIMIT);
	}
	
	public long getCycleTime() {
		return config.getLong(CYCLE_TIME);
	}
	
	public boolean getNoAir() {
		return config.getBoolean(NO_AIR);
	}
	
	public long getWorldConditionsTime() {
		return config.getLong(WORLD_CONDITIONS_TIME);
	}
	
	public long getWorldConditionsTimeCycleTime() {
		return config.getLong(WORLD_CONDITIONS_TIME_CYCLE_TIME);
	}
	
	public String getStartMessage() {
		return config.getString(START_MESSAGE);
	}
	
	public String getReminderMessage() {
		return config.getString(REMINDER_MESSAGE);
	}
	
	public long getReminderCycleTime() {
		return config.getLong(REMINDER_CYCLE_TIME);
	}
	
	public boolean getAutoStart() {
		return config.getBoolean(AUTO_START);
	}
	
	public boolean hasEntrance() {
		if(this.config.get(ENTRANCE) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasExit() {
		if(this.config.get(EXIT) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Set<String> getSpawns() {
		if(this.config.contains((SpawnConfigurationStructure.SPAWNS))) {
			return config.getConfigurationSection(SpawnConfigurationStructure.SPAWNS).getKeys(false);
		} 
		return null;
	}
}