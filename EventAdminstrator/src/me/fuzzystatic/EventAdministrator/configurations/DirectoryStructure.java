package me.fuzzystatic.EventAdministrator.configurations;

import java.io.File;

public class DirectoryStructure {
	public static final String EVENT_DIR = "events" + File.separator;
	public static final String SCHEMATICS_DIR = "schematics" + File.separator;
	
	public static void createEventDirectory(File directory) {
		new File(directory + File.separator + EVENT_DIR).mkdir();
	}
	
	public static void createSchematicDirectory(File directory) {
		new File(directory + File.separator + SCHEMATICS_DIR).mkdir();
	}
}
