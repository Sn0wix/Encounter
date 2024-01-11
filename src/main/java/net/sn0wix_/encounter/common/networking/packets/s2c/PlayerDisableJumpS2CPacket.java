package net.sn0wix_.encounter.common.networking.packets.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.sn0wix_.encounter.common.Encounter;

public class PlayerDisableJumpS2CPacket {
    public static void reciveLock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Encounter.LOGGER.info("Locked jump");
    }

    public static void reciveUnlock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Encounter.LOGGER.info("Unlocked jump");
    }
}
