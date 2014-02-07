package me.fuzzystatic.EventAdministrator.maps;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;

public class BossEventMap {
	
	private static final Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public void set(Integer id, Integer eventID) {
		map.put(id, eventID);
	}

	public Map<Integer, Integer> get() {
		return map;
	}
	
	public ArrayListMultimap<Integer, Integer> getInvert() {
		return Multimaps.invertFrom(Multimaps.forMap(map), ArrayListMultimap.<Integer, Integer>create());
	}
}
