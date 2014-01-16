package me.fuzzystatic.EventManager.entities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class EventEntities {

	private final List<Entity> nonPlayerEntities = new ArrayList<Entity>();
	private final List<LivingEntity> bosses = new ArrayList<LivingEntity>();
	private final List<LivingEntity> mobs = new ArrayList<LivingEntity>();
	private final List<Player> players = new ArrayList<Player>();

	public EventEntities(World world) {	
	    for (Entity entity : world.getEntities()) {
	    	if (entity instanceof Player) {
		    	this.players.add((Player) entity);
	    	} else {
	    		this.nonPlayerEntities.add(entity);
	    	}
		    if (entity instanceof LivingEntity && !(entity instanceof HumanEntity)) this.mobs.add((LivingEntity) entity);
	    	if (entity instanceof EnderDragon) this.bosses.add((EnderDragon) entity);
	    }
	}
	
	public void removeAllNonPlayerEntities() {
		for(Entity entity : this.nonPlayerEntities) entity.remove();
	}
	
	public void teleportAllPlayers(Location location) {
		for(Player player : this.players) {
			player.sendMessage("You are being teleported out of the event...");
	    	player.teleport(location);
		}
	}
	
	public boolean bossAlive() {
		boolean alive = false;
		if (this.bosses.size() > 0) alive = true;
		return alive;
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
