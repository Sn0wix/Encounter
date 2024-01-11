package net.sn0wix_.encounter.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.sn0wix_.encounter.client.util.ClientVariables;
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

    @Shadow
    private boolean leftButtonClicked;

    @Shadow
    private boolean middleButtonClicked;

    @Shadow
    private boolean rightButtonClicked;

    @Inject(method = "updateMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void injectChangeLookDirection(CallbackInfo ci, double l, double k) {
        if (this.client.player != null && ClientVariables.isPlayerLocked()) {
            this.client.player.changeLookDirection(0, 0);
            ci.cancel();
        }
    }

    @Inject(method = "onMouseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;wrapScreenError(Ljava/lang/Runnable;Ljava/lang/String;Ljava/lang/String;)V", shift = At.Shift.BEFORE), cancellable = true)
    private void injectOnMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (ClientVariables.isPlayerLocked()) {
            leftButtonClicked = false;
            rightButtonClicked = false;
            middleButtonClicked = false;
            KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(button), false);
            ci.cancel();
        }
    }
}
