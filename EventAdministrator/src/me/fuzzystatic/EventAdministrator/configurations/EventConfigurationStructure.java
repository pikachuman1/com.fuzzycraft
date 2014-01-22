package me.fuzzystatic.EventAdministrator.configurations;

import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConfigAccessor;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;
import me.fuzzystatic.EventAdministrator.utilities.YMLLocation;

public class EventConfigurationStructure implements FileStructure {
	
	public static final String PASTE_LOCATION = "pasteLocation";
	public static final String ENTRANCE = "entrance";
	public static final String EXIT = "exit";
	public static final String CREATURE_LIMIT = "creatureLimit";
	public static final String CYCLE_TIME = "cycleTime";
	public static final String NO_AIR = "noAir";
	public static final String WORLD_CONDITIONS = "worldConditions";
	public static final String WORLD_CONDITIONS_TIME = WORLD_CONDITIONS + "." + "time";
	public static final String WORLD_CONDITIONS_TIME_CYCLE_TIME = WORLD_CONDITIONS + "." + "timeCycleTime";
	public static final String START_MESSAGE = "startMessage";
	public static final String REMINDER = "reminder";
	public static final String REMINDER_MESSAGE = REMINDER + "." + "message";
	public static final String REMINDER_CYCLE_TIME = REMINDER + '.' + "cycleTime";
	
	private static final int defaultCreatureLimit = 201;
	private static final long defaultCycle = 14400;
	private static final boolean defaultNoAir = false;
	private static final long defaultWorldConditionsTime = 18000;
	private static final long defaultWorldConditionsTimeCycleTime = 60;
	private static final String defaultStartMessage = "";
	private static final String defaultReminderMessage = "";
	private static final long defaultReminderCycleTime = -1;
	
	private final ConfigAccessor configAccessor;
	private final FileConfiguration config;
	
	public EventConfigurationStructure(EventAdministrator plugin, String eventName) {
		this.configAccessor = new ConfigAccessor(plugin, DirectoryStructure.EVENT_DIR + eventName + ".yml");
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
	
	@Override
	public void createFileStructure() {
		if(this.config.get(CREATURE_LIMIT) == null) setCreatureLimit(defaultCreatureLimit);
		if(this.config.get(CYCLE_TIME) == null) setCycleTime(defaultCycle);
		if(this.config.get(NO_AIR) == null) setNoAir(defaultNoAir);
		if(this.config.get(WORLD_CONDITIONS_TIME) == null) setWorldConditionsTime(defaultWorldConditionsTime);
		if(this.config.get(WORLD_CONDITIONS_TIME_CYCLE_TIME) == null) setWorldConditionsTimeCycleTime(defaultWorldConditionsTimeCycleTime);
		if(this.config.get(START_MESSAGE) == null) setStartMessage(defaultStartMessage);
		if(this.config.get(REMINDER_MESSAGE) == null) setReminderMessage(defaultReminderMessage);
		if(this.config.get(REMINDER_CYCLE_TIME) == null) setReminderCycleTime(defaultReminderCycleTime);
		this.configAccessor.saveConfig();
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
	
	public boolean existsEntrance() {
		if(this.config.get(ENTRANCE) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Location getExit() {
		Map<String, Object> map = new YMLLocation().getLocation(config, EXIT);
		return new SerializableLocation(map).getLocation();
	}
	
	public boolean existsExit() {
		if(this.config.get(EXIT) != null) {
			return true;
		} else {
			return false;
		}
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
	
	public Set<String> getSpawns() {
		if(this.config.contains((SpawnConfigurationStructure.SPAWNS))) {
			return config.getConfigurationSection(SpawnConfigurationStructure.SPAWNS).getKeys(false);
		} 
		return null;
	}
}