package com.farestr06.yavpm.item;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;

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
    public static final RegistryKey<EquipmentAsset> ARMOR_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, makeId("densitite"));
    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(
            BASE_ARMOR_DURABILITY,
            Map.of(
                    EquipmentType.BOOTS, 4,
                    EquipmentType.LEGGINGS, 7,
                    EquipmentType.CHESTPLATE, 9,
                    EquipmentType.HELMET, 4,
                    EquipmentType.BODY, 12
            ), 22, YavpmSounds.ITEM_ARMOR_EQUIP_DENSITITE, 1f, 0.3f,
            YavpmTags.Items.REPAIRS_DENSITITE_ARMOR, ARMOR_KEY
    );
}
