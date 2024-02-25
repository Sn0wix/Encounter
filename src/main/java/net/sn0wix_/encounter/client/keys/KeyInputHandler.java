package net.sn0wix_.encounter.client.keys;

import net.minecraft.client.MinecraftClient;
import net.sn0wix_.encounter.client.util.ClientVariables;
import net.sn0wix_.encounter.common.networking.packets.c2s.CrawlC2SPacket;

import static net.sn0wix_.encounter.client.keys.KeyBindings.crawlKey;

public class KeyInputHandler {
    private static boolean wasCrawlKeyPressed = false;

    public static void handleInput(MinecraftClient client) {
        if (client.player != null && !ClientVariables.isCrawlLocked()) {
            if (crawlKey.isPressed() && !KeyVariables.isCrawlKeyPressed()) {
                KeyVariables.setCrawlKeyPressed(true);
            } else if (!crawlKey.isPressed() && KeyVariables.isCrawlKeyPressed()) {
                KeyVariables.setCrawlKeyPressed(false);
            }

            if (KeyVariables.isCrawlKeyPressed() != wasCrawlKeyPressed) {
                CrawlC2SPacket.send(KeyVariables.isCrawlKeyPressed());
                wasCrawlKeyPressed = KeyVariables.isCrawlKeyPressed();
            }
        } else if (ClientVariables.isCrawlLocked()) {
            wasCrawlKeyPressed = false;
            KeyVariables.setCrawlKeyPressed(false);
        }
    }
}
