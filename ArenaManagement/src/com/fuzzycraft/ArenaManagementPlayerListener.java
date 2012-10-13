package com.fuzzycraft;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArenaManagementPlayerListener implements Listener{
	
	public Location locLC;
	public Location locRC;
	
	public ArenaManagement am;
	
	public ArenaManagementPlayerListener(ArenaManagement instance) {
		am = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerClick(PlayerInteractEvent evt) {
		
		if (evt.getAction() == Action.LEFT_CLICK_BLOCK) {
			locLC = evt.getClickedBlock().getLocation();
			evt.getPlayer().sendMessage(locLC.toString());
		}
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
			locRC = evt.getClickedBlock().getLocation();
			evt.getPlayer().sendMessage(locRC.toString());
		}
	}
	
}
