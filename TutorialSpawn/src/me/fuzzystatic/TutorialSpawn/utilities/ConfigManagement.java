package me.fuzzystatic.TutorialSpawn.utilities;

import java.io.File;
import java.io.IOException;
 
import me.fuzzystatic.TutorialSpawn.TutorialSpawn;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
 
public class ConfigManagement {
	
	public TutorialSpawn plugin;
    
	public ConfigManagement(TutorialSpawn plugin) {
		this.plugin = plugin;
	}
	 
    public FileConfiguration createConfig(String name) {
        String pluginDir = plugin.getDataFolder().toString();
        File dir = new File(pluginDir);
        // this is optional. Only needed if you want to put it into a
        // sub-directory. If not, and you just want to put your file in the plugin
        // data folder, no need to add "+File.separator+subdirectory" to the
        // constructor parameter. You can remove the second parameter even. If
        // you want to get fancy, check for null, or overload the method.
        if (!dir.exists()) {
        	dir.mkdir();
        }
        if (!name.endsWith(".yml")) {
        	name = name + ".yml";
        }
        File file = new File(dir, name); // initializes the file object
        if (!file.exists()) {
        	try {
        		file.createNewFile();
        	} catch (IOException e) {
        		e.printStackTrace();
        	} // creates a new file if it doesn't exist
        }
        return YamlConfiguration.loadConfiguration(file); // returns the newly created configuration object.
	}
 
	public void saveConfig(String name) {
		FileConfiguration config = getConfig(name);
		String pluginDir = plugin.getDataFolder().toString();
		File dir = new File(pluginDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!name.endsWith(".yml")) {
			name = name + ".yml";
		}
		File file = new File(dir, name);
		YamlConfiguration.loadConfiguration(file);
		// saves to file
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public FileConfiguration getConfig(String name) {
		String pluginDir = plugin.getDataFolder().toString();
		File dir = new File(pluginDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!name.endsWith(".yml")) {
			name = name + ".yml";
		}
		File file = new File(dir, name);
		if (!file.exists()) {
			createConfig(name);
		}
		return YamlConfiguration.loadConfiguration(file); // file found, load into config and return it.
	}
}