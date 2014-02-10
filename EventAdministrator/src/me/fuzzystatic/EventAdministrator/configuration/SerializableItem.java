package me.fuzzystatic.EventAdministrator.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SerializableItem implements ConfigurationSerializable {
	
	private static final String KEY = "item";	
	private static final String MAIN_SPLIT = " ";
	private static final String SUB_SPLIT = ":";
	private static final String LORE_SPLIT = ",";	
	private static final String DISPLAY_NAME_KEY = "name" + SUB_SPLIT;
	private static final String LORE_KEY = "lore" + SUB_SPLIT;
	private static final String COLOR_CODE_CHAR = "&";	
	
	private final ItemStack item;
	private final Object type, amount, displayName, lore, enchants;

	private ItemStack itemBuilder;
	private ItemMeta meta;
	private StringBuilder displayNameBuilder;
	private List<String> loreBuilder;
	private Map<String, String> enchantsBuilder;

	public SerializableItem(ItemStack item) {
		this.type = item.serialize().get("type");
		this.amount = item.serialize().get("amount");
		this.meta = (ItemMeta) item.serialize().get("meta");
		this.displayName = meta.serialize().get("display-name")
				.toString().replaceAll(" ", "_")
				.replaceAll("¤", COLOR_CODE_CHAR);
		this.lore = meta.serialize().get("lore").toString()
				.replaceAll("\\, ", "\\,")
				.replaceAll("\\[", "")
				.replaceAll("\\]", "")
				.replaceAll("¤", COLOR_CODE_CHAR);
		this.enchants = meta.serialize().get("enchants").toString()
				.replaceAll("\\, ", " ")
				.replaceAll("\\=", "\\:")
				.replaceAll("\\{", "")
				.replaceAll("\\}", "");
		this.item = item;
	}

	public SerializableItem(Map<String, Object> map) {
		String[] mainSplit = map.get(KEY).toString().split(MAIN_SPLIT);
		this.type = mainSplit[0];
		this.amount = mainSplit[1];
		for (String string : mainSplit) {
			if (string.contains(DISPLAY_NAME_KEY)) {
				this.displayNameBuilder.append(string.split(SUB_SPLIT)[1]
						.replaceAll("_", " ")
						.replaceAll(COLOR_CODE_CHAR, "¤"));
			} else if (string.contains(LORE_KEY)) {
				for (String loreString : string.split(SUB_SPLIT)) {
					this.loreBuilder.add(loreString.split(LORE_SPLIT)[1]
							.replaceAll("\\,", "\\, ")
							.replaceAll(COLOR_CODE_CHAR, "¤"));
				}
			} else {	
				this.enchantsBuilder.put(string.split(SUB_SPLIT)[0], string.split(SUB_SPLIT)[1]);
			}
		}
		this.displayName = this.displayNameBuilder.toString();	
		this.lore =	this.loreBuilder;
		this.enchants = this.enchantsBuilder;
		
		// Build meta
		this.meta.setDisplayName(this.displayNameBuilder.toString());
		this.meta.setLore(this.loreBuilder);
		Iterator<Entry<String, String>> iterator = this.enchantsBuilder.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<String, String> pair = (Map.Entry<String, String>)iterator.next();
	        this.meta.addEnchant(Enchantment.getByName(pair.getKey()), Integer.valueOf(pair.getValue()), false);
	        iterator.remove();
	    }
	    
	    // Build item
		this.itemBuilder.setType((Material) type);
		this.itemBuilder.setAmount((int) amount);
		this.itemBuilder.setItemMeta(meta);
		this.item = this.itemBuilder;
    }

	@Override
	public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put(KEY, type + " " +  
				amount + " " +
				"name:" + displayName + " " +
				"lore:" + lore + " " +
				enchants);
		return map;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
}
