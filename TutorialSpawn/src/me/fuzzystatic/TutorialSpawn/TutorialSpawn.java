package me.fuzzystatic.TutorialSpawn;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TutorialSpawn extends JavaPlugin {
	
	private TSPlayerListener tspl = new TSPlayerListener(this);
	
	FileConfiguration config;
	
	private String world = "";
	private double x, y, z;
	private float yaw, pitch;
	
	public String tsMarker = "[TutorialSpawn]";
	public String spawnYml = "tutorialspawn.spawn";
	public String exitYml = "tutorialspawn.exit";
	public String phraseYml = "tutorialspawn.passphrase";
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.tspl, this);
				
		config = getConfig();
		
		File UserData = new File(getDataFolder() + File.separator + "userdata");
		UserData.mkdir();

		try{			
			if(!config.contains(phraseYml)) {
				config.set(phraseYml, "putwhateverphraseyoulike");
			}	
			if(!config.contains(spawnYml)) {
				config.set(spawnYml + ".world", world);
				config.set(spawnYml + ".x", x);
				config.set(spawnYml + ".y", y);
				config.set(spawnYml + ".z", z);
				config.set(spawnYml + ".yaw", yaw);
				config.set(spawnYml + ".pitch", pitch);
			}		
			if(!config.contains(exitYml)) {
				config.set(exitYml + ".world", world);
				config.set(exitYml + ".x", x);
				config.set(exitYml + ".y", y);
				config.set(exitYml + ".z", z);
				config.set(exitYml + ".yaw", yaw);
				config.set(exitYml + ".pitch", pitch);
			}	
			saveConfig();
		} catch(Exception e1){
			e1.printStackTrace();
		}
		
		getCommand("ts").setExecutor(new TSCommands(this));
	}
}