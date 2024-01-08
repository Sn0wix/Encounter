package net.sn0wix_.incounter.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.sn0wix_.incounter.client.Variables;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Mouse.class)
public abstract class MouseMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "updateMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void injectChangeLookDirection(CallbackInfo ci, double l, double k) {
        if (this.client.player != null && Variables.isPlayerLocked()) {
            this.client.player.changeLookDirection(0, 0);
            ci.cancel();
        }
    }
}
