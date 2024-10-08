package com.farestr06.yafm.item;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.item.custom.MolyItem;
import com.farestr06.yafm.item.custom.ReactorItem;
import com.farestr06.yafm.item.custom.SoulPowderItem;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;

import static com.farestr06.api.item.ItemHelper.*;
import static com.farestr06.yafm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yafm.entity.YavpmBoats.*;
import static com.farestr06.yafm.item.ModArmorMaterials.STUDDED;

public class YavpmItems {

    // region Banana
    public static final Item BANANA = makeItem(
            makeId("banana"),
            new Item.Settings().food(YavpmFoods.BANANA)
    );
    public static final Item BANANA_SEEDS = makeAdvancedItem(
            makeId("banana_seeds"),
            new AliasedBlockItem(YavpmBlocks.BANANA_CROP, new Item.Settings())
    );
    // endregion
    // region Peanut
    public static final Item PEANUT = makeAdvancedItem(
            makeId("peanut"),
            new AliasedBlockItem(YavpmBlocks.PEANUT_CROP, new Item.Settings().food(YavpmFoods.RAW_PEANUT))
    );
    public static final Item COOKED_PEANUT = makeItem(
            makeId("cooked_peanut"),
            new Item.Settings().food(YavpmFoods.COOKED_PEANUT)
    );
    // endregion
    // region Oak
    public static final Item ACORN = makeAdvancedItem(
            makeId("acorn"),
            new AliasedBlockItem(YavpmBlocks.OAK_SAPLING_CROP, new Item.Settings().food(YavpmFoods.ACORN))
    );
    public static final Item DIAMOND_ACORN = makeItem(
            makeId("diamond_acorn"),
            new Item.Settings()
                    .food(YavpmFoods.DIAMOND_ACORN)
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
    );
    // endregion

    public static final Item CHOCOLATE = makeItem(
            makeId("chocolate"),
            new Item.Settings().food(YavpmFoods.CHOCOLATE)
    );

    public static final Item SOUL_POWDER = makeAdvancedItem(
            makeId("soul_powder"),
            new SoulPowderItem(new Item.Settings())
    );

    // Magic Herb
    public static final Item MOLY = makeAdvancedItem(
            makeId("moly"),
            new MolyItem(new Item.Settings().maxCount(16))
    );

    public static final Item REACTOR = makeAdvancedItem(
            makeId("reactor"),
            new ReactorItem(new Item.Settings().maxDamage(1024))
    );
    public static final Item HEATED_REACTOR = makeAdvancedItem(
            makeId("heated_reactor"),
            new ReactorItem(new Item.Settings().maxDamage(1024))
    );

    // region Studded Armor
    public static final Item STUDDED_HELMET = makeAdvancedItem(
            makeId("studded_helmet"),
            new ArmorItem(
                    STUDDED,
                    ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(20))
            )
    );
    public static final Item STUDDED_CHESTPLATE = makeAdvancedItem(
            makeId("studded_chestplate"),
            new ArmorItem(
                    STUDDED,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(20))
            )
    );
    public static final Item STUDDED_LEGGINGS = makeAdvancedItem(
            makeId("studded_leggings"),
            new ArmorItem(
                    STUDDED,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(20))
            )
    );
    public static final Item STUDDED_BOOTS = makeAdvancedItem(
            makeId("studded_boots"),
            new ArmorItem(
                    STUDDED,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(20))
            )
    );
    // endregion

    public static final Item APPLE_SIGN = makeAdvancedItem(
            makeId("apple_sign"),
            new SignItem(
                    new Item.Settings().maxCount(16),
                    YavpmBlocks.APPLE_SIGN,
                    YavpmBlocks.APPLE_WALL_SIGN
            )
    );
    public static final Item APPLE_HANGING_SIGN = makeAdvancedItem(
            makeId("apple_hanging_sign"),
            new HangingSignItem(
                    YavpmBlocks.APPLE_HANGING_SIGN,
                    YavpmBlocks.APPLE_WALL_HANGING_SIGN,
                    new Item.Settings().maxCount(16)
            )
    );

    public static final Item APPLE_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_BOAT_ID, APPLE_BOAT_KEY, false);
    public static final Item APPLE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_CHEST_BOAT_ID, APPLE_BOAT_KEY, false);

    private static void addToNatural(FabricItemGroupEntries entries) {
        entries.add(BANANA_SEEDS);
        entries.add(PEANUT);
        entries.add(ACORN);
    }
    private static void addToFunctional(FabricItemGroupEntries entries) {
        entries.add(APPLE_SIGN);
        entries.add(APPLE_HANGING_SIGN);
    }
    private static void addToFoodAndDrink(FabricItemGroupEntries entries) {
        entries.add(CHOCOLATE);
        entries.add(BANANA);
        entries.add(PEANUT);
        entries.add(COOKED_PEANUT);
        entries.add(ACORN);
        entries.add(DIAMOND_ACORN);
        entries.add(MOLY);
    }
    private static void addToIngredients(FabricItemGroupEntries entries) {
        entries.add(SOUL_POWDER);
        entries.add(REACTOR);
    }
    private static void addToTools(FabricItemGroupEntries entries) {
        entries.add(APPLE_BOAT);
        entries.add(APPLE_CHEST_BOAT);
    }
    private static void addToCombat(FabricItemGroupEntries entries) {
        entries.add(STUDDED_HELMET);
        entries.add(STUDDED_CHESTPLATE);
        entries.add(STUDDED_LEGGINGS);
        entries.add(STUDDED_BOOTS);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering items for YAVPM!");

        setUpComponents();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(YavpmItems::addToNatural);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(YavpmItems::addToFunctional);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(YavpmItems::addToFoodAndDrink);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(YavpmItems::addToIngredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(YavpmItems::addToTools);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(YavpmItems::addToCombat);
        FuelRegistry.INSTANCE.add(YavpmItems.HEATED_REACTOR, 1600);
    }

    private static void setUpComponents() {
        // make Glistering Melon edible

        DefaultItemComponentEvents.MODIFY.register(context ->
                context.modify(Items.GLISTERING_MELON_SLICE, builder ->
                        builder.add(DataComponentTypes.FOOD, YavpmFoods.GLISTERING_MELON_SLICE)
                )
        );
    }
}
