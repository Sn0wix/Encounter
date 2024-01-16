package net.sn0wix_.encounter.common.networking.packets.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sn0wix_.encounter.client.util.ClientVariables;
import net.sn0wix_.encounter.common.networking.ModPackets;

public class PlayerDisableShiftS2CPacket {
    public static void reciveLock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        client.execute(() -> ClientVariables.setShiftLocked(true));
    }

    public static void reciveUnlock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        client.execute(() -> ClientVariables.setShiftLocked(false));
    }

    public static void send(ServerPlayerEntity player, boolean lock) {
        if (lock) {
            ServerPlayNetworking.send(player, ModPackets.LOCK_SHIFT, PacketByteBufs.create());
        } else {
            ServerPlayNetworking.send(player, ModPackets.UNLOCK_SHIFT, PacketByteBufs.create());
        }
    }
}
