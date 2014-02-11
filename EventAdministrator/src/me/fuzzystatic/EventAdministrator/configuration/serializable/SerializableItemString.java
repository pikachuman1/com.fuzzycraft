package me.fuzzystatic.EventAdministrator.configuration.serializable;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.fuzzystatic.EventAdministrator.interfaces.SerializableConstants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.material.MaterialData;

public class SerializableItemString implements SerializableConstants {
	
	private final Material type;
	//private final MaterialData data;
	private final Integer amount;
	private final Short durability;
	private final String displayName;
	private final List<String> lore;
	private final Map<Enchantment, Integer> enchants;
	
		
	public SerializableItemString(ItemStack item) {
		this.type = item.getType();
		//this.data = item.getData();
        this.amount = item.getAmount();
        this.durability = item.getDurability();
        ItemMeta meta = item.getItemMeta();
        this.displayName = meta.getDisplayName();
        this.lore = meta.getLore();
        this.enchants = meta.getEnchants();
	}
	
	public StringBuilder serialize() {
		StringBuilder itemBuilder = new StringBuilder();
        if(this.type != null) itemBuilder.append(this.type.toString());
        if(this.amount != null) itemBuilder.append(MAIN_SPLIT).append(this.amount);
        //if(this.data != null) itemBuilder.append(MAIN_SPLIT).append(MATERIAL_DATA_KEY).append(this.data);
        if(this.durability != null) itemBuilder.append(MAIN_SPLIT).append(DURABILITY_KEY).append(this.durability);
        if(this.displayName != null) itemBuilder.append(MAIN_SPLIT).append(DISPLAY_NAME_KEY).append(this.displayName.replaceAll(" ", "_").replaceAll(COLOR_CODE_CHAR, REPLACEMENT_COLOR_CODE_CHAR));
        if(this.lore != null) itemBuilder.append(MAIN_SPLIT).append(LORE_KEY).append(this.lore.toString().replaceAll("\\, ", "\\,").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "_").replaceAll(COLOR_CODE_CHAR, REPLACEMENT_COLOR_CODE_CHAR));
        if(this.enchants != null) {
        	 for(Entry<Enchantment, Integer> enchantment : this.enchants.entrySet()) itemBuilder.append(MAIN_SPLIT).append(enchantment.getKey().getName()).append(SUB_SPLIT).append(enchantment.getValue());
        }
		return itemBuilder;
	}
	

}
