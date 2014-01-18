package me.fuzzystatic.EventManager.entities;

import me.fuzzystatic.interfaces.EventMultimap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class EventBossMultimap implements EventMultimap {
	
	private static final Multimap<String, Integer> multimap = ArrayListMultimap.create();
	
	@Override
	public void set(String eventName, Integer integer) {
		multimap.put(eventName, integer);
	}

	@Override
	public Multimap<String, Integer> get() {
		return multimap;
	}
	
	@Override
	public Multimap<Integer, String> getInvert() {
		Multimap<Integer, String> invertedMultimap = Multimaps.invertFrom(multimap, ArrayListMultimap.<Integer, String>create());
		return invertedMultimap;
	}
}
