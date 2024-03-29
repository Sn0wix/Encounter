package net.sn0wix_.encounter.common.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.common.Encounter;

public class ModSounds {
    public static final SoundEvent STALKER_JUMPSCARE = registerSoundEvent("stalker_jumpscare");
    public static final SoundEvent CRAWLER_JUMPSCARE = registerSoundEvent("crawler_jumpscare");
    public static final SoundEvent PHANTOM_JUMPSCARE = registerSoundEvent("phantom_jumpscare");
    public static final SoundEvent CRAWLER_PEEK = registerSoundEvent("crawler_peek");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Encounter.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerModSounds() {
        Encounter.LOGGER.info("Registering sounds for " + Encounter.MOD_ID);
    }
}
