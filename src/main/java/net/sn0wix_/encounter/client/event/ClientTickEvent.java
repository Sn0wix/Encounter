package net.sn0wix_.encounter.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.sn0wix_.encounter.client.keys.KeyInputHandler;
import net.sn0wix_.encounter.client.util.ClientVariables;

public class ClientTickEvent {
    public static class EndClientTick implements ClientTickEvents.EndTick {

        @Override
        public void onEndTick(MinecraftClient client) {
            handleScareMovement(client);
            KeyInputHandler.handleInput(client);

            if (ClientVariables.isPlayerLocked() || ClientVariables.isF5Locked()) {
                handleF5(client);
            }

            if (client.player == null && ClientVariables.isPlayerLocked()) {
                ClientVariables.unlockPlayer();
            }
        }
    }

    public static class StartClientTick implements ClientTickEvents.StartTick {
        @Override
        public void onStartTick(MinecraftClient client) {
            handleScareMovement(client);
        }
    }

    public static void handleF5(MinecraftClient client) {
        if (!client.options.getPerspective().isFirstPerson()) {
            client.options.setPerspective(Perspective.FIRST_PERSON);
            client.gameRenderer.onCameraEntitySet(client.getCameraEntity());
            client.worldRenderer.scheduleTerrainUpdate();
        }
    }

    public static void handleScareMovement(MinecraftClient client) {
        if (client.player != null && ClientVariables.isPlayerLocked()) {
            if (ClientVariables.getScarePos() != null) {
                client.player.setPos(ClientVariables.getScarePos().x, ClientVariables.getScarePos().y, ClientVariables.getScarePos().z);
            }
        }
    }
}
