package net.sn0wix_.incounter;

import net.fabricmc.api.ModInitializer;

import net.sn0wix_.incounter.item.ModItemGroup;
import net.sn0wix_.incounter.item.ModItems;
import net.sn0wix_.incounter.networking.ModPackets;
import net.sn0wix_.incounter.sounds.ModSounds;
import net.sn0wix_.incounter.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Incounter implements ModInitializer {
	//TODO 5 monsters
	//TODO went block
	//TODO (disable shift when cut sceene)
	//TODO fix two keyboards not working when scaring
	//TODO idea: seecret ratafak skin
	//TODO idea: crawl key(x)


	public static final Logger LOGGER = LoggerFactory.getLogger("incounter");
	public static final String MOD_ID = "incounter";

	@Override
	public void onInitialize() {
		ModItemGroup.registerModItemGroups();
		ModItems.registerModItems();
		ModRegistries.registerModStuffs();
		ModSounds.registerModSounds();
		ModPackets.registerC2SPackets();
	}
}
