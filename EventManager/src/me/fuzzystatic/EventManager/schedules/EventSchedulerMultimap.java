package me.fuzzystatic.EventManager.schedules;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class EventSchedulerMultimap {

	private static final Multimap<String, Integer> multimap = ArrayListMultimap.create();
	
	public static void setBosses(String eventName, int integer) {
		multimap.put(eventName, integer);
	}
	
	public static Multimap<String, Integer> getBosses() {
		return multimap;
	}
}
