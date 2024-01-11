package net.sn0wix_.encounter.common.util;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.UUID;

public class ServerVariables {
    private static final ArrayList<UUID> LOCKED_PLAYERS = new ArrayList<>(1);

    public static void addLockedPlayer(PlayerEntity player) {
        LOCKED_PLAYERS.add(player.getUuid());
    }

    public static ArrayList<UUID> getLockedPlayers() {
        return LOCKED_PLAYERS;
    }

    public static void removeLockedPlayer(PlayerEntity player) {
        LOCKED_PLAYERS.remove(player.getUuid());
    }

    public static void clearLockedPlayers() {
        LOCKED_PLAYERS.clear();
    }
}
