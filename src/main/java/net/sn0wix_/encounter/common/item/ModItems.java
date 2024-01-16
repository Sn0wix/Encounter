package net.sn0wix_.encounter.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.common.Encounter;
import net.sn0wix_.encounter.common.entity.ModEntities;
import net.sn0wix_.encounter.common.item.custom.BloodItem;

public class ModItems {
    public static final Item STALKER_SPAWN_EGG = registerItem("stalker_spawn_egg",
            new SpawnEggItem(ModEntities.STALKER, 7025684, 14861373, new FabricItemSettings()));

    public static final Item CRAWLER_SPAWN_EGG = registerItem("crawler_spawn_egg",
            new SpawnEggItem(ModEntities.CRAWLER, 15790320, 1315860, new FabricItemSettings()));

    public static final Item PHANTOM_SPAWN_EGG = registerItem("phantom_spawn_egg",
            new SpawnEggItem(ModEntities.PHANTOM, 4144959, 16777215, new FabricItemSettings()));

    public static final Item BLOOD = registerItem("blood", new BloodItem(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        Item item1 = Registry.register(Registries.ITEM, new Identifier(Encounter.MOD_ID, name), item);
        addItemToGroup(item1);
        return item1;
    }

    private static void addItemToGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.NORMAL_ITEM_GROUP_KEY).register(content -> content.add(item));
    }

    public static void registerModItems() {
        Encounter.LOGGER.info("Registering Mod items for " + Encounter.MOD_ID);
    }
}
