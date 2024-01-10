package net.sn0wix_.encounter.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.sn0wix_.encounter.Encounter;
import net.sn0wix_.encounter.client.Variables;

public class PlayerLockS2CPacket {
    public static void reciveLock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Variables.lockPlayer();
        Variables.setScarePos(new Vec3d(packetByteBuf.readVector3f()));
        Encounter.LOGGER.info(Variables.getScarePos().toString());

        client.executeSync(() -> client.getSoundManager().stopAll());
    }

    public static void reciveUnlock(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Variables.unlockPlayer();
        Variables.setScarePos(null);
    }
}
