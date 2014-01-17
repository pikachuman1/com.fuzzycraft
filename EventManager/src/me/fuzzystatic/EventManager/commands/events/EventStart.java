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
	private final EventConfigurationStructure ecs;
	private final PlayerItems playerItems;
	private final Reminder reminder;
	private final Spawning spawning;
	private final WorldConditions worldConditions;
	
	public EventStart(EventManager plugin) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(this.plugin, EventName.getName());
		this.playerItems = new PlayerItems(this.plugin);
		this.reminder = new Reminder(this.plugin);
		this.spawning = new Spawning(this.plugin);
		this.worldConditions = new WorldConditions(this.plugin);
	}
				
	public void start() {
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + " Dragon event is starting! Type /warp de or take the portal at spawn to join!");
		ecs.getPasteLocation().getWorld().spawnEntity(ecs.getPasteLocation(), EntityType.ENDER_DRAGON);
		try {
			WorldEditSession worldEditSession = new WorldEditSession(plugin, ecs.getPasteLocation().getWorld());
		   	CuboidClipboard clipboard = worldEditSession.loadSchematic(EventName.getName());
			clipboard.paste(worldEditSession.getEditSession(), BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir());
		} catch (MaxChangedBlocksException e) {
			e.printStackTrace();
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("emStart")) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
				public void run() {
					EventStop eventStop = new EventStop(plugin);
					eventStop.stop();
					start();
					spawning.start();
					worldConditions.start();
					reminder.start();
					playerItems.start();
				}
			}, 0, ecs.getCycleTime() * 20);
			return true;
		}
		return false;
	}
}
