package me.fuzzystatic.interfaces;

import com.google.common.collect.Multimap;

public interface EventMultimap {
		
	public void set(String eventName, Integer integer);

	public Multimap<String, Integer> get();
	
	public Multimap<Integer, String> getInvert();

}
