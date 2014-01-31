package me.fuzzystatic.EventAdministrator.configurations;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class DirectoryStructure {
	
	private JavaPlugin plugin;
	private final DefaultConfigurationStructure dcs;
	private final String EVENT_DIR;
	
	public DirectoryStructure(JavaPlugin plugin) {
		if (!plugin.isInitialized()) throw new IllegalArgumentException("Plugin must be initialized!");
		this.plugin = plugin;
		this.dcs = new DefaultConfigurationStructure(plugin);
		this.EVENT_DIR = plugin.getDataFolder() + File.separator + "events" + File.separator;
	}
		
	public String getSchematicDir() {
		if (this.dcs.getUseWESchematicDir()) {
			return plugin.getDataFolder().getParent() + File.separator + "WorldEdit" + File.separator + "schematics" + File.separator;
		} else {
			return plugin.getDataFolder() + File.separator + "schematics" + File.separator;
		}
	}
	
	public void createEventDirectory() {
		new File(EVENT_DIR).mkdir();
	}
	
	public void createSchematicDirectory() {
		new File(getSchematicDir()).mkdir();
	}
	
	public File getEventDirPath() {
		return new File(EVENT_DIR);
	}
	
	public File getSchematicsDirPath() {
		return new File(getSchematicDir());
	}
	
	public File[] eventFiles() {
		return getEventDirPath().listFiles();
	}
	
	public File[] schematicFiles() {
		return getSchematicsDirPath().listFiles();
	}
}
