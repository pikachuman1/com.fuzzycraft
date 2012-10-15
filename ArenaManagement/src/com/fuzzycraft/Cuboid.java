package com.fuzzycraft;

import org.bukkit.Location;

public class Cuboid {
	
	private final String world;
	private final int x1, y1, z1;
	private final int x2, y2, z2;

	public Cuboid(Location location1, Location location2) {
		if (location1.getWorld() != location2.getWorld()) {
			throw new IllegalArgumentException("locations must be on the same world");
		}
		world = location1.getWorld().getName();
		x1 = Math.min(location1.getBlockX(), location2.getBlockX());
		y1 = Math.min(location1.getBlockY(), location2.getBlockY());
		z1 = Math.min(location1.getBlockZ(), location2.getBlockZ());
		x2 = Math.max(location1.getBlockX(), location2.getBlockX());
		y2 = Math.max(location1.getBlockY(), location2.getBlockY());
		z2 = Math.max(location1.getBlockZ(), location2.getBlockZ());
	}
}
