package net.sn0wix_.encounter.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.sn0wix_.encounter.client.event.ClientTickEvent;
import net.sn0wix_.encounter.client.keyBindings.KeyBindings;
import net.sn0wix_.encounter.client.models.CrawlerModel;
import net.sn0wix_.encounter.client.models.StalkerModel;
import net.sn0wix_.encounter.common.entity.ModEntities;
import net.sn0wix_.encounter.common.networking.ModPackets;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EncounterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindings.registerKeyBindings();
        EntityRendererRegistry.register(ModEntities.STALKER, ctx -> new GeoEntityRenderer<>(ctx, new StalkerModel()));
        EntityRendererRegistry.register(ModEntities.CRAWLER, ctx -> new GeoEntityRenderer<>(ctx, new CrawlerModel()));

        ClientTickEvents.END_CLIENT_TICK.register(new ClientTickEvent.EndClientTick());
        ClientTickEvents.START_CLIENT_TICK.register(new ClientTickEvent.StartClientTick());

        ModPackets.registerS2CPackets();
    }
}
