package me.fuzzystatic.EventManager.commands.events;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.Spawning;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.entities.EventEntities;
import me.fuzzystatic.EventManager.utilities.WorldEditSession;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;

public class EventStart implements CommandExecutor {

	private EventManager plugin;
	private final EventConfigurationStructure ecs;
	private final Spawning es;
	
	public EventStart(EventManager plugin) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
		this.es = new Spawning(this.plugin);
	}
		
	private final ItemStack bow = new ItemStack(Material.BOW, 1);
	private final ItemStack arrows = new ItemStack(Material.ARROW, 64);
	private final ItemStack food = new ItemStack(Material.BREAD, 6);
				
	public void startEvent() {
		//Before or after regen can affect event. Currently before.
		//if(!(eventConfig.get(Strings.EC_EXIT.toString()) == null)) {
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + " Dragon event is starting! Type /warp de or take the portal at spawn to join!");
		ecs.getPasteLocation().getWorld().spawnEntity(ecs.getPasteLocation(), EntityType.ENDER_DRAGON);
		//}
		try {
			WorldEditSession worldEditSession = new WorldEditSession(plugin, ecs.getPasteLocation().getWorld());
		   	CuboidClipboard clipboard = worldEditSession.loadSchematic(EventName.getName());
			clipboard.paste(worldEditSession.getEditSession(), BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir());
		} catch (MaxChangedBlocksException e) {
			e.printStackTrace();
		}
	}
	
	public void items(long seconds) {
		long ticks = seconds * 20;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				EventEntities eventEntities = new EventEntities(ecs.getPasteLocation().getWorld());
				for (Player player : eventEntities.getPlayers()) {
					PlayerInventory inventory = player.getInventory();
				    inventory.addItem(bow);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(food);
				    inventory.addItem(bow);
				}	
			}
		}, 60, ticks);
	}
	
	public void eventTimeMidnight(long seconds) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				ecs.getPasteLocation().getWorld().setTime(18000);
			}
		}, 0, seconds * 20);
	}
	
	public void reminder(long seconds) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
			   	EventEntities eventEntities = new EventEntities(ecs.getPasteLocation().getWorld());
				if(eventEntities.bossAlive()) {
					Bukkit.getServer().broadcastMessage(ChatColor.GREEN + " Dragon event is in progress! Type /warp de or take the portal at spawn to join!");
				}	
			}
		}, seconds * 20, seconds * 20);
	}
		
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStart")) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
				public void run() {
					EventStop eventStop = new EventStop(plugin);
					eventStop.stopEvent();
					startEvent();
					es.startSpawns();
					items(90);
					eventTimeMidnight(60);
					reminder(300);
				}
			}, 0, ecs.getCycle() * 20);
			return true;
		}
		return false;
	}
}
