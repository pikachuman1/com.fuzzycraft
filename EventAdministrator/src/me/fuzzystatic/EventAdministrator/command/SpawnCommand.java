package me.fuzzystatic.EventAdministrator.command;

import org.bukkit.permissions.Permission;

public class SpawnCommand extends Command {

	@Override
	public Permission permission() {
		Permission permission = new Permission("spawn");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	public String usage() {
		return super.usage() + " s";
	}
}
