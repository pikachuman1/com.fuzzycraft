package me.fuzzystatic.EventAdministrator.utilities;

import com.sk89q.worldedit.Vector;

public class WorldEditRectangle {

	private final Vector origin;
	private final Vector end;
	
	public WorldEditRectangle(Vector origin, Vector size) {
		this.origin = origin;
		this.end = null;
		this.end.add((origin.getX() - 1) + size.getX(), 
				(origin.getY() - 1) + size.getY(), 
				(origin.getZ() - 1) + size.getZ());
	}
	
	
}
