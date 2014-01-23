package me.fuzzystatic.EventAdministrator.entities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;

public class CommandSenderSpawnMap {

	private static final Map<CommandSender, String> map = new HashMap<CommandSender, String>();

	public void set(CommandSender name, String eventName) {
		map.put(name, eventName);
	}

	public Map<CommandSender, String> get() {
		return map;
	}
	
	public ArrayListMultimap<String, CommandSender> getInvert() {
		return Multimaps.invertFrom(Multimaps.forMap(map), ArrayListMultimap.<String, CommandSender>create());
	}
}
