package me.fuzzystatic.EventAdministrator.worldedit;

import java.io.File;

import me.fuzzystatic.EventAdministrator.configuration.structure.DirectoryStructure;
import me.fuzzystatic.EventAdministrator.configuration.structure.EventConfigurationStructure;

import org.bukkit.plugin.java.JavaPlugin;

public class WorldEdit {
	
	protected JavaPlugin plugin;
	protected final DirectoryStructure ds;
	protected final File file;
	protected final EventConfigurationStructure ecs;
			
	public WorldEdit(JavaPlugin plugin, String eventName) {
		this.plugin = plugin;
		this.ds = new DirectoryStructure(plugin);
		this.file = new File(this.ds.getSchematicsDirPath() + File.separator + eventName + ".schematic");
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}	
}
