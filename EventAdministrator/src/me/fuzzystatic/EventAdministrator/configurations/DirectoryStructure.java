package me.fuzzystatic.EventAdministrator.configurations;

import java.io.File;

import me.fuzzystatic.EventAdministrator.EventAdministrator;

public class DirectoryStructure {
	
	private EventAdministrator plugin;
	
	public DirectoryStructure(EventAdministrator plugin) {
		this.plugin = plugin;
	}
	
	public static final String EVENT_DIR = "events" + File.separator;
	public static final String SCHEMATICS_DIR = "schematics" + File.separator;
	
	public static void createEventDirectory(File directory) {
		new File(directory + File.separator + EVENT_DIR).mkdir();
	}
	
	public static void createSchematicDirectory(File directory) {
		new File(directory + File.separator + SCHEMATICS_DIR).mkdir();
	}
	
	public File eventDirPath() {
		return new File(plugin.getDataFolder() + File.separator + EVENT_DIR);
	}
	
	public File schematicsDirPath() {
		return new File(plugin.getDataFolder() + File.separator + SCHEMATICS_DIR);
	}
	
	public File[] eventFiles() {
		return eventDirPath().listFiles();
	}
	
	public File[] schematicFiles() {
		return schematicsDirPath().listFiles();
	}
}
