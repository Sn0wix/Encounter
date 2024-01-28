package net.sn0wix_.encounter.common.networking.packets.c2s;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sn0wix_.encounter.common.keyMaps.ServerKeyHashMap;
import net.sn0wix_.encounter.common.networking.ModPackets;

public class CrawlC2SPacket {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        boolean pressed = buf.unwrap().readBoolean();

        if (pressed) {
            server.execute(() -> ServerKeyHashMap.CRAWL_KEYS.put(player.getUuid(), true));
        } else {
            server.execute(() -> ServerKeyHashMap.CRAWL_KEYS.put(player.getUuid(), false));
        }
    }

    public static void send(boolean pressed) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(pressed);

        ClientPlayNetworking.send(ModPackets.CRAWL_PACKET, buf);
    }
}
