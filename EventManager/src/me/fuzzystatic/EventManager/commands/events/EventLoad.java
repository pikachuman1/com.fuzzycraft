package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.utilities.WorldEditSession;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldguard.bukkit.BukkitUtil;

public class EventLoad implements CommandExecutor {
	
	private EventManager plugin;
		
	public EventLoad(EventManager plugin) {
		this.plugin = plugin;
	}
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("emLoad")) {
			Player player = (Player) sender;
			try {
				WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
				CuboidClipboard clipboard = worldEditSession.loadSchematic(EventName.getName());
	    		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());			
				clipboard.paste(worldEditSession.getEditSession(), BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir());
				return true;
			} catch (EmptyClipboardException e) {
				player.sendMessage(ChatColor.DARK_PURPLE + "Clipboard is empty.");
				return true;
			} catch (MaxChangedBlocksException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Maximum changed blocks.");
				e.printStackTrace();
				return true;
			}
		}
		return false;
	}
}
