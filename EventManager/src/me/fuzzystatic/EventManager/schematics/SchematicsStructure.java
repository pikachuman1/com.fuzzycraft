package me.fuzzystatic.EventManager.schematics;

import java.io.File;

public class SchematicsStructure {

	public static final String DIRECTORY = "schematics";
	
	public static void createDirectoryStructure(File file) {
		File EventDir = new File(file, DIRECTORY);
		EventDir.mkdir();
	}
}
