package me.fuzzystatic.EventAdministrator.configuration;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
 
public class SerializableLocationMap implements ConfigurationSerializable {
    
	private final World world;
	private final double x, y, z;
	private final float yaw, pitch;
	private final Location location;
 
    public SerializableLocationMap(Location location) {
    	this.world = location.getWorld();
    	this.x = location.getBlockX();
    	this.y = location.getBlockY();
    	this.z = location.getBlockZ();
    	this.pitch = location.getPitch();
    	this.yaw = location.getYaw();
        this.location = location;
    }
 
    public SerializableLocationMap(Map<String, Object> map) {
    	this.world = Bukkit.getWorld(map.get("world").toString());
    	this.x = (Double) map.get("x");
    	this.y = (Double) map.get("y");
    	this.z = (Double) map.get("z");
    	this.pitch = Float.intBitsToFloat((Integer) map.get("pitch"));    
    	this.yaw = Float.intBitsToFloat((Integer) map.get("yaw"));
    	this.location = new Location(world, x, y, z, yaw, pitch);
    }
 
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("world", world);
        map.put("x", x);
        map.put("y", y);
        map.put("z", z);
        map.put("pitch", pitch);
        map.put("yaw", yaw);
        return map;
    }
    
    public Location getLocation() {
        return this.location;
    }
}