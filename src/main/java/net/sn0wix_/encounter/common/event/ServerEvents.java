package net.sn0wix_.encounter.common.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.EntityPose;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.sn0wix_.encounter.common.keyMaps.ServerKeyHashMap;

public class ServerEvents {
    public static class ServerEndTickEvent implements ServerTickEvents.EndTick {
        @Override
        public void onEndTick(MinecraftServer server) {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                if (ServerKeyHashMap.isCrawling(player)) {
                    player.setPose(EntityPose.SWIMMING);
                }
            });
        }
    }

    public static class PlayerDisconnectEvent implements ServerPlayConnectionEvents.Disconnect {
        @Override
        public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
            server.execute(() -> ServerKeyHashMap.CRAWL_KEYS.remove(handler.player.getUuid()));
        }
    }
}
