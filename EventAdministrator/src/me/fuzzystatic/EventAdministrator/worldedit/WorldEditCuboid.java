package me.fuzzystatic.EventAdministrator.worldedit;

import org.bukkit.Location;

import com.sk89q.worldedit.Vector;

public class WorldEditCuboid {

	private final double originX;
	private final double originY;
	private final double originZ;
	private final double endX;
	private final double endY;
	private final double endZ;
	
	public WorldEditCuboid(Vector origin, Vector size) {
		this.originX = origin.getX();	
		this.originY = origin.getY();	
		this.originZ = origin.getZ();	
		this.endX = originX + size.getX() - 1; 
		this.endY = originY + size.getY() - 1; 
		this.endZ = originZ + size.getZ() - 1; 
	}
	
	public boolean inCuboid(Location location) {
		if (this.originX <= location.getX() && this.originY <= location.getY() && this.originZ <= location.getZ()) {
			if (this.endX >= location.getX() && this.endY >= location.getY() && this.endZ >= location.getZ()) {
				return true;
			}
		}
		return false;
	}
}
