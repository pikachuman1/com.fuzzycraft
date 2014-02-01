package me.fuzzystatic.EventAdministrator.maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;

public class SchedulerEventMap {

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
	
	public Set<String> getUniqueValues() {
		return new HashSet<String>(new SchedulerEventMap().get().values());
	}

}
