package me.fuzzystatic.EventAdministrator.commands.event;

import me.fuzzystatic.EventAdministrator.commands.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class EventReminder extends Command {
	
	@Override
	public boolean runCommand(JavaPlugin plugin, CommandSender sender, String[] args) {
		if(args.length > 1) {
			switch(args[1]) {
			case "c" 			: new EventReminderCycleTime().runCommand(plugin, sender, args); break;
			case "cycle" 		: new EventReminderCycleTime().runCommand(plugin, sender, args); break;
			case "m" 			: new EventReminderMessage().runCommand(plugin, sender, args); break;
			case "msg" 			: new EventReminderMessage().runCommand(plugin, sender, args); break;
			case "message" 		: new EventReminderMessage().runCommand(plugin, sender, args); break;
			}
			return true;
		} else {
			sender.sendMessage(new EventReminderCycleTime().usage());
			sender.sendMessage(new EventReminderMessage().usage());
			return true;
		}
	}
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("reminder");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	@Override
	public String usage() {
		return super.usage() + " rem{inder}";
	}

}
