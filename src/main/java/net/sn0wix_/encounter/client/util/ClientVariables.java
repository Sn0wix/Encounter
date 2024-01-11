package net.sn0wix_.encounter.client.util;

import net.minecraft.util.math.Vec3d;

public class ClientVariables {
    private static boolean isPlayerLocked = false;
    private static Vec3d scarePos = null;

    public static void lockPlayer() {
        isPlayerLocked = true;
    }

    public static void unlockPlayer() {
        isPlayerLocked = false;
    }

    public static boolean isPlayerLocked() {
        return isPlayerLocked;
    }


    public static Vec3d getScarePos() {
        return scarePos;
    }

    public static void setScarePos(Vec3d vec3d) {
        scarePos = vec3d;
    }
}
