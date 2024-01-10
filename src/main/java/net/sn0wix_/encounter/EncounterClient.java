package net.sn0wix_.encounter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.sn0wix_.encounter.client.KeyBindings;
import net.sn0wix_.encounter.client.event.ClientTickEvent;
import net.sn0wix_.encounter.entity.ModEntities;
import net.sn0wix_.encounter.client.renderers.stalker.StalkerRenderer;
import net.sn0wix_.encounter.networking.ModPackets;

public class EncounterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindings.registerKeyBindings();
        EntityRendererRegistry.register(ModEntities.STALKER, StalkerRenderer::new);

        ClientTickEvents.END_CLIENT_TICK.register(new ClientTickEvent.EndClientTick());
        ClientTickEvents.START_CLIENT_TICK.register(new ClientTickEvent.StartClientTick());

        ModPackets.registerS2CPackets();
    }
}
