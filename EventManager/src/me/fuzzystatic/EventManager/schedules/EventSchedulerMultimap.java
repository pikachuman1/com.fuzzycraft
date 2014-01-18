package me.fuzzystatic.EventManager.schedules;

import me.fuzzystatic.interfaces.EventMultimap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class EventSchedulerMultimap implements EventMultimap {

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
