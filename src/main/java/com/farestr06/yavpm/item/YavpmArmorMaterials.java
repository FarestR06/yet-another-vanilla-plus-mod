package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmArmorMaterials {
    
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
                                map.put(ArmorItem.Type.BOOTS, 2);
                                map.put(ArmorItem.Type.LEGGINGS, 6);
                                map.put(ArmorItem.Type.CHESTPLATE, 8);
                                map.put(ArmorItem.Type.HELMET, 7);
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

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> materialSupplier) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, makeId(name), materialSupplier.get());
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering armor materials for YAVPM!");
    }
}
