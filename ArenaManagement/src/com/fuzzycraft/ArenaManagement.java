package com.fuzzycraft;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ArenaManagement extends JavaPlugin {
	 
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
	}
 
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
	
	public void onPlayerClick(PlayerInteractEvent evt) {
		evt.getPlayer().sendMessage(evt.getClickedBlock().getLocation().toString());
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("basic")){ // If the player typed /basic then do the following...
			sender.sendMessage("Test.");
			return true;
		} else if (cmd.getName().equalsIgnoreCase("basic2")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				sender.sendMessage("Test.");
			}
			return true;
		}
		return false;
	}
}
