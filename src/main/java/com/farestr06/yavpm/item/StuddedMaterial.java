package com.farestr06.yavpm.item;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class StuddedMaterial {

    public static final ResourceKey<EquipmentAsset> ARMOR_KEY = ResourceKey.create(
            EquipmentAssets.ROOT_ID, makeId("studded")
    );

    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(
            20,
            Map.of(
                    ArmorType.HELMET, 6,
                    ArmorType.CHESTPLATE, 7,
                    ArmorType.LEGGINGS, 5,
                    ArmorType.BOOTS, 1
                    ),
            22,
            YavpmSounds.ITEM_ARMOR_EQUIP_STUDDED,
            0f,
            0f,
            YavpmTags.Items.REPAIRS_STUDDED_ARMOR,
            ARMOR_KEY
    );
}
