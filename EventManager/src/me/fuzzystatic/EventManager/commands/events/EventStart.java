package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.schedules.PlayerItems;
import me.fuzzystatic.EventManager.schedules.Reminder;
import me.fuzzystatic.EventManager.schedules.Spawning;
import me.fuzzystatic.EventManager.schedules.WorldConditions;
import me.fuzzystatic.EventManager.utilities.WorldEditSession;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;

public class EventStart implements CommandExecutor {

	private EventManager plugin;
	
	public EventStart(EventManager plugin) {
		this.plugin = plugin;
	}
				
	public boolean start() {
		EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
		ecs.createFileStructure();
		if(ecs.existsPasteLocation()) {
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Dragon event is starting! Type /warp de or take the portal at spawn to join!");
			ecs.getPasteLocation().getWorld().spawnEntity(ecs.getPasteLocation(), EntityType.ENDER_DRAGON);
			try {
				WorldEditSession worldEditSession = new WorldEditSession(plugin, ecs.getPasteLocation().getWorld());
			   	CuboidClipboard clipboard = worldEditSession.loadSchematic(EventName.getName());
				clipboard.paste(worldEditSession.getEditSession(), BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir());
			} catch (MaxChangedBlocksException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStart")) {
			EventConfigurationStructure ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
				public void run() {
					EventStop eventStop = new EventStop(plugin);
					PlayerItems playerItems = new PlayerItems(plugin);
					Reminder reminder = new Reminder(plugin);
					Spawning spawning = new Spawning(plugin);
					WorldConditions worldConditions = new WorldConditions(plugin);
					
					if(eventStop.stop()) {
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "All events stopped.");
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Error stopping event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + ".");	
					}
					if(eventStop.clearEntities()) {
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " cleared.");
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Paste location does not exist. Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " not cleared.");	
					}
					if(start()) {
						spawning.start();
						worldConditions.start();
						reminder.start();
						playerItems.start();
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " has started.");
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Paste location does not exist. Event " + ChatColor.DARK_AQUA + EventName.getName() + ChatColor.LIGHT_PURPLE + " not started.");	
					}
				}
			}, 0, ecs.getCycleTime() * 20);
			return true;
		}
		return false;
	}
}
