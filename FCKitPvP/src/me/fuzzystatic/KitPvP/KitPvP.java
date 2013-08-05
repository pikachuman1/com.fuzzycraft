package me.fuzzystatic.KitPvP;

import me.fuzzystatic.KitPvP.kits.*;
import me.fuzzystatic.KitPvP.kits.acquired.*;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPvP extends JavaPlugin {
	
	private PlayerListener pl = new PlayerListener(this);
	private MagicListener ml = new MagicListener(this);
	
	public void onEnable(){
		getConfig().options().copyDefaults(true);
        saveConfig();
        
        PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);
		pm.registerEvents(this.ml, this);

		getCommand("kit").setExecutor(new KitPermCheck(this));
		getCommand("kits").setExecutor(new KitPermCheck(this));
		
		getCommand("squire").setExecutor(new Squire(this));
		getCommand("pvp").setExecutor(new Squire(this));
		getCommand("archer").setExecutor(new Archer(this));
		getCommand("streaker").setExecutor(new Streaker(this));
		getCommand("knight").setExecutor(new Knight(this));
		getCommand("tank").setExecutor(new Knight(this));
		getCommand("chemist").setExecutor(new Chemist(this));	
		getCommand("lumberjack").setExecutor(new Lumberjack(this));
		getCommand("lj").setExecutor(new Lumberjack(this));
		getCommand("ninja").setExecutor(new Ninja(this));	
		getCommand("mystic").setExecutor(new Mystic(this));
		getCommand("king").setExecutor(new King(this));
		getCommand("firemage").setExecutor(new FireMage(this));
		getCommand("fm").setExecutor(new FireMage(this));
		getCommand("reaper").setExecutor(new Reaper(this));
		getCommand("fireomen").setExecutor(new FireOmen(this));
		getCommand("fo").setExecutor(new FireOmen(this));
		getCommand("monk").setExecutor(new Monk(this));
	}
}

