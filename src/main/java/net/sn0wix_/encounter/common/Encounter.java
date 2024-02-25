package net.sn0wix_.encounter.common;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.sn0wix_.encounter.client.util.fileUtil.GameOptionsFileWriter;
import net.sn0wix_.encounter.common.event.ServerEvents;
import net.sn0wix_.encounter.common.item.ModItemGroup;
import net.sn0wix_.encounter.common.item.ModItems;
import net.sn0wix_.encounter.common.networking.ModPackets;
import net.sn0wix_.encounter.common.sounds.ModSounds;
import net.sn0wix_.encounter.common.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.io.IOException;

public class Encounter implements ModInitializer {
	//TODO fix jumping/sneaking when crawling
	//TODO 3 monsters
	//TODO idea: secret ratafak skin
	//TODO add a config file that saves hash maps and variables

	public static final Logger LOGGER = LoggerFactory.getLogger(Encounter.MOD_ID);
	public static final String MOD_ID = "encounter";
	public static final String NEW_GAME_OPTIONS_FILE_NAME = "the_encounter_options.txt";

	@Override
	public void onInitialize() {
		try {
			GameOptionsFileWriter.run();
		}catch (IOException e) {
			LOGGER.error(Marker.ANY_MARKER, "Can not create " + NEW_GAME_OPTIONS_FILE_NAME, new RuntimeException(e));
			throw new RuntimeException(e);
		}

		ModItemGroup.registerModItemGroups();
		ModItems.registerModItems();
		ModRegistries.registerModStuffs();
		ModSounds.registerModSounds();
		ModPackets.registerC2SPackets();

		ServerTickEvents.END_SERVER_TICK.register(new ServerEvents.ServerEndTickEvent());
		ServerPlayConnectionEvents.DISCONNECT.register(new ServerEvents.PlayerDisconnectEvent());
	}
}
