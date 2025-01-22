package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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

    /*
    private static final List<ArmorMaterial.Layer> LAYERS = List.of(
            new ArmorMaterial.Layer(
                    makeId("studded"),
                    "",
                    true)
            ,new ArmorMaterial.Layer(
                    makeId("studded"),
                    "_overlay",
                    false)
    );

    public static final RegistryEntry<ArmorMaterial> STUDDED = registerArmorMaterial(
            "studded",
            () -> new ArmorMaterial(
                    Util.make(
                            new EnumMap<>(ArmorItem.Type.class),
                            map -> {
                                map.put(ArmorItem.Type.BOOTS, 1);
                                map.put(ArmorItem.Type.LEGGINGS, 5);
                                map.put(ArmorItem.Type.CHESTPLATE, 7);
                                map.put(ArmorItem.Type.HELMET, 6);
                            }
                            ),
                    21,
                    YavpmSounds.ITEM_ARMOR_EQUIP_STUDDED,
                    () -> Ingredient.ofItems(
                            Items.LEATHER,
                            Items.IRON_INGOT
                            ),
                    LAYERS,
                    0f,
                    0f
            )
    );

     */
}
