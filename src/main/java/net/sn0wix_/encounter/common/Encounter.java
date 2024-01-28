package net.sn0wix_.encounter.common;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.sn0wix_.encounter.common.event.ServerEvents;
import net.sn0wix_.encounter.common.item.ModItemGroup;
import net.sn0wix_.encounter.common.item.ModItems;
import net.sn0wix_.encounter.common.networking.ModPackets;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import net.sn0wix_.encounter.common.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encounter implements ModInitializer {
	//TODO fix jumping/sneaking when crawling
	//TODO 3 monsters
	//TODO idea: secret ratafak skin
	//TODO add a config file that saves hash maps and variables
	//TODO return from f5 when scaring
	//TODO auto options

	public static final Logger LOGGER = LoggerFactory.getLogger(Encounter.MOD_ID);
	public static final String MOD_ID = "encounter";

	@Override
	public void onInitialize() {
		ModItemGroup.registerModItemGroups();
		ModItems.registerModItems();
		ModRegistries.registerModStuffs();
		ModSounds.registerModSounds();
		ModPackets.registerC2SPackets();

		ServerTickEvents.END_SERVER_TICK.register(new ServerEvents.ServerEndTickEvent());
		ServerPlayConnectionEvents.DISCONNECT.register(new ServerEvents.PlayerDisconnectEvent());
	}
}
