package net.sn0wix_.encounter.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sn0wix_.encounter.entity.ModEntities;
import net.sn0wix_.encounter.entity.custom.StalkerEntity;

public class ModRegistries {
    public static void registerModStuffs(){
        registerAttributes();
    }

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.STALKER, StalkerEntity.setAttributes());
    }
}
