package net.sn0wix_.encounter.common;

import net.fabricmc.api.ModInitializer;

import net.sn0wix_.encounter.common.item.ModItemGroup;
import net.sn0wix_.encounter.common.item.ModItems;
import net.sn0wix_.encounter.common.networking.ModPackets;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import net.sn0wix_.encounter.common.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encounter implements ModInitializer {
	//TODO 3 monsters
	//TODO vent block
	//TODO (disable shift when cut scene)
	//TODO disable jump + command
	//TODO idea: secret ratafak skin
	//TODO crawl key(x)
	//TODO custom command for stalker sleep
	//TODO add a config file that saves hash maps and variables
	//TODO return from f5 when scaring
	//TODO auto options
	//TODO boss bar rendering

	public static final Logger LOGGER = LoggerFactory.getLogger(Encounter.MOD_ID);
	public static final String MOD_ID = "encounter";

	@Override
	public void onInitialize() {
		ModItemGroup.registerModItemGroups();
		ModItems.registerModItems();
		ModRegistries.registerModStuffs();
		ModSounds.registerModSounds();
		ModPackets.registerC2SPackets();
	}
}
