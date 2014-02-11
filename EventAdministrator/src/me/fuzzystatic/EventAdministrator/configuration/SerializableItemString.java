package me.fuzzystatic.EventAdministrator.configuration;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.fuzzystatic.EventAdministrator.interfaces.SerializableConstants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SerializableItemString implements SerializableConstants {
	
	private final Material type;
	private final Integer amount;
	private final Short durability;
	private final String displayName;
	private final List<String> lore;
	private final Map<Enchantment, Integer> enchants;
	
	private ItemMeta meta;
	private String itemBuilder = new String();
		
	public SerializableItemString(ItemStack item) {
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
        	 for(Entry<Enchantment, Integer> enchantment : this.enchants.entrySet()) this.itemBuilder = this.itemBuilder + " " + enchantment.getKey().getName() + ":" + enchantment.getValue();
        }
		return this.itemBuilder;
	}
	

}
