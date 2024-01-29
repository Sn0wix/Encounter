package net.sn0wix_.encounter.mixin.client;

import net.minecraft.client.option.GameOptions;
import net.sn0wix_.encounter.common.Encounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/io/File;<init>(Ljava/io/File;Ljava/lang/String;)V"), index = 1)
    private String inject(String child) {
        return Encounter.NEW_GAME_OPTIONS_FILE_NAME;
    }
}
