package net.sn0wix_.encounter.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.item.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Unique
    private static final Identifier BLOOD_OVERLAY = new Identifier(Encounter.MOD_ID, "textures/gui/blood_overlay.png");
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getLastFrameDuration()F", shift = At.Shift.AFTER))
    private void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (client.player != null && this.client.player.getInventory().getArmorStack(3).isOf(ModItems.BLOOD)) {
            this.renderOverlay(context, BLOOD_OVERLAY, 1.0f);
        }
    }
}
