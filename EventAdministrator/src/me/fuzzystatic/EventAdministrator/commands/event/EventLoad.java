package me.fuzzystatic.EventAdministrator.commands.event;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.worldedit.WorldEditLoad;
import me.fuzzystatic.EventAdministrator.worldedit.WorldEditRectangle;

public class EventLoad extends Command {

	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender)) {
			Player player = (Player) sender;
			
			WorldEditLoad wel = new WorldEditLoad(plugin, eventName);
			WorldEditRectangle wer = new WorldEditRectangle(wel.getClipboard().getOrigin(), wel.getClipboard().getSize());
			if (wer.inRectangle(player.getLocation())) sendMessage(sender, "You are in the event");
			
			if (wel.paste()) sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " loaded.");
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
