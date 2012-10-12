package com.fuzzycraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArenaManagement extends JavaPlugin {

	private PlayerListener pl = new PlayerListener(this);
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
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
