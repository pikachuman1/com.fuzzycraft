package me.fuzzystatic.EventAdministrator.entities;

import java.util.ArrayList;
import java.util.List;

import me.fuzzystatic.EventAdministrator.worldedit.WorldEditCuboid;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.Vector;

public class Entities {

	private final List<Integer> livingEntities = new ArrayList<Integer>();
	private final List<Entity> nonPlayerEntities = new ArrayList<Entity>();
	private final List<LivingEntity> mobs = new ArrayList<LivingEntity>();
	private final List<Player> players = new ArrayList<Player>();

	public Entities(World world) {	
	    for (Entity entity : world.getEntities()) {
	    	if (entity instanceof Player) {
		    	this.players.add((Player) entity);
	    	} else {
	    		this.nonPlayerEntities.add(entity);
	    	}
		    if (entity instanceof LivingEntity) {
		    	livingEntities.add(entity.getEntityId());
	    		if(!(entity instanceof HumanEntity)) this.mobs.add((LivingEntity) entity);
		    }
	    }
	}
	
	public Entities(World world, Vector origin, Vector size) {
		WorldEditCuboid wec = new WorldEditCuboid(origin, size);
	    for (Entity entity : world.getEntities()) {
	    	if (wec.inCuboid(entity.getLocation())) {
	    		if (entity instanceof Player) {
			    	this.players.add((Player) entity);
		    	} else {
		    		this.nonPlayerEntities.add(entity);
		    	}
			    if (entity instanceof LivingEntity) {
			    	livingEntities.add(entity.getEntityId());
			     	if (!(entity instanceof HumanEntity)) this.mobs.add((LivingEntity) entity); 
			    }
	    	}
	    }
	}
	
	public boolean removeAllNonPlayerEntities() {
		for(Entity entity : this.nonPlayerEntities) {
			entity.remove();
			return true;
		}
		return false;
	}
	
	public boolean teleportAllPlayers(Location location) {
		for(Player player : this.players) {
			player.sendMessage("You are being teleported out of the event...");
	    	player.teleport(location);
	    	return true;
		}
		return false;
	}
	
	public List<Integer> getLivingEntities() {
		return this.livingEntities;
	}
	
	public List<Entity> getNonPlayerEntities() {
		return this.nonPlayerEntities;
	}
	
	public List<LivingEntity> getMobs() {
		return this.mobs;
	}
	
	public List<Player> getPlayers() {
		return this.players;
	}
}
