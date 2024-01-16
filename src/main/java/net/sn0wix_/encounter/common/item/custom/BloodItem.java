package net.sn0wix_.encounter.common.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;

public class BloodItem extends Item implements Equipment {
    public BloodItem(Settings settings) {
        super(settings);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}
