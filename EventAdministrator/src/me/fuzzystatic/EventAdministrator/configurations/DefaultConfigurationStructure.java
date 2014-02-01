package me.fuzzystatic.EventAdministrator.configurations;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConfigAccessor;

public class DefaultConfigurationStructure implements FileStructure {

	public static final String MYSQL = "mysql";
	public static final String MYSQL_HOST = MYSQL + "." + "host";
	public static final String MYSQL_PORT = MYSQL + "." + "port";
	public static final String MYSQL_USER = MYSQL + "." + "user";
	public static final String MYSQL_PASSWORD = MYSQL + "." + "password";
	public static final String MYSQL_DATABASE = MYSQL + "." + "database";
	public static final String MYSQL_PREFIX = MYSQL + "." + "prefix";
	public static final String WORLD_EDIT_BOOLEAN = "useWESchematicDir";
	
	private static final String defaultMySQLHost = "localhost";
	private static final int defaultMySQLPort = 3306;
	private static final String defaultMySQLUser = "user";
	private static final String defaultMySQLPassword = "pass";
	private static final String defaultMySQLDatabase = "db";
	private static final String defaultMySQLPrefix = "EA_";
	private static final boolean defaultUseWESchematicDir = false;
	
	private final ConfigAccessor configAccessor;
	private final FileConfiguration config;
	
	public DefaultConfigurationStructure(JavaPlugin plugin) {
		if (!plugin.isInitialized()) throw new IllegalArgumentException("Plugin must be initialized!");
		this.configAccessor = new ConfigAccessor(plugin, plugin.getDataFolder() + File.separator + "config.yml");
		this.config = configAccessor.getConfig();
	}
	
	private void setMySQLHost(String mySQLHost) {
		this.config.set(MYSQL_HOST, mySQLHost);
		this.configAccessor.saveConfig();
	}
	
	private void setMySQLPort(int mySQLPort) {
		this.config.set(MYSQL_PORT, mySQLPort);
		this.configAccessor.saveConfig();
	}
	
	private void setMySQLUser(String mySQLUser) {
		this.config.set(MYSQL_USER, mySQLUser);
		this.configAccessor.saveConfig();
	}
	
	private void setMySQLPassword(String mySQLPassword) {
		this.config.set(MYSQL_PASSWORD, mySQLPassword);
		this.configAccessor.saveConfig();
	}
	
	private void setMySQLDatabase(String mySQLDatabase) {
		this.config.set(MYSQL_DATABASE, mySQLDatabase);
		this.configAccessor.saveConfig();
	}
	
	private void setMySQLPrefix(String mySQLPrefix) {
		this.config.set(MYSQL_PREFIX, mySQLPrefix);
		this.configAccessor.saveConfig();
	}
	
	private void setUseWESchematicDir(boolean useWESchematicDir) {
		this.config.set(WORLD_EDIT_BOOLEAN, useWESchematicDir);
		this.configAccessor.saveConfig();
	}
	
	@Override
	public boolean createFileStructure() {
		boolean configAltered = false;
		if(this.config.get(MYSQL_HOST) == null) {
			setMySQLHost(defaultMySQLHost);
			configAltered = true;
		}
		if(this.config.get(MYSQL_PORT) == null) {
			setMySQLPort(defaultMySQLPort);
			configAltered = true;
		}
		if(this.config.get(MYSQL_USER) == null) {
			setMySQLUser(defaultMySQLUser);
			configAltered = true;
		}
		if(this.config.get(MYSQL_PASSWORD) == null) {
			setMySQLPassword(defaultMySQLPassword);
			configAltered = true;
		}
		if(this.config.get(MYSQL_DATABASE) == null) {
			setMySQLDatabase(defaultMySQLDatabase);
			configAltered = true;
		}
		if(this.config.get(MYSQL_PREFIX) == null) {
			setMySQLPrefix(defaultMySQLPrefix);
			configAltered = true;
		}
		if(this.config.get(WORLD_EDIT_BOOLEAN) == null) {
			setUseWESchematicDir(defaultUseWESchematicDir);
			configAltered = true;
		}
		if (configAltered) {
			this.configAccessor.saveConfig();
		}
		return configAltered;
	}
	
	public String getMySQLHost() {
		return config.getString(MYSQL_HOST);
	}
	
	public int getMySQLPort() {
		return config.getInt(MYSQL_PORT);
	}
	
	public String getMySQLUser() {
		return config.getString(MYSQL_USER);
	}
	
	public String getMySQLPassword() {
		return config.getString(MYSQL_PASSWORD);
	}
	
	public String getMySQLDatabase() {
		return config.getString(MYSQL_DATABASE);
	}
	
	public String getMySQLPrefix() {
		return config.getString(MYSQL_PREFIX);
	}
	
	public boolean getUseWESchematicDir() {
		return config.getBoolean(WORLD_EDIT_BOOLEAN);
	}
}
