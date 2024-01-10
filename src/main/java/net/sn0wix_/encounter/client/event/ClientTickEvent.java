package net.sn0wix_.encounter.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.sn0wix_.encounter.client.Variables;

import static net.sn0wix_.encounter.client.KeyBindings.crawlKey;

public class ClientTickEvent {
    public static class EndClientTick implements ClientTickEvents.EndTick {
        @Override
        public void onEndTick(MinecraftClient client) {
            stopMoving(client);
            handleKeyInputs(client);
        }

        private static void handleKeyInputs(MinecraftClient client) {
            if (crawlKey.wasPressed()) {
                if (client.player != null)
                    client.player.sendMessage(Text.of("joooooooooooooo"));
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
        if (client.player != null && Variables.isPlayerLocked()) {
            if (Variables.getScarePos() != null) {
                client.player.setPos(Variables.getScarePos().x, Variables.getScarePos().y, Variables.getScarePos().z);
            }
        }
    }
}
