package net.sn0wix_.encounter.common.networking.packets.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.sn0wix_.encounter.client.util.ClientVariables;
import net.sn0wix_.encounter.common.networking.ModPackets;
import net.sn0wix_.encounter.common.util.ServerVariables;
import org.joml.Vector3f;

public class PlayerLockS2CPacket {
    public static void recieveLock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        ClientVariables.lockPlayer();
        ClientVariables.setScarePos(new Vec3d(packetByteBuf.readVector3f()));

        client.executeSync(() -> client.getSoundManager().stopAll());
    }

    public static void recieveUnlock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        ClientVariables.unlockPlayer();
        ClientVariables.setScarePos(null);
    }

    public static void sendLockAndWriteToHashMap(ServerPlayerEntity player, Vec3d vec3d) {
        ServerVariables.addLockedPlayer(player);
        ServerPlayNetworking.send(player, ModPackets.LOCK_PLAYER, createLockBuf(vec3d));
    }

    public static void sendUnlockAndWriteToHashMap(ServerPlayerEntity player) {
        ServerVariables.removeLockedPlayer(player);
        ServerPlayNetworking.send(player, ModPackets.UNLOCK_PLAYER, PacketByteBufs.create());
    }

    private static PacketByteBuf createLockBuf(Vec3d vec3d) {
        PacketByteBuf buff = PacketByteBufs.create();
        buff.writeVector3f(new Vector3f((float) vec3d.x, (float) vec3d.y, (float) vec3d.z));
        return buff;
    }
}
