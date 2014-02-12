package me.fuzzystatic.EventAdministrator.schedules;

import me.fuzzystatic.EventAdministrator.configuration.serializable.DeserializableItemString;
import me.fuzzystatic.EventAdministrator.configuration.structure.EventConfigurationStructure;
import me.fuzzystatic.EventAdministrator.configuration.structure.PlayerItemsConfigurationStructure;
import me.fuzzystatic.EventAdministrator.entities.Entities;
import me.fuzzystatic.EventAdministrator.maps.SchedulerEventMap;
import me.fuzzystatic.EventAdministrator.worldedit.WorldEditLoad;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerItems {
	
	private JavaPlugin plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	private final PlayerItemsConfigurationStructure pics;
	
	public PlayerItems(JavaPlugin plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
		this.pics = new PlayerItemsConfigurationStructure(plugin, eventName);

	}
	
	public void start() {
		int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				WorldEditLoad wel = new WorldEditLoad(plugin, eventName);
				Entities eventEntities = new Entities(ecs.getPasteLocation().getWorld(), wel.getClipboard().getOrigin(), wel.getClipboard().getSize());
				for (Player player : eventEntities.getPlayers()) {
					PlayerInventory inventory = player.getInventory();
					DeserializableItemString dis = new DeserializableItemString();
					if (inventory.getHelmet() == null && pics.hasItem(PlayerItemsConfigurationStructure.HELMET)) inventory.setHelmet(dis.deserialize(pics.getItem(PlayerItemsConfigurationStructure.HELMET)));
					if (inventory.getChestplate() == null && pics.hasItem(PlayerItemsConfigurationStructure.CHESTPLATE)) inventory.setChestplate(dis.deserialize(pics.getItem(PlayerItemsConfigurationStructure.CHESTPLATE)));
					if (inventory.getLeggings() == null && pics.hasItem(PlayerItemsConfigurationStructure.LEGGINGS)) inventory.setLeggings(dis.deserialize(pics.getItem(PlayerItemsConfigurationStructure.LEGGINGS)));
					if (inventory.getBoots() == null && pics.hasItem(PlayerItemsConfigurationStructure.BOOTS)) inventory.setBoots(dis.deserialize(pics.getItem(PlayerItemsConfigurationStructure.BOOTS)));
					for (Object object : pics.getInventoryItems()) inventory.addItem(dis.deserialize(object.toString()));
				}	
			}
		}, pics.getInventoryStartTime() * 20, pics.getInventoryCycleTime() * 20);
		SchedulerEventMap esm = new SchedulerEventMap();
		esm.set(id, eventName);
	}
}
