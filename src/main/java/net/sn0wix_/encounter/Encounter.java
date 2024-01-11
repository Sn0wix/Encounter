package net.sn0wix_.encounter;

import net.fabricmc.api.ModInitializer;

import net.sn0wix_.encounter.item.ModItemGroup;
import net.sn0wix_.encounter.item.ModItems;
import net.sn0wix_.encounter.networking.ModPackets;
import net.sn0wix_.encounter.sounds.ModSounds;
import net.sn0wix_.encounter.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encounter implements ModInitializer {
	//TODO 5 monsters
	//TODO went block
	//TODO (disable shift when cut scene)
	//TODO disable jump + command
	//TODO fix scare math
	//TODO idea: secret ratafak skin
	//TODO crawl key(x)
	//TODO rename git repo to encounter


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
