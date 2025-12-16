package com.farestr06.yavpm.item;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class DensititeMaterial {
    public static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            1801,
            2.5f,
            6f,
            20,
            YavpmTags.Items.DENSITITE_TOOL_MATERIALS
    );
    public static final int BASE_ARMOR_DURABILITY = 34;
    public static final ResourceKey<EquipmentAsset> ARMOR_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, makeId("densitite"));
    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(
            BASE_ARMOR_DURABILITY,
            Map.of(
                    ArmorType.BOOTS, 4,
                    ArmorType.LEGGINGS, 7,
                    ArmorType.CHESTPLATE, 9,
                    ArmorType.HELMET, 4,
                    ArmorType.BODY, 12
            ), 22, YavpmSounds.ITEM_ARMOR_EQUIP_DENSITITE, 1f, 0.3f,
            YavpmTags.Items.REPAIRS_DENSITITE_ARMOR, ARMOR_KEY
    );
}
