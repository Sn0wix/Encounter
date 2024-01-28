package net.sn0wix_.encounter.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.sn0wix_.encounter.client.keys.KeyInputHandler;
import net.sn0wix_.encounter.client.util.ClientVariables;

public class ClientTickEvent {
    public static class EndClientTick implements ClientTickEvents.EndTick {

        @Override
        public void onEndTick(MinecraftClient client) {
            stopMoving(client);
            KeyInputHandler.handleInputs(client);

            if (client.player == null && ClientVariables.isPlayerLocked()) {
                ClientVariables.unlockPlayer();
            }
        }
    }

    public static class StartClientTick implements ClientTickEvents.StartTick {
        @Override
        public void onStartTick(MinecraftClient client) {
            stopMoving(client);
        }
    }

    public static void stopMoving(MinecraftClient client) {
        if (client.player != null && ClientVariables.isPlayerLocked()) {
            if (ClientVariables.getScarePos() != null) {
                client.player.setPos(ClientVariables.getScarePos().x, ClientVariables.getScarePos().y, ClientVariables.getScarePos().z);
            }
        }
    }
}
