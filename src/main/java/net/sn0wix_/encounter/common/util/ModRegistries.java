package net.sn0wix_.encounter.common.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sn0wix_.encounter.common.commands.*;
import net.sn0wix_.encounter.common.entity.ModEntities;
import net.sn0wix_.encounter.common.entity.custom.CrawlerEntity;
import net.sn0wix_.encounter.common.entity.custom.PhantomEntity;
import net.sn0wix_.encounter.common.entity.custom.StalkerEntity;

public class ModRegistries {
    public static void registerModStuffs(){
        registerAttributes();
        registerCommands();
    }

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.STALKER, StalkerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRAWLER, CrawlerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.PHANTOM, PhantomEntity.setAttributes());
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(DisableJumpCommand::register);
        CommandRegistrationCallback.EVENT.register(DisableShiftCommand::register);
        CommandRegistrationCallback.EVENT.register(StopRenderingBossBarCommand::register);


        CommandRegistrationCallback.EVENT.register(StartCrawlerPeekAnimCommand::register);
        CommandRegistrationCallback.EVENT.register(StartScareAnimCommand::register);
        CommandRegistrationCallback.EVENT.register(StartStalkerWakeUpAnimCommand::register);


        CommandRegistrationCallback.EVENT.register(RandomCommand::register);

        CommandRegistrationCallback.EVENT.register(CustomHelpCommand::register);
    }
}
