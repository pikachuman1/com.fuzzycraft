package me.fuzzystatic.EventAdministrator.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SerializableItem implements ConfigurationSerializable {
	
	private static final String KEY = "item";
	
	private static final String MAIN_SPLIT = " ";
	private static final String SUB_SPLIT = ":";
	
	private static final String DISPLAY_NAME_KEY = "name" + SUB_SPLIT;
	private static final String LORE_KEY = "lore" + SUB_SPLIT;
	
	private final Object type, amount, displayName, lore, enchants;

	private StringBuilder displayNameBuilder, loreBuilder, enchantsBuilder;

	public SerializableItem(ItemStack item) {
		this.type = item.serialize().get("type");
		this.amount = item.serialize().get("amount");
		ItemMeta meta = (ItemMeta) item.serialize().get("meta");
		this.displayName = meta.serialize().get("display-name").toString().replaceAll(" ", "_");
		this.lore = meta.serialize().get("lore").toString().replaceAll("\\, ", "\\;").replaceAll("\\[", "").replaceAll("\\]", "");
		this.enchants = meta.serialize().get("enchants").toString().replaceAll("\\=", "\\:").replaceAll("\\{", "").replaceAll("\\}", "");
    }
	
	public SerializableItem(Map<String, Object> map) {
		String[] mainSplit = map.get(KEY).toString().split(MAIN_SPLIT);
		this.type = mainSplit[0];
		this.amount = mainSplit[1];
		for (String string : mainSplit) {
			if (string.contains(DISPLAY_NAME_KEY)) {
				displayNameBuilder.append(string.split(SUB_SPLIT)[1]);
			} else if (string.contains(LORE_KEY)) {
				loreBuilder.append(string.split(SUB_SPLIT)[1]);
			} else {	
				enchantsBuilder.append(string);
			}
		}
		this.displayName = displayNameBuilder.toString().replaceAll("_", " ");
		this.lore =	"[" + loreBuilder.toString().replaceAll("\\;", "\\, ") + "]";
		this.enchants =	"{"+ enchantsBuilder.toString().replaceAll("\\:", "\\=") + "}";	
    }

	@Override
	public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put(KEY, 
				type + " " +  
				amount + " " +
				"name:" + displayName + " " +
				"lore:" + lore + " " +
				enchants);
		return map;
	}

	
	/*  inventory:
    type: BOW
    amount: 64
    meta:
      ==: ItemMeta
      meta-type: UNSPECIFIC
      display-name: It's a Fancy Name
      lore:
      - Special
      - Things
      - Can
      - Go
      - Here
      enchants:
        ARROW_DAMAGE: 1
*/
}
