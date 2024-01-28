package net.sn0wix_.encounter.client.keys;

import net.sn0wix_.encounter.client.util.ClientVariables;

public class KeyVariables {
    private static boolean crawlKeyPressed = false;

    public static boolean isCrawlKeyPressed() {
        return crawlKeyPressed;
    }

    public static void setCrawlKeyPressed(boolean pressed) {
        crawlKeyPressed = pressed;
        ClientVariables.setJumpLocked(pressed);
        ClientVariables.setShiftLocked(pressed);
    }
}
