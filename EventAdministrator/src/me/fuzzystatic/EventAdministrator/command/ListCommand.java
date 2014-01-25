package me.fuzzystatic.EventAdministrator.command;

import org.bukkit.permissions.Permission;

public class ListCommand extends Command {
	
	@Override
	public Permission permission() {
		Permission permission = new Permission("list");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	public String usage() {
		return super.usage() + " list";
	}
}
