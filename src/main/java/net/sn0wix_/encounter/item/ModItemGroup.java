package net.sn0wix_.encounter.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sn0wix_.encounter.Encounter;

public class ModItemGroup {
    public static final ItemGroup NORMAL_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(Encounter.MOD_ID,"item_tab"), FabricItemGroup.builder().displayName(Text.translatable("incounter.item_tab")).icon(() -> new ItemStack(Items.WITHER_SKELETON_SKULL)).build());
    public static final RegistryKey<ItemGroup> NORMAL_ITEM_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Encounter.MOD_ID, "item_tab"));

    public static void registerModItemGroups(){
        Encounter.LOGGER.info("Registering item groups for " + Encounter.MOD_ID);
    }
}
