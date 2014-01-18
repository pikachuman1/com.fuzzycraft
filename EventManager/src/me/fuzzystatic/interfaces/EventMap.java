package me.fuzzystatic.interfaces;

import java.util.Map;

import com.google.common.collect.ArrayListMultimap;

public interface EventMap {
		
	public void set(Integer integer, String string);

	public Map<Integer, String> get();
	
	public ArrayListMultimap<String, Integer> getInvert();
}
