package net.sn0wix_.incounter.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.sn0wix_.incounter.Incounter;
import net.sn0wix_.incounter.networking.packets.PlayerLockS2CPacket;

public class ModPackets {
    public static final Identifier LOCK_PLAYER = new Identifier(Incounter.MOD_ID, "lock_player");
    public static final Identifier UNLOCK_PLAYER = new Identifier(Incounter.MOD_ID, "unlock_player");


    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LOCK_PLAYER, PlayerLockS2CPacket::reciveLock);
        ClientPlayNetworking.registerGlobalReceiver(UNLOCK_PLAYER, PlayerLockS2CPacket::reciveUnlock);
    }
}
