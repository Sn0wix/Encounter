package net.sn0wix_.encounter.mixin.common;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sn0wix_.encounter.common.util.ServerVariables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    @Shadow public abstract Entity getCameraEntity();

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setCameraEntity(Lnet/minecraft/entity/Entity;)V", shift = At.Shift.BEFORE), cancellable = true)
    private void injectAttack(Entity target, CallbackInfo ci) {
        if (ServerVariables.getLockedPlayers().contains(this.getCameraEntity().getUuid()) && (!this.getCameraEntity().getUuid().equals(target.getUuid()))) {
            ci.cancel();
        }
    }
}
