package net.sn0wix_.encounter.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.Encounter;
import net.sn0wix_.encounter.networking.packets.PlayerLockS2CPacket;

public class ModPackets {
    public static final Identifier LOCK_PLAYER = new Identifier(Encounter.MOD_ID, "lock_player");
    public static final Identifier UNLOCK_PLAYER = new Identifier(Encounter.MOD_ID, "unlock_player");


    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LOCK_PLAYER, PlayerLockS2CPacket::reciveLock);
        ClientPlayNetworking.registerGlobalReceiver(UNLOCK_PLAYER, PlayerLockS2CPacket::reciveUnlock);
    }
}
