package net.sn0wix_.encounter.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.Encounter;
import net.sn0wix_.encounter.entity.ModEntities;

public class ModItems {
    public static final Item KEVOSAURUS_REX_SPAWN_EGG = registerItem("stalker_spawn_egg",
            new SpawnEggItem(ModEntities.STALKER, 7025684, 14861373, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        Item item1 = Registry.register(Registries.ITEM, new Identifier(Encounter.MOD_ID, name), item);
        addItemToGroup(item1, ModItemGroup.NORMAL_ITEM_GROUP_KEY);

        return item1;
    }

    private static void addItemToGroup(Item item, RegistryKey<ItemGroup> group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> {
            content.add(item);
        });
    }

    public static void registerModItems() {
        Encounter.LOGGER.info("Registering Mod items for " + Encounter.MOD_ID);
    }
}
