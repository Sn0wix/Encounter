package net.sn0wix_.encounter.client.keyBindings;

public class KeyVariables {
    private static boolean crawlKeyPressed = false;

    public static boolean isCrawlKeyPressed() {
        return crawlKeyPressed;
    }

    public static void setCrawlKeyPressed(boolean pressed) {
        crawlKeyPressed = pressed;
    }
}
