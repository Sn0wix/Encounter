package net.sn0wix_.encounter.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.sn0wix_.encounter.client.util.ClientVariables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BossBarHud.class)
public abstract class BossBarRenderingMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowWidth()I", shift = At.Shift.AFTER), cancellable = true)
    private void injectRender(DrawContext context, CallbackInfo ci) {
        if (!ClientVariables.shouldRenderBossBar()) {
            ci.cancel();
        }
    }
}
