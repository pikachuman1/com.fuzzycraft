package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;
import me.fuzzystatic.EventAdministrator.utilities.WorldEditSession;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EmptyClipboardException;

public class EventSave extends Command {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) {
		String eventName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			Player player = (Player) sender;
    		SerializableLocation sl = new SerializableLocation(player.getLocation());
			try {
				WorldEditSession worldEditSession = new WorldEditSession(plugin, player);
				worldEditSession.saveSchematic(eventName);
			} catch (EmptyClipboardException e) {
				player.sendMessage(ChatColor.LIGHT_PURPLE + "Clipboard is empty.");
			}
    		ecs.setPasteLocation(sl.serialize());
			return true;
		} else {
			playerOnly(sender);
			return false;
		}
	}

	@Override
	public String permissionNode() {
		return "eventadministrator.save";
	}

	@Override
	public String usage() {
		return ChatColor.LIGHT_PURPLE + "/ea save";
	}
}