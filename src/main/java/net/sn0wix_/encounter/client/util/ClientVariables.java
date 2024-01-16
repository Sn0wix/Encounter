package net.sn0wix_.encounter.client.util;

import net.minecraft.util.math.Vec3d;

public class ClientVariables {
    private static boolean playerLocked = false;
    private static boolean jumpLocked = false;
    private static boolean shiftLocked = false;
    private static boolean crawlLocked = false;
    private static Vec3d scarePos = null;

    public static void setCrawlLocked(boolean crawlLocked) {
        ClientVariables.crawlLocked = crawlLocked;
    }

    public static void setJumpLocked(boolean jumpLocked) {
        ClientVariables.jumpLocked = jumpLocked;
    }

    public static void setShiftLocked(boolean shiftLocked) {
        ClientVariables.shiftLocked = shiftLocked;
    }

    public static boolean isJumpLocked() {
        return jumpLocked;
    }

    public static boolean isShiftLocked() {
        return shiftLocked;
    }

    public static boolean isCrawlLocked() {
        return crawlLocked;
    }

    public static void lockPlayer() {
        playerLocked = true;
    }

    public static void unlockPlayer() {
        playerLocked = false;
    }

    public static boolean isPlayerLocked() {
        return playerLocked;
    }

    public static Vec3d getScarePos() {
        return scarePos;
    }

    public static void setScarePos(Vec3d vec3d) {
        scarePos = vec3d;
    }
}
