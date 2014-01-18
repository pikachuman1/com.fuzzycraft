package me.fuzzystatic.EventManager.entities;

import org.bukkit.entity.Entity;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class EventBossMultimap {
	
	private static final Multimap<String, Entity> multimap = ArrayListMultimap.create();
	
	public static void setBosses(String eventName, Entity entity) {
		multimap.put(eventName, entity);
	}
	
	public static Multimap<String, Entity> getBosses() {
		return multimap;
	}
}
