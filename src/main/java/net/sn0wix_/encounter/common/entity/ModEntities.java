package net.sn0wix_.encounter.common.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.entity.custom.CrawlerEntity;
import net.sn0wix_.encounter.common.entity.custom.PhantomEntity;
import net.sn0wix_.encounter.common.entity.custom.StalkerEntity;

public class ModEntities {
    public static final EntityType<StalkerEntity> STALKER = Registry.register(Registries.ENTITY_TYPE, new Identifier(Encounter.MOD_ID, "stalker"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, StalkerEntity::new).dimensions(EntityDimensions.fixed(0.8f, 4.5f)).build());

    public static final EntityType<CrawlerEntity> CRAWLER = Registry.register(Registries.ENTITY_TYPE, new Identifier(Encounter.MOD_ID, "crawler"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CrawlerEntity::new).dimensions(EntityDimensions.fixed(2.2f, 2.9f)).build());

    public static final EntityType<PhantomEntity> PHANTOM = Registry.register(Registries.ENTITY_TYPE, new Identifier(Encounter.MOD_ID, "phantom"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PhantomEntity::new).dimensions(EntityDimensions.fixed(0.7f, 4.4f)).build());
}
