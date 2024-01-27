package net.sn0wix_.encounter.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityPose;
import net.minecraft.text.Text;
import net.sn0wix_.encounter.client.keyBindings.KeyVariables;
import net.sn0wix_.encounter.client.util.ClientVariables;

import java.security.Key;

import static net.sn0wix_.encounter.client.keyBindings.KeyBindings.crawlKey;

public class ClientTickEvent {
    public static class EndClientTick implements ClientTickEvents.EndTick {
        @Override
        public void onEndTick(MinecraftClient client) {
            stopMoving(client);
            handleKeyInputs(client);

            if (client.player == null && ClientVariables.isPlayerLocked()) {
                ClientVariables.unlockPlayer();
            }
        }

        private static void handleKeyInputs(MinecraftClient client) {
            if (client.player != null) {
                if (crawlKey.isPressed() && !crawlKey.wasPressed() && !KeyVariables.isCrawlKeyPressed()) {
                    client.player.sendMessage(Text.of("pressed"));
                    KeyVariables.setCrawlKeyPressed(true);
                } else if (!crawlKey.isPressed() && crawlKey.wasPressed() && KeyVariables.isCrawlKeyPressed()) {
                    client.player.sendMessage(Text.of("released"));
                    KeyVariables.setCrawlKeyPressed(false);
                } else if (!crawlKey.isPressed() && KeyVariables.isCrawlKeyPressed()) {
                    KeyVariables.setCrawlKeyPressed(false);
                }

                client.player.sendMessage(Text.of("is pressed " + crawlKey.isPressed()));
                client.player.sendMessage(Text.of("was pressed " + crawlKey.wasPressed()));
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
