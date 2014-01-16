package me.fuzzystatic.EventManager.configurations;

import java.io.File;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import me.fuzzystatic.EventManager.utilities.ConfigAccessor;
import me.fuzzystatic.EventManager.utilities.YMLLocation;

public class EventConfigurationStructure {
	
	public static final String DIRECTORY = "events";
	public static final String PASTE_LOCATION = "pasteLocation";
	public static final String ENTRANCE = "entrance";
	public static final String EXIT = "exit";
	public static final String CREATURE_LIMIT = "creatureLimit";
	public static final String CYCLE = "cycle";
	public static final String NO_AIR = "noAir";
	
	private static final int defaultCreatureLimit = 201;
	private static final long defaultCycle = 14400;
	private static final boolean defaultNoAir = false;
	
	private final ConfigAccessor configAccessor;
	private final FileConfiguration config;
	
	public EventConfigurationStructure(ConfigAccessor configAccessor) {
		this.configAccessor = configAccessor;
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
	
	public void setCycle(long seconds) {
		this.config.set(CYCLE, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setNoAir(boolean noAir) {
		this.config.set(NO_AIR, noAir);
		this.configAccessor.saveConfig();
	}
	
	public void createFileStructure() {
		if(this.config.get(CREATURE_LIMIT) == null) setCreatureLimit(defaultCreatureLimit);
		if(this.config.get(CYCLE) == null) setCycle(defaultCycle);
		if(this.config.get(NO_AIR) == null) setNoAir(defaultNoAir);
		this.configAccessor.saveConfig();
	}
	
	public static void createDirectoryStructure(File file) {
		File EventDir = new File(file, DIRECTORY);
		EventDir.mkdir();
	}
	
	public int getCreatureLimit() {
		return config.getInt(CREATURE_LIMIT);
	}
}