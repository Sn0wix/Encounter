package net.sn0wix_.encounter.common.keyMaps;

import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerKeyHashMap {
    public static final Map<UUID, Boolean> CRAWL_KEYS = new HashMap<>();

    public static boolean isCrawling(PlayerEntity player) {
        return player != null && CRAWL_KEYS.getOrDefault(player.getUuid(), false);
    }
}
