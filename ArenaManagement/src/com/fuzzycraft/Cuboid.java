package com.fuzzycraft;

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
	
	public int[][][] array () {
		int[][][] locArray = new int[getSizeX()][getSizeY()][getSizeZ()];
		for (int x = minX; x <= maxX; x++) {
		    for (int y = minY; y <= maxY; y++) {
		        for (int z = minZ; z <= maxZ; z++) {
		        	locArray[x][y][z] = x + y + z;
		        }
		    }
		}
		return locArray;
	}
	
}
