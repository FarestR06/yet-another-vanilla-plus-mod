package com.farestr06.yavpm.item;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;

import java.util.Map;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmArmorMaterials {

    public static final RegistryKey<EquipmentAsset> STUDDED_KEY = RegistryKey.of(
            EquipmentAssetKeys.REGISTRY_KEY, makeId("studded")
    );

    public static final ArmorMaterial STUDDED = new ArmorMaterial(
            20,
            Map.of(
                    EquipmentType.HELMET, 6,
                    EquipmentType.CHESTPLATE, 7,
                    EquipmentType.LEGGINGS, 5,
                    EquipmentType.BOOTS, 1
                    ),
            22,
            YavpmSounds.ITEM_ARMOR_EQUIP_STUDDED,
            0f,
            0f,
            YavpmTags.Items.REPAIRS_STUDDED_ARMOR,
            STUDDED_KEY
    );
}
