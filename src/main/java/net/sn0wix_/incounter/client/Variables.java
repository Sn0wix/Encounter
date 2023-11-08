package net.sn0wix_.incounter.client;

public class Variables {
    private static boolean isPlayerLocked = false;
    private static boolean wasPlayerLocked = false;

    public static void lockPlayer() {
        isPlayerLocked = true;
        wasPlayerLocked = true;
    }

    public static void unlockPlayer() {
        isPlayerLocked = false;
        wasPlayerLocked = false;
    }

    public static boolean wasPlayerLocked() {
        return wasPlayerLocked;
    }

    public static boolean isPlayerLocked() {
        return isPlayerLocked;
    }
}
