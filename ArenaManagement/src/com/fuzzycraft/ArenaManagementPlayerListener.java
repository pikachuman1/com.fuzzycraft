package com.fuzzycraft;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ArenaManagementPlayerListener implements Listener{
	
	public ArenaManagement plugin;
	
	public ArenaManagementPlayerListener(ArenaManagement instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent evt){
		evt.getPlayer().sendMessage("Join");
	}
    
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerClick(PlayerInteractEvent evt) {
		Location positionLeftClick;
		Location positionRightClick;
		
		if (evt.getAction() == Action.LEFT_CLICK_BLOCK) {
			positionLeftClick = evt.getClickedBlock().getLocation();
			evt.getPlayer().sendMessage(positionLeftClick.toString());
		}
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
			positionRightClick = evt.getClickedBlock().getLocation();
			evt.getPlayer().sendMessage(positionRightClick.toString());
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("basic")){ // If the player typed /basic then do the following...
			sender.sendMessage("Test.");
			return true;
		}
		return false;
	}
	
}
