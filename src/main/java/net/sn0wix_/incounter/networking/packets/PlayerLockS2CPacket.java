package net.sn0wix_.incounter.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.sn0wix_.incounter.client.Variables;

public class PlayerLockS2CPacket {
    public static void reciveLock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Variables.lockPlayer();
    }

    public static void reciveUnlock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Variables.unlockPlayer();
    }
}
