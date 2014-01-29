package me.fuzzystatic.EventAdministrator.commands.event;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldguard.bukkit.BukkitUtil;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.utilities.WorldEditSession;

public class EventLoad extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			Player player = (Player) sender;
			try {
				WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
				CuboidClipboard clipboard = worldEditSession.loadSchematic(eventName);
				clipboard.paste(worldEditSession.getEditSession(), BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir());
			} catch (EmptyClipboardException e) {
				player.sendMessage(ChatColor.DARK_PURPLE + "Clipboard is empty.");
			} catch (MaxChangedBlocksException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Maximum changed blocks.");
				e.printStackTrace();
			}
			return true;
		} else {
			playerOnly(sender);
			return false;
		}
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("load");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " load";
	}
}
