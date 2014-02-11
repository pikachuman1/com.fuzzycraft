package me.fuzzystatic.EventAdministrator.configuration;

import java.util.ArrayList;
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
	private static final String COLOR_CODE_CHAR = "§";	
	private static final String REPLACEMENT_COLOR_CODE_CHAR = "&";	
	
	private final Material type;
	private final Integer amount;
	private final Short durability;
	private final String displayName;
	private final List<String> lore;
	private final Map<Enchantment, Integer> enchants;
	
	private ItemStack item;
	private ItemMeta meta;
	private String itemBuilder = new String();
	private List<String> loreBuilder = new ArrayList<String>();
		
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
        if(this.type != null) this.itemBuilder = this.type.toString();
        if(this.amount != null) this.itemBuilder = this.itemBuilder + " " + this.amount;
        if(this.durability != null) this.itemBuilder = this.itemBuilder + " " + "durability:" + this.durability;
        if(this.displayName != null) this.itemBuilder = this.itemBuilder + " " + "name:" + this.displayName.replaceAll(" ", "_").replaceAll(COLOR_CODE_CHAR, REPLACEMENT_COLOR_CODE_CHAR);
        if(this.lore != null) this.itemBuilder = this.itemBuilder + " " + "lore:" + this.lore.toString().replaceAll("\\, ", "\\,").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "_").replaceAll(COLOR_CODE_CHAR, REPLACEMENT_COLOR_CODE_CHAR);
        if(this.enchants != null) {
        	 for(Entry<Enchantment, Integer> enchantment : this.enchants.entrySet()) {
        		 this.itemBuilder = this.itemBuilder + " " + enchantment.getKey().getName() + ":" + enchantment.getValue();
        	 }
        }
		return this.itemBuilder;
	}
	
	public ItemStack deserialize(String string) {
		String[] array = string.split(MAIN_SPLIT);
		this.item = new ItemStack(Material.getMaterial(array[0]));
		this.item.setAmount(Integer.valueOf(array[1]));
		this.meta = this.item.getItemMeta();
		for (int i = 2; i < array.length; i++) {
			if (array[i].contains(DURABILITY_KEY)) {
				item.setDurability(Short.valueOf(array[i].split(SUB_SPLIT)[1]));
			} else if (array[i].contains(DISPLAY_NAME_KEY)) {
				meta.setDisplayName(array[i].split(SUB_SPLIT)[1].replaceAll("_", " ").replaceAll(REPLACEMENT_COLOR_CODE_CHAR, COLOR_CODE_CHAR));
			} else if (array[i].contains(LORE_KEY)) {
				if (array[i].split(SUB_SPLIT)[1].contains(LORE_SPLIT)) {
					for (String loreString : array[i].split(SUB_SPLIT)[1].split(LORE_SPLIT)) {
						this.loreBuilder.add(loreString.replaceAll("_", " ").replaceAll(REPLACEMENT_COLOR_CODE_CHAR, COLOR_CODE_CHAR));
					}
				} else {
					this.loreBuilder.add(array[i].split(SUB_SPLIT)[1].replaceAll("_", " ").replaceAll(REPLACEMENT_COLOR_CODE_CHAR, COLOR_CODE_CHAR));
				}
			} else {	
				this.meta.addEnchant(Enchantment.getByName(array[i].split(SUB_SPLIT)[0]), Integer.valueOf(array[i].split(SUB_SPLIT)[1]), false);
			}
		}
		this.meta.setLore(this.loreBuilder);
		this.item.setItemMeta(this.meta);
		return this.item;
    }
}
