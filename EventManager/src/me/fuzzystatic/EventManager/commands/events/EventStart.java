package me.fuzzystatic.EventManager.commands.events;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.EventSpawners;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.constants.Strings;
import me.fuzzystatic.EventManager.entities.EventEntities;
import me.fuzzystatic.EventManager.utilities.ConfigAccessor;
import me.fuzzystatic.EventManager.utilities.DirectoryStructure;
import me.fuzzystatic.EventManager.utilities.SerializableLocation;
import me.fuzzystatic.EventManager.utilities.ConsoleLogs;
import me.fuzzystatic.EventManager.utilities.WorldEditSession;
import me.fuzzystatic.EventManager.utilities.YMLLocation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;

public class EventStart implements CommandExecutor {

	private EventManager plugin;
	
	public EventStart(EventManager plugin) {
		this.plugin = plugin;
	}
	
	ConsoleLogs sc = new ConsoleLogs();
	
	private final ItemStack bow = new ItemStack(Material.BOW, 1);
	private final ItemStack arrows = new ItemStack(Material.ARROW, 64);
	private final ItemStack food = new ItemStack(Material.BREAD, 6);
	
	private static List<Integer> bossIDs = new ArrayList<Integer>();
			
	public void startEvent() {
		ConfigAccessor eventAccessor = new ConfigAccessor(plugin, EventName.getFilename());
		FileConfiguration eventConfig = eventAccessor.getConfig();
	   	World world = Bukkit.getWorld(eventConfig.getString(EventConfigurationStructure.PASTE_LOCATION + ".world"));
		SerializableLocation slEvent = new SerializableLocation(new YMLLocation().getLocation(eventConfig, EventConfigurationStructure.PASTE_LOCATION));
		//Before or after regen can affect event. Currently before.
		//if(!(eventConfig.get(Strings.EC_EXIT.toString()) == null)) {
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + " Dragon event is starting! Type /warp de or take the portal at spawn to join!");
		world.spawnEntity(slEvent.getLocation(), EntityType.ENDER_DRAGON);
		//}
		try {
			WorldEditSession worldEditSession = new WorldEditSession(plugin, world);
		   	CuboidClipboard clipboard = worldEditSession.loadSchematic(EventName.getName());
			clipboard.paste(worldEditSession.getEditSession(), BukkitUtil.toVector(slEvent.getLocation()), eventConfig.getBoolean(EventConfigurationStructure.NO_AIR));
		} catch (MaxChangedBlocksException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void items(long seconds) {
		long ticks = seconds * 20;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				ConfigAccessor eventAccessor = new ConfigAccessor(plugin, Strings.DIR_EVENTS + File.separator + EventName.getName() + File.separator + Strings.EVENT_FILE_NAME);
				FileConfiguration eventConfig = eventAccessor.getConfig();
			   	World world = Bukkit.getWorld(eventConfig.getString(Strings.EC_PASTE_LOCATION.toString() + ".world"));
				EventEntities eventEntities = new EventEntities(world);
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
		long ticks = seconds * 20;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				ConfigAccessor eventAccessor = new ConfigAccessor(plugin, Strings.DIR_EVENTS + File.separator + EventName.getName() + File.separator + Strings.EVENT_FILE_NAME);
				FileConfiguration eventConfig = eventAccessor.getConfig();
			   	World world = Bukkit.getWorld(eventConfig.getString(Strings.EC_PASTE_LOCATION.toString() + ".world"));
				world.setTime(18000);
			}
		}, 0, ticks);
	}
	
	public void reminder(long seconds) {
		long ticks = seconds * 20;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				ConfigAccessor eventAccessor = new ConfigAccessor(plugin, Strings.DIR_EVENTS + File.separator + EventName.getName() + File.separator + Strings.EVENT_FILE_NAME);
				FileConfiguration eventConfig = eventAccessor.getConfig();
			   	World world = Bukkit.getWorld(eventConfig.getString(Strings.EC_PASTE_LOCATION.toString() + ".world"));
			   	EventEntities eventEntities = new EventEntities(world);
				if(eventEntities.bossAlive()) {
					Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + Strings.PLUGIN_NAME.toString() + ChatColor.GREEN + " Dragon event is in progress! Type /warp de or take the portal at spawn to join!");
				}	
			}
		}, ticks, ticks);
	}
		
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStart")) {
			ConfigAccessor eventAccessor = new ConfigAccessor(plugin, Strings.DIR_EVENTS + File.separator + EventName.getName() + File.separator + Strings.EVENT_FILE_NAME);
			FileConfiguration eventConfig = eventAccessor.getConfig();
		   	long ticks = eventConfig.getLong(Strings.EC_CYCLE.toString());
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
					EventStop eventStop = new EventStop(plugin);
					eventStop.stopEvent();
					startEvent();
					startSpawns();
					items(90);
					eventTimeMidnight(60);
					reminder(300);
				}
			}, 0, ticks);
			return true;
		}
		return false;
	}
}
