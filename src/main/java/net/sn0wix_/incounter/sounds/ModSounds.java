package net.sn0wix_.incounter.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sn0wix_.incounter.Incounter;

public class ModSounds {
    public static final SoundEvent JUMPSCARE = registerSoundEvent("jumpscare");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Incounter.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerModSounds() {
        Incounter.LOGGER.info("Registering sounds for " + Incounter.MOD_ID);
    }
}
