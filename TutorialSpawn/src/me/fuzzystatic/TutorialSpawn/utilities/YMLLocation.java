package me.fuzzystatic.TutorialSpawn.utilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class YMLLocation {

	private String world = "";
	private double x, y, z;
	private float yaw, pitch;
	
	private final Map<String, Object> map = new HashMap<String, Object>();
		
	// Extracts location from the YML in the form of a map.
	public Map<String, Object> getLocation(FileConfiguration config, String ymlKey) {
		map.put("world", config.get(ymlKey + ".world"));
	    map.put("x", config.get(ymlKey + ".x"));
		map.put("y", config.get(ymlKey + ".y"));
	  	map.put("z", config.get(ymlKey + ".z"));
	  	map.put("yaw", config.get(ymlKey + ".yaw"));
	   	map.put("pitch", config.get(ymlKey + ".pitch"));
		return map;
	}
	
	// Sets location to the YML. Config will still need to be received and saved after using.
	public void setLocation(Map<String, Object> map, FileConfiguration config, String ymlKey) {
		config.set(ymlKey + ".world", map.get("world"));
	    config.set(ymlKey + ".x", map.get("x"));
	    config.set(ymlKey + ".y", map.get("y"));
	    config.set(ymlKey + ".z", map.get("z"));
	    config.set(ymlKey + ".yaw", map.get("yaw"));
	    config.set(ymlKey + ".pitch", map.get("pitch"));
	}
	
	// Sets blank location to the YML
	public void setBlankLocation(FileConfiguration config, String ymlKey) {
		config.set(ymlKey + ".world", world);
		config.set(ymlKey + ".x", x);
		config.set(ymlKey + ".y", y);
		config.set(ymlKey + ".z", z);
		config.set(ymlKey + ".yaw", yaw);
		config.set(ymlKey + ".pitch", pitch);
	}
}
