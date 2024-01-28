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

public class PlayerDisableJumpS2CPacket {
    public static void recieveLock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        client.execute(() -> ClientVariables.setJumpLocked(true));
    }

    public static void recieveUnlock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        client.execute(() -> ClientVariables.setJumpLocked(false));
    }

    public static void send(ServerPlayerEntity player, boolean lock) {
        if (lock) {
            ServerPlayNetworking.send(player, ModPackets.LOCK_JUMP, PacketByteBufs.create());
        } else {
            ServerPlayNetworking.send(player, ModPackets.UNLOCK_JUMP, PacketByteBufs.create());
        }
    }
}
