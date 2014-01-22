package me.fuzzystatic.EventAdministrator.commands.events;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConsoleLogs;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CycleTime {
	
	private EventAdministrator plugin;
	private final EventConfigurationStructure ecs;
	private final CommandSender sender;
	private final String args[];

	public CycleTime(EventAdministrator plugin, CommandSender sender, String args[]) {
		this.plugin = plugin;
		this.ecs = new EventConfigurationStructure(this.plugin, EventName.getName());	
		this.sender = sender;
		this.args = args;
	}
	
	public void runCommand() {
		this.ecs.createFileStructure();
		if (this.args.length > 0) {		
			this.ecs.setCycleTime(Long.valueOf(this.args[0]));
		} else {
			this.ecs.getCycleTime();
		}
		sendMessage();
	}
	
	public String permissionNode() {
		return "eventadministrator.cycle";
	}
	
	public String message() {	
		return ChatColor.LIGHT_PURPLE + "New event cycle set to " + ChatColor.DARK_AQUA + args[0] + ".";
	}
	
	public boolean isPlayer() {
		if (this.sender instanceof Player) {
			return true;	
		}
		return false;	
	}
	
	public void sendMessage() {
		if (isPlayer()) {
			this.sender.sendMessage(message());
		} else {
			ConsoleLogs.sendMessage(message());
		}
	}
}
