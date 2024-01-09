package net.sn0wix_.incounter.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.sn0wix_.incounter.Incounter;
import net.sn0wix_.incounter.client.Variables;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getNarratorManager()Lnet/minecraft/client/util/NarratorManager;", shift = At.Shift.BEFORE), cancellable = true)
    private void injectOnKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        Incounter.LOGGER.info("key");

        if (Variables.isPlayerLocked()) {
            if (this.client.currentScreen == null) {
                InputUtil.Key key2 = InputUtil.fromKeyCode(key, scancode);
                if (action != 0) {
                    KeyBinding.setKeyPressed(key2, false);
                }
            }

            Incounter.LOGGER.info("locked");
            ci.cancel();
        }
    }
}
