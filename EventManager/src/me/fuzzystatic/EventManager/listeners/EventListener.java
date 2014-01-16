package me.fuzzystatic.EventManager.listeners;

import me.fuzzystatic.EventManager.EventManager;

import org.bukkit.event.Listener;

public final class EventListener implements Listener {

	public EventManager plugin;
    
	public EventListener(EventManager plugin) {
		this.plugin = plugin;
	}
		
	/*@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {	// Sets up the delay for the player teleport
		Player player = event.getPlayer();
		World world = Bukkit.getWorld(plugin.config.getString(Strings.DC_SAMPLE_EVENT.toString() + ".world"));
		EventEntities eventEntities = new EventEntities(world);
		if(eventEntities.bossAlive()) {
			player.sendMessage(ChatColor.DARK_AQUA + Strings.PLUGIN_NAME.toString() + ChatColor.GREEN + " Dragon event is in progress! Type /warp de or take the portal at spawn to join!");
		}
	}*/
		
	/*@EventHandler
	public void onBossDeath(EntityDeathEvent event) {	// Sets up the delay for the player teleport
		Entity entity = event.getEntity();
		if(entity instanceof EnderDragon) {
			Bukkit.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					World world = Bukkit.getWorld(plugin.config.getString(Strings.EC_PASTE_LOCATION.toString() + ".world"));
					EventEntities eventEntities = new EventEntities(world);
					if(!eventEntities.bossAlive()) {
						Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + Strings.PLUGIN_NAME.toString() + ChatColor.GREEN + " Dragon event is over! Thanks everyone who participated!");
					}	
				}
			}, 500);
		}
	}*/
}