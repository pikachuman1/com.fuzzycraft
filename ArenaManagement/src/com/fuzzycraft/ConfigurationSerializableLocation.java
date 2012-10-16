package com.fuzzycraft;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
 
public class ConfigurationSerializableLocation implements ConfigurationSerializable {
    
	private final World world;
	private final double x, y, z;
	private final float yaw, pitch;
	private final Location loc;
	private final Chunk chunk;
 
    public ConfigurationSerializableLocation(Location loc) {
    	world = loc.getWorld();
    	x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();
        chunk = loc.getChunk();
        this.loc = loc;
    }
 
    public ConfigurationSerializableLocation(Map<String, Object> map) {
    	world = Bukkit.getWorld(map.get("world").toString());
    	x = (Double) map.get("x");
        y = (Double) map.get("y");
        z = (Double) map.get("z");
        yaw = Float.intBitsToFloat((Integer) map.get("yaw"));
        pitch = Float.intBitsToFloat((Integer) map.get("pitch"));    
        loc = new Location(world, x, y, z, yaw, pitch);
        chunk = loc.getChunk();
    }
 
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("world", loc.getWorld().getName());
        map.put("x", loc.getX());
        map.put("y", loc.getY());
        map.put("z", loc.getZ());
        map.put("yaw", Float.floatToIntBits(loc.getYaw()));
        map.put("pitch", Float.floatToIntBits(loc.getPitch()));
        return map;
    }
    
    public Location getLocation() {
        Location location = new Location(world, x, y, z, yaw, pitch);
        return location;
    }
    
}