package me.fuzzystatic.EventAdministrator.command;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.event.EventReminderCycleTime;
import me.fuzzystatic.EventAdministrator.commands.event.EventReminderMessage;
import me.fuzzystatic.EventAdministrator.commands.list.EventList;
import me.fuzzystatic.EventAdministrator.commands.list.SpawnList;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class ReminderCommand extends Command {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String[] args) {
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
			sender.sendMessage(new EventList().usage());
			sender.sendMessage(new SpawnList().usage());
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
