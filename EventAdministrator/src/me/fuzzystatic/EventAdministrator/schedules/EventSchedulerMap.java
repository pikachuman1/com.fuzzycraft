package me.fuzzystatic.EventAdministrator.schedules;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;

public class EventSchedulerMap {

	private static final Map<Integer, String> map = new HashMap<Integer, String>();

	public void set(Integer id, String eventName) {
		map.put(id, eventName);
	}

	public Map<Integer, String> get() {
		return map;
	}
	
	public ArrayListMultimap<String, Integer> getInvert() {
		return Multimaps.invertFrom(Multimaps.forMap(map), ArrayListMultimap.<String, Integer>create());
	}
}
