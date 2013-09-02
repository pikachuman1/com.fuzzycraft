package me.fuzzystatic.TutorialSpawn;

import java.io.File;

import me.fuzzystatic.TutorialSpawn.utils.YMLLocation;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TutorialSpawn extends JavaPlugin {
	
	private TSPlayerListener tspl = new TSPlayerListener(this);
	
	public FileConfiguration config;
	
	private String world = "";
	private double x, y, z;
	private float yaw, pitch;
	
	public final String tsMarker = "[TutorialSpawn]";
	
	public final String phraseYml = "tutorialspawn.passphrase";
	public final String defaultPhrase = "putwhateverphraseyoulike";
	
	public final String maxJoinEventsYml = "tutorialspawn.maxJoinEvents";
	public final int defaultMaxJoinEvents = -1;
	
	public final String reusePassphraseYml = "tutorialspawn.reusePassphrase";
	public final boolean reusePassphrase = false;
	
	public final String spawnYml = "tutorialspawn.spawn";
	public final String exitYml = "tutorialspawn.exit";
	
	public final String teleportDelayYml = "tutorialspawn.teleportDelay";
	public final double defaultTeleportDelay = 0.5;
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.tspl, this);
				
		config = getConfig();
		
		File UserData = new File(getDataFolder() + File.separator + "userdata");
		UserData.mkdir();

		try{			
			if(!config.contains(phraseYml)) {
				config.set(phraseYml, defaultPhrase);
			}
			if(!config.contains(maxJoinEventsYml)) {
				config.set(maxJoinEventsYml, defaultMaxJoinEvents);
			}	
			if(!config.contains(reusePassphraseYml)) {
				config.set(reusePassphraseYml, reusePassphrase);
			}
			if(!config.contains(spawnYml)) {
				new YMLLocation().setBlankLocation(config, spawnYml);
			}		
			if(!config.contains(exitYml)) {
				new YMLLocation().setBlankLocation(config, exitYml);
			}	
			if(!config.contains(teleportDelayYml)) {
				config.set(teleportDelayYml, defaultTeleportDelay);
			}
			saveConfig();
		} catch(Exception e1){
			e1.printStackTrace();
		}
		
		getCommand("tsphrase").setExecutor(new TSCommands(this));
		getCommand("tssetphrase").setExecutor(new TSCommands(this));
		getCommand("tsgetphrase").setExecutor(new TSCommands(this));
		getCommand("tsspawn").setExecutor(new TSCommands(this));
		getCommand("tssetspawn").setExecutor(new TSCommands(this));
		getCommand("tsexit").setExecutor(new TSCommands(this));
		getCommand("tssetexit").setExecutor(new TSCommands(this));
		getCommand("tssetmje").setExecutor(new TSCommands(this));
		getCommand("tsgetmje").setExecutor(new TSCommands(this));
		getCommand("tssetrp").setExecutor(new TSCommands(this));
		getCommand("tsgetrp").setExecutor(new TSCommands(this));
		getCommand("tssettd").setExecutor(new TSCommands(this));
		getCommand("tsgettd").setExecutor(new TSCommands(this));
	}
}