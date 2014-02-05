package me.fuzzystatic.EventAdministrator.commands.event;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldguard.bukkit.BukkitUtil;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;

public class EventLoad extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			try {
		        EditSession es = new EditSession(new BukkitWorld(ecs.getPasteLocation().getWorld()), Integer.MAX_VALUE);
				CuboidClipboard cc = SchematicFormat.MCEDIT.load(new File(eventName + ".schematic"));
				cc.paste(es, BukkitUtil.toVector(ecs.getPasteLocation()), ecs.getNoAir(), true);
			} catch (MaxChangedBlocksException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Maximum changed blocks.");
				e.printStackTrace();
			} catch (IOException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Could not load schematic.");
				e.printStackTrace();
			} catch (DataException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Could not load schematic.");
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
