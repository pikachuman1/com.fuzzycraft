package me.fuzzystatic.KitPvP;

import java.util.HashMap;

public class PlayerKits {
	private static final HashMap<String, String> playerKits = new HashMap<String, String>();
		
    public PlayerKits() {
    }
 
    public void setKit(String player, String kit) {
        playerKits.put(player, kit);
    }
 
    public String getKit(String player) {
        if (playerKits.containsKey(player)) {
            return playerKits.get(player);
        }
        return null;
    }
}

