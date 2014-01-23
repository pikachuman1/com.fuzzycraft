package me.fuzzystatic.EventAdministrator.commands.event.spawn;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.command.Command;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configurations.SpawnConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.CommandSenderEventMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnLocation extends Command {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String args[]) { 
		String eventName = new CommandSenderEventMap().get().get(sender);
		String spawnName = new CommandSenderEventMap().get().get(sender);
		EventConfigurationStructure ecs = new EventConfigurationStructure(plugin, eventName);	
		ecs.createFileStructure();
		SpawnConfigurationStructure scs = new SpawnConfigurationStructure(plugin, eventName, spawnName);
		scs.createFileStructure();
		if (hasPermissionNode(sender) && isPlayer(sender)) {
			Player player = (Player) sender;
	   		scs.setLocation(player.getLocation());
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "New spawn location set.");
			return true;
		}
		return false;
	}
	
	@Override
	public String permissionNode() {
		return "eventadministrator.spawns.location";
	}
	
	@Override
	public String usage() {
		return ChatColor.LIGHT_PURPLE + "/ea s loc";
	}
}