package me.fuzzystatic.EventAdministrator.commands.event;

import java.io.File;
import java.io.IOException;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;
import me.fuzzystatic.EventAdministrator.utilities.WorldEditSession;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

public class EventSave extends Command {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) {
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			Player player = (Player) sender;
    		SerializableLocation sl = new SerializableLocation(player.getLocation());
			try {
				WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
				CuboidClipboard cc = worldEditSession.getLocalSession().getClipboard();
				SchematicFormat.MCEDIT.save(cc, new File(eventName + ".schematic"));
			} catch (EmptyClipboardException e) {
				player.sendMessage(ChatColor.LIGHT_PURPLE + "Clipboard is empty.");
			} catch (IOException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Could not load schematic.");
				e.printStackTrace();
			} catch (DataException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Could not load schematic.");
				e.printStackTrace();
			}
    		ecs.setPasteLocation(sl.serialize());
			return true;
		} else {
			playerOnly(sender);
			return false;
		}
	}

	@Override
	public Permission permission() {
		Permission permission = new Permission("save");
		permission.addParent(super.permission(), true);
		return permission;
	}

	@Override
	public String usage() {
		return super.usage() + " save";
	}
}