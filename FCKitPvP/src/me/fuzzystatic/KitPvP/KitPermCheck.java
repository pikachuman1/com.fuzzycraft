package me.fuzzystatic.KitPvP;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitPermCheck implements CommandExecutor {
	@SuppressWarnings("unused")
	private KitPvP plugin;
	public KitPermCheck(KitPvP plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(commandLabel.equalsIgnoreCase("kit") || commandLabel.equalsIgnoreCase("kits"));
       	Player player = (Player) sender;
       	player.sendMessage(ChatColor.DARK_AQUA + "*You have the following kits*");  
       	player.sendMessage(ChatColor.DARK_AQUA + " ");  
       	player.sendMessage(ChatColor.DARK_AQUA + "Standard:");  
       	if(player.hasPermission("kitpvp.Squire")) {
       		player.sendMessage(ChatColor.GREEN + "- Squire " + ChatColor.LIGHT_PURPLE + "/squire"); 		
    	}
       	if(player.hasPermission("kitpvp.archer")) {
       		player.sendMessage(ChatColor.GREEN + "- Archer " + ChatColor.LIGHT_PURPLE + "/archer");   		
       	}
       	if(player.hasPermission("kitpvp.streaker")) {
       		player.sendMessage(ChatColor.GREEN + "- Streaker " + ChatColor.LIGHT_PURPLE + "/streaker");   		
       	}
       	player.sendMessage(ChatColor.DARK_AQUA + "Acquired:"); 
       	if(player.hasPermission("kitpvp.knight")) {
       		player.sendMessage(ChatColor.GREEN + "- Knight " + ChatColor.LIGHT_PURPLE + "/knight" + ChatColor.DARK_AQUA + " or "  + ChatColor.LIGHT_PURPLE + "/tank");  		
    	}      	
       	if(player.hasPermission("kitpvp.chemist")) {
       		player.sendMessage(ChatColor.GREEN + "- Chemist " + ChatColor.LIGHT_PURPLE + "/chemist");  		
    	}
       	if(player.hasPermission("kitpvp.lumberjack")) {
       		player.sendMessage(ChatColor.GREEN + "- Lumberjack " + ChatColor.LIGHT_PURPLE + "/lumberjack" + ChatColor.DARK_AQUA + " or "  + ChatColor.LIGHT_PURPLE + "/lj");  		
    	}
       	if(player.hasPermission("kitpvp.ninja")) {
       		player.sendMessage(ChatColor.GREEN + "- Ninja " + ChatColor.LIGHT_PURPLE + "/ninja");  		
    	}
       	if(player.hasPermission("kitpvp.mystic")) {
       		player.sendMessage(ChatColor.GREEN + "- Mystic " + ChatColor.LIGHT_PURPLE + "/mystic");  		
    	}
       	if(player.hasPermission("kitpvp.king")) {
       		player.sendMessage(ChatColor.GREEN + "- King " + ChatColor.LIGHT_PURPLE + "/king");  		
    	}
       	if(player.hasPermission("kitpvp.firemage")) {
       		player.sendMessage(ChatColor.GREEN + "- Fire Mage " + ChatColor.LIGHT_PURPLE + "/firemage" + ChatColor.DARK_AQUA + " or "  + ChatColor.LIGHT_PURPLE + "/fm");  		
    	}
       	if(player.hasPermission("kitpvp.reaper")) {
       		player.sendMessage(ChatColor.GREEN + "- Reaper " + ChatColor.LIGHT_PURPLE + "/reaper");  		
    	}
       	if(player.hasPermission("kitpvp.fireomen")) {
       		player.sendMessage(ChatColor.GREEN + "- Fire Omen " + ChatColor.LIGHT_PURPLE + "/fireomen" + ChatColor.DARK_AQUA + " or "  + ChatColor.LIGHT_PURPLE + "/fo");  		
    	}
       	if(player.hasPermission("kitpvp.monk")) {
       		player.sendMessage(ChatColor.GREEN + "- Monk " + ChatColor.LIGHT_PURPLE + "/monk");  		
    	}
		return false;
	}
}
