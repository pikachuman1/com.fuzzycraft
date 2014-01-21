package me.fuzzystatic.EventAdministrator.configurations;

import org.bukkit.configuration.file.FileConfiguration;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConfigAccessor;

public class DefaultConfigurationStructure implements FileStructure {

	public static final String WORLD_EDIT_BOOLEAN = "useWESchematicDir";
	
	private static final boolean defaultUseWESchematicDir = false;
	
	private final ConfigAccessor configAccessor;
	private final FileConfiguration config;
	
	public DefaultConfigurationStructure(EventAdministrator plugin, String eventName) {
		this.configAccessor = new ConfigAccessor(plugin, DirectoryStructure.EVENT_DIR + eventName + ".yml");
		this.config = configAccessor.getConfig();
	}
	
	private void setUseWESchematicDir(boolean useWESchematicDir) {
		this.config.set(WORLD_EDIT_BOOLEAN, useWESchematicDir);
		this.configAccessor.saveConfig();
	}
	
	@Override
	public void createFileStructure() {
		if(this.config.get(WORLD_EDIT_BOOLEAN) == null) setUseWESchematicDir(defaultUseWESchematicDir);
	}
}
