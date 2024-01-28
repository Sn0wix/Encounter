package net.sn0wix_.encounter.mixin.common;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.sn0wix_.encounter.client.keyBindings.KeyVariables;
import net.sn0wix_.encounter.common.keyMaps.ServerKeyHashMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow public abstract GameProfile getGameProfile();

    @Inject(method = "updatePose", at = @At("HEAD"), cancellable = true)
    private void injectUpdatePose(CallbackInfo ci) {
        if (ServerKeyHashMap.CRAWL_KEYS.getOrDefault(this.getGameProfile().getId(), false) || KeyVariables.isCrawlKeyPressed()) {
            ci.cancel();
        }
    }
}
