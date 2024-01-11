package net.sn0wix_.encounter.common.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.networking.packets.s2c.PlayerDisableJumpS2CPacket;
import net.sn0wix_.encounter.common.networking.packets.s2c.PlayerLockS2CPacket;

public class ModPackets {
    public static final Identifier LOCK_PLAYER = new Identifier(Encounter.MOD_ID, "lock_player");
    public static final Identifier UNLOCK_PLAYER = new Identifier(Encounter.MOD_ID, "unlock_player");

    public static final Identifier LOCK_JUMP = new Identifier(Encounter.MOD_ID, "lock_jump");
    public static final Identifier UNLOCK_JUMP = new Identifier(Encounter.MOD_ID, "unlock_jump");


    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LOCK_PLAYER, PlayerLockS2CPacket::reciveLock);
        ClientPlayNetworking.registerGlobalReceiver(UNLOCK_PLAYER, PlayerLockS2CPacket::reciveUnlock);

        ClientPlayNetworking.registerGlobalReceiver(LOCK_JUMP, PlayerDisableJumpS2CPacket::reciveLock);
        ClientPlayNetworking.registerGlobalReceiver(UNLOCK_JUMP, PlayerDisableJumpS2CPacket::reciveUnlock);
    }
}
