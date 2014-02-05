package me.fuzzystatic.EventAdministrator.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
 

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
 
public class ConfigAccessor {
 
    private final String filename;
    private final JavaPlugin plugin;
 
    private File configFile;
    private FileConfiguration fileConfiguration;
 
    public ConfigAccessor(JavaPlugin plugin, String filename) {
		if (!plugin.isInitialized()) throw new IllegalArgumentException("Plugin must be initialized!");
        this.plugin = plugin;
        this.filename = filename;
    }
 
    public void reloadConfig() {
        if (configFile == null) configFile = new File(filename);
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
 
        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResource(filename);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fileConfiguration.setDefaults(defConfig);
        }
    }
 
    public FileConfiguration getConfig() {
        if (fileConfiguration == null) this.reloadConfig();
        return fileConfiguration;
    }
 
    public void saveConfig() {
    	if (fileConfiguration != null || configFile != null) {
            try {
                getConfig().save(configFile);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
            }
        }
    }
 
    public void saveDefaultConfig() {
        if (!configFile.exists()) this.plugin.saveResource(filename, false);
    }
 
}