package net.sn0wix_.encounter.common.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sn0wix_.encounter.common.entity.ModEntities;
import net.sn0wix_.encounter.common.entity.custom.CrawlerEntity;
import net.sn0wix_.encounter.common.entity.custom.StalkerEntity;

public class ModRegistries {
    public static void registerModStuffs(){
        registerAttributes();
    }

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.STALKER, StalkerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRAWLER, CrawlerEntity.setAttributes());
    }
}
