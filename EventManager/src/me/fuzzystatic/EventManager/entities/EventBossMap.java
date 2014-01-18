package me.fuzzystatic.EventManager.entities;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;

import me.fuzzystatic.interfaces.EventMap;

public class EventBossMap implements EventMap {
	
	private static final Map<Integer, String> map = new HashMap<Integer, String>();

	@Override
	public void set(Integer id, String eventName) {
		map.put(id, eventName);
	}

	@Override
	public Map<Integer, String> get() {
		return map;
	}
	
	@Override
	public ArrayListMultimap<String, Integer> getInvert() {
		return Multimaps.invertFrom(Multimaps.forMap(map), ArrayListMultimap.<String, Integer>create());
	}
}
