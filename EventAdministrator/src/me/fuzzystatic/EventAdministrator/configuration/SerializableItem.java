package me.fuzzystatic.EventAdministrator.configuration;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SerializableItem {
	
	private static final String MAIN_SPLIT = " ";
	private static final String SUB_SPLIT = ":";
	private static final String LORE_SPLIT = ",";	
	private static final String DURABILITY_KEY = "durability" + SUB_SPLIT;
	private static final String DISPLAY_NAME_KEY = "name" + SUB_SPLIT;
	private static final String LORE_KEY = "lore" + SUB_SPLIT;
	private static final String COLOR_CODE_CHAR = "&";	
	
	private final Material type;
	private final Integer amount;
	private final Short durability;
	private final String displayName;
	private final List<String> lore;
	private final Map<Enchantment, Integer> enchants;
	
	private ItemStack item;
	private ItemMeta meta;
	private List<String> loreBuilder;
		
	public SerializableItem(ItemStack item) {
		this.type = item.getType();
        this.amount = item.getAmount();
        this.durability = item.getDurability();
        this.meta = item.getItemMeta();
        this.displayName = meta.getDisplayName();
        this.lore = meta.getLore();
        this.enchants = meta.getEnchants();
	}
	
	public String serialize() {
    	String itemBuilder = null;
        if(type != null) itemBuilder = type.toString();
        if(amount != null) itemBuilder = itemBuilder + " " + amount;
        if(durability != null) itemBuilder = itemBuilder + " " + "durability:" + durability;
        if(displayName != null) itemBuilder = itemBuilder + " " + "name:" + displayName
        		.replaceAll(" ", "_")
        		.replaceAll("¤", COLOR_CODE_CHAR);
        if(lore != null) itemBuilder = itemBuilder + " " + "lore:" + lore.toString()
				.replaceAll("\\, ", "\\,")
				.replaceAll("\\[", "")
				.replaceAll("\\]", "")
				.replaceAll("¤", COLOR_CODE_CHAR);
        if(enchants != null) {
        	 for(Entry<Enchantment, Integer> enchantment : enchants.entrySet()) {
        		 itemBuilder = itemBuilder + " " + enchantment.getKey().getName() + ":" + enchantment.getValue();
        	 }
        }
		return itemBuilder;
	}
	
	public ItemStack deserialize(String string) {
		String[] mainSplit = string.split(MAIN_SPLIT);
		if(mainSplit[0] != null) {
			this.item = new ItemStack(Material.getMaterial(mainSplit[0]));
		}
		if(mainSplit[1] != null) this.item.setAmount(Integer.valueOf(mainSplit[1]));
		this.meta = item.getItemMeta();
		loreBuilder = null;
		for (String subString : mainSplit) {
			if (subString.contains(DURABILITY_KEY)) {
				item.setDurability(Short.valueOf(string.split(SUB_SPLIT)[1]));
			} else if (subString.contains(DISPLAY_NAME_KEY)) {
				meta.setDisplayName(string.split(SUB_SPLIT)[1]
						.replaceAll("_", " ")
						.replaceAll(COLOR_CODE_CHAR, "¤"));
			} else if (subString.contains(LORE_KEY)) {
				for (String loreString : string.split(SUB_SPLIT)) {
					loreBuilder.add(loreString.split(LORE_SPLIT)[1]
							.replaceAll("\\,", "\\, ")
							.replaceAll(COLOR_CODE_CHAR, "¤"));
				}
			} else {	
				this.meta.addEnchant(Enchantment.getByName(subString.split(SUB_SPLIT)[0]), Integer.valueOf(subString.split(SUB_SPLIT)[1]), false);
			}
			this.meta.setLore(loreBuilder);

		}
		this.item.setItemMeta(this.meta);
		return this.item;
    }
}
