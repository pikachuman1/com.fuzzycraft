package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;
import me.fuzzystatic.EventAdministrator.utilities.WorldEditSession;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.Countable;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.blocks.BaseBlock;

public class EventLocations extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);
		if (args.length > 1) {
			if (hasPermissionNode(sender) && isPlayer(sender)) {
				if (args[1].equalsIgnoreCase("en") || args[1].equalsIgnoreCase("entrance")) {
					Player player = (Player) sender;
					SerializableLocation sl = new SerializableLocation(player.getLocation());
					ecs.setEntrance(sl.serialize());
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event entrance set.");
				}
				if (args[1].equalsIgnoreCase("ex") || args[1].equalsIgnoreCase("exit")) {
					Player player = (Player) sender;
					SerializableLocation sl = new SerializableLocation(player.getLocation());
					ecs.setExit(sl.serialize());
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "New event exit set.");
				}
				if (args[1].equalsIgnoreCase("t") || args[1].equalsIgnoreCase("test")) {
					Player player = (Player) sender;
					try {
						WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
						CuboidClipboard clipboard = worldEditSession.loadSchematic(eventName);
						ConsoleLogs.sendMessage(clipboard.getSize().toString());
						ConsoleLogs.sendMessage(clipboard.getOrigin().toString());
						ConsoleLogs.sendMessage(clipboard.getOffset().toString());
						ConsoleLogs.sendMessage(String.valueOf(clipboard.getHeight()));
						ConsoleLogs.sendMessage(String.valueOf(clipboard.getLength()));
						ConsoleLogs.sendMessage(String.valueOf(clipboard.getWidth()));
					} catch (EmptyClipboardException e) {
						player.sendMessage(ChatColor.DARK_PURPLE + "Clipboard is empty.");
					}
				}
				if (args[1].equalsIgnoreCase("r") || args[1].equalsIgnoreCase("rest")) {
					Player player = (Player) sender;
					try {
						WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
						CuboidClipboard clipboard = worldEditSession.loadSchematic(eventName);
						for(Countable<Integer> integer : clipboard.getBlockDistribution()) {
							ConsoleLogs.sendMessage(String.valueOf(integer));
						}
					} catch (EmptyClipboardException e) {
						player.sendMessage(ChatColor.DARK_PURPLE + "Clipboard is empty.");
					}
				}
				if (args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("crest")) {
					Player player = (Player) sender;
					try {
						WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
						CuboidClipboard clipboard = worldEditSession.loadSchematic(eventName);
						for(Countable<BaseBlock> integer : clipboard.getBlockDistributionWithData()) {
							ConsoleLogs.sendMessage(String.valueOf(integer));
						}
					} catch (EmptyClipboardException e) {
						player.sendMessage(ChatColor.DARK_PURPLE + "Clipboard is empty.");
					}
				}
			} else {
				playerOnly(sender);
			}
			return true;
		} else {
			sendMessage(sender, ChatColor.LIGHT_PURPLE + "TO SET: " + usage());
			return false;
		}
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("locations");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " loc{ation} [en{trance}|ex{it}]";
	}
}
