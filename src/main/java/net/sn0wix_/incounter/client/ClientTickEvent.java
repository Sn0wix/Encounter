package net.sn0wix_.incounter.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientTickEvent {
    public static void registerEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (Variables.isPlayerLocked()) {
                //client.mouse.updateMouse();
            }else if (Variables.wasPlayerLocked()) {
                //client.mouse.unlockCursor();
                Variables.unlockPlayer();
            }
        });
    }
}
