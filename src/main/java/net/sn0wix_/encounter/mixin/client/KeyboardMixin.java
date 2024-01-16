package net.sn0wix_.encounter.mixin.client;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.sn0wix_.encounter.client.util.ClientVariables;
import net.sn0wix_.encounter.common.Encounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getNarratorManager()Lnet/minecraft/client/util/NarratorManager;", shift = At.Shift.BEFORE), cancellable = true)
    private void injectOnKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (ClientVariables.isPlayerLocked()) {
            if (this.client.currentScreen == null && client.player != null) {
                KeyBinding.unpressAll();
            }

            ci.cancel();
        }


        if (ClientVariables.isJumpLocked() && (client.options.jumpKey.matchesKey(key, scancode) || client.options.jumpKey.isPressed())) {
            if (client.player != null) {
                KeyBinding.setKeyPressed(InputUtil.fromKeyCode(key, scancode), false);
                ci.cancel();
            }
        }

        if (ClientVariables.isShiftLocked() && (client.options.sneakKey.matchesKey(key, scancode) || client.options.sneakKey.isPressed())) {
            if (client.player != null) {
                KeyBinding.setKeyPressed(InputUtil.fromKeyCode(key, scancode), false);
                ci.cancel();
            }
        }
    }
}
