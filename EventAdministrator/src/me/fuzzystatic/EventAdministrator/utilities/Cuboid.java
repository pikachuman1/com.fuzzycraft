package me.fuzzystatic.EventAdministrator.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Cuboid {

	private final String world;
	private final int minX, minY, minZ;
	private final int maxX, maxY, maxZ;

	public Cuboid(Location loc1, Location loc2) {
		if (loc1.getWorld() != loc2.getWorld()) {
			throw new IllegalArgumentException("Locations must be on the same world");
		}
		world = loc1.getWorld().getName();
		minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
		minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
		minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
		maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
		maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
	}

	public String getWorld() {
		return world;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMinZ() {
		return minZ;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMaxZ() {
		return maxZ;
	}

	public int getSizeX() {
		return (maxX - minX) + 1;
	}

	public int getSizeY() {
		return (maxY - minY) + 1;
	}

	public int getSizeZ() {
		return (maxZ - minZ) + 1;
	}

	public void saveBlockData(String filename) {
		File file = new File(filename + ".schematic");
		try {
		    if(!file.exists())
		    {
		        FileOutputStream fos = new FileOutputStream(file);
		        fos.flush();
		        fos.close();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int x = minX; x <= maxX; x++) {
		    for (int y = minY; y <= maxY; y++) {
		        for (int z = minZ; z <= maxZ; z++) {
		        	Location loc = new Location(Bukkit.getServer().getWorld(world), x, y, z, 0, 0);
		        	try {
			        	BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			            {
			                bw.write(loc.getBlock() + "");
			                bw.newLine();
			            }
			            bw.flush();
			            bw.close();
		        	} catch (IOException e) {
		    			e.printStackTrace();
		    		}
		        }
		    }
		}
	}
}
