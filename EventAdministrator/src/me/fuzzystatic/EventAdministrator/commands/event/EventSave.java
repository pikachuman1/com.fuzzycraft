package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.commands.Command;
import me.fuzzystatic.EventAdministrator.configuration.structure.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.maps.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.worldedit.WorldEditSave;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class EventSave extends Command {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String args[]) {
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			Player player = (Player) sender;
			WorldEditSave wes = new WorldEditSave(plugin, eventName);
			wes.save(player);
			sendMessage(sender, ChatColor.LIGHT_PURPLE + "Event " + ChatColor.DARK_AQUA + eventName + ChatColor.LIGHT_PURPLE + " saved.");
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