package net.sn0wix_.incounter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.sn0wix_.incounter.entity.ModEntities;
import net.sn0wix_.incounter.entity.client.stalker.StalkerRenderer;

public class IncounterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.STALKER, StalkerRenderer::new);
    }
}
