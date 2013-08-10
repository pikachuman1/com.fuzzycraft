package me.fuzzystatic.TutorialSpawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TSPermCheck implements CommandExecutor {
	
	private TutorialSpawn plugin;
	
	public TSPermCheck(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
       	Player player = (Player) sender;
		return false;
	}
}
