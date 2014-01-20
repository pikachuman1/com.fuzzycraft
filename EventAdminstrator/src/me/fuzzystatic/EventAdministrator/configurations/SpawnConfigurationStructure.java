package me.fuzzystatic.EventAdministrator.configurations;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.events.EventName;
import me.fuzzystatic.EventAdministrator.interfaces.FileStructure;
import me.fuzzystatic.EventAdministrator.utilities.ConfigAccessor;
import me.fuzzystatic.EventAdministrator.utilities.SerializableLocation;
import me.fuzzystatic.EventAdministrator.utilities.YMLLocation;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

public class SpawnConfigurationStructure implements FileStructure{
	
	public static final String SPAWNS = "spawns"; 
	
	private final String locationYML, mobYML, amountYML, startTimeYML, cycleTimeYML, isBossYML;
	
	private static final String defaultMob = "zombie";
	private static final int defaultAmount = 1;
	private static final long defaultStart = 0;
	private static final long defaultCycle = -1;
	private static final boolean defaultIsBoss = false;
	
	private final ConfigAccessor configAccessor;
	private final FileConfiguration config;
	private final String spawnPrefix;
	
	public EntityType getEntityType(String mobString) {
		EntityType mobEntityType = null;
		switch(mobString.toLowerCase()) {
			case "bat" : 			mobEntityType = EntityType.BAT; break;
			case "blaze" : 			mobEntityType = EntityType.BLAZE; break;
			case "cavespider" : 	mobEntityType = EntityType.CAVE_SPIDER; break;
			case "cave_spider" : 	mobEntityType = EntityType.CAVE_SPIDER; break;
			case "chicken" : 		mobEntityType = EntityType.CHICKEN; break;
			case "cow" : 			mobEntityType = EntityType.COW; break;
			case "creeper" : 		mobEntityType = EntityType.CREEPER; break;
			case "dragon" : 		mobEntityType = EntityType.ENDER_DRAGON; break;
			case "enderdragon" : 	mobEntityType = EntityType.ENDER_DRAGON; break;
			case "ender_dragon" : 	mobEntityType = EntityType.ENDER_DRAGON; break;
			case "enderman" : 		mobEntityType = EntityType.ENDERMAN; break;
			case "ghast" : 			mobEntityType = EntityType.GHAST; break;
			case "giant" : 			mobEntityType = EntityType.GIANT; break;
			case "horse" : 			mobEntityType = EntityType.HORSE; break;
			case "irongolem" :		mobEntityType = EntityType.IRON_GOLEM; break;
			case "iron_golem" :		mobEntityType = EntityType.IRON_GOLEM; break;
			case "magmacube" :		mobEntityType = EntityType.MAGMA_CUBE; break;
			case "magma_cube" :		mobEntityType = EntityType.MAGMA_CUBE; break;
			case "mushroomcow" :	mobEntityType = EntityType.MUSHROOM_COW; break;
			case "mushroom_cow" :	mobEntityType = EntityType.MUSHROOM_COW; break;
			case "ocelot" :			mobEntityType = EntityType.OCELOT; break;
			case "pig" : 			mobEntityType = EntityType.PIG; break;
			case "pigzombie" : 		mobEntityType = EntityType.PIG_ZOMBIE; break;
			case "pig_zombie" : 	mobEntityType = EntityType.PIG_ZOMBIE; break;
			case "sheep" :			mobEntityType = EntityType.SHEEP; break;
			case "silverfish" :		mobEntityType = EntityType.SILVERFISH; break;
			case "skeleton" :		mobEntityType = EntityType.SKELETON; break;
			case "slime" :			mobEntityType = EntityType.SLIME; break;
			case "snowman" :		mobEntityType = EntityType.SNOWMAN; break;
			case "spider" :			mobEntityType = EntityType.SPIDER; break;
			case "squid" :			mobEntityType = EntityType.SQUID; break;
			case "villager" :		mobEntityType = EntityType.VILLAGER; break;
			case "witch" :			mobEntityType = EntityType.WITCH; break;
			case "wither" :			mobEntityType = EntityType.WITHER; break;
			case "wolf" :			mobEntityType = EntityType.WOLF; break;
			case "zombie" : 		mobEntityType = EntityType.ZOMBIE; break;
		}
		return mobEntityType;
	}
	
	public SpawnConfigurationStructure(EventAdministrator plugin, String spawnName) {
		this.configAccessor = new ConfigAccessor(plugin, DirectoryStructure.EVENT_DIR + EventName.getName() + ".yml");
		this.config = configAccessor.getConfig();
		this.spawnPrefix = SPAWNS + "." + spawnName + ".";
		this.locationYML = spawnPrefix + "location";
		this.mobYML = spawnPrefix + "mob";
		this.amountYML = spawnPrefix + "amount";
		this.startTimeYML = spawnPrefix + "startTime";
		this.cycleTimeYML = spawnPrefix + "cycleTime";
		this.isBossYML = spawnPrefix + "isBoss";
	}
	
	public void setLocation(Location location) {
   		SerializableLocation sl = new SerializableLocation(location);
		new YMLLocation().setLocation(sl.serialize(), this.config, locationYML);
		this.configAccessor.saveConfig();
	}
	
	public void setMob(String mob) {
		this.config.set(this.mobYML, mob);
		this.configAccessor.saveConfig();
	}
	
	public void setAmount(int amount) {
		this.config.set(this.amountYML, amount);
		this.configAccessor.saveConfig();
	}
	
	public void setStartTime(long seconds) {
		this.config.set(this.startTimeYML, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setCycleTime(long seconds) {
		this.config.set(this.cycleTimeYML , seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setIsBoss(boolean isBoss) {
		this.config.set(this.isBossYML, isBoss);
		this.configAccessor.saveConfig();
	}

	@Override
	public void createFileStructure() {
		if(this.config.get(this.locationYML) == null) new YMLLocation().setBlankLocation(this.config, this.locationYML);;
		if(this.config.get(this.mobYML) == null) setMob(defaultMob);
		if(this.config.get(this.amountYML) == null) setAmount(defaultAmount);
		if(this.config.get(this.startTimeYML) == null) setStartTime(defaultStart);
		if(this.config.get(this.cycleTimeYML) == null) setCycleTime(defaultCycle);
		if(this.config.get(this.isBossYML) == null) setIsBoss(defaultIsBoss);
		this.configAccessor.saveConfig();
	}
	
	public Location getLocation() {
		YMLLocation ymlLocation = new YMLLocation();
		return new SerializableLocation(ymlLocation.getLocation(config, locationYML)).getLocation();
	}
	
	public EntityType getMob() {
		return getEntityType(this.config.get(mobYML).toString());
	}
	
	public int getAmount() {
		return config.getInt(amountYML);
	}
	
	public long getStartTime() {
		return config.getLong(startTimeYML);
	}
	
	public long getCycleTime() {
		return config.getLong(cycleTimeYML);
	}
	
	public boolean getIsBoss() {
		return config.getBoolean(isBossYML);
	}
}
