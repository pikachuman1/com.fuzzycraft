package me.fuzzystatic.EventAdministrator.configuration;

import java.util.ArrayList;
import java.util.List;

import me.fuzzystatic.EventAdministrator.interfaces.SerializableConstants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeserializableItemString implements SerializableConstants {

	private static final String LORE_SPLIT = ",";	
	private static final String DURABILITY_KEY = "durability" + SUB_SPLIT;
	private static final String DISPLAY_NAME_KEY = "name" + SUB_SPLIT;
	private static final String LORE_KEY = "lore" + SUB_SPLIT;
	
	private ItemStack item;
	private ItemMeta meta;
	private List<String> loreBuilder = new ArrayList<String>();
	
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
					for (String loreString : array[i].split(SUB_SPLIT)[1].split(LORE_SPLIT)) this.loreBuilder.add(loreString.replaceAll("_", " ").replaceAll(REPLACEMENT_COLOR_CODE_CHAR, COLOR_CODE_CHAR));
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
