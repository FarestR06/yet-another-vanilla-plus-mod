package com.farestr06.yafm.item;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.item.custom.MolyItem;
import com.farestr06.yafm.item.custom.ReactorItem;
import com.farestr06.yafm.item.custom.SoulPowderItem;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class YavpmItems {


    public static final Item BANANA = registerItem("banana", new Item(new Item.Settings().food(YavpmFoods.BANANA)));
    public static final Item BANANA_SEEDS = registerItem("banana_seeds", new AliasedBlockItem(YavpmBlocks.BANANA_CROP, new Item.Settings()));

    public static final Item PEANUT = registerItem("peanut", new AliasedBlockItem(YavpmBlocks.PEANUT_CROP, new Item.Settings().food(YavpmFoods.RAW_PEANUT)));
    public static final Item COOKED_PEANUT = registerItem("cooked_peanut", new Item(new Item.Settings().food(YavpmFoods.COOKED_PEANUT)));

    public static final Item CHOCOLATE = registerItem("chocolate", new Item(new Item.Settings().food(YavpmFoods.CHOCOLATE)));

    public static final Item SOUL_POWDER = registerItem("soul_powder", new SoulPowderItem(
            new Item.Settings()
    ));

    public static final Item MOLY = registerItem("moly", new MolyItem(
            new Item.Settings().maxCount(16)
    ));

    public static final Item REACTOR = registerItem("reactor", new ReactorItem(
            new Item.Settings().maxDamage(1024)
    ));
    public static final Item HEATED_REACTOR = registerItem("heated_reactor", new ReactorItem(
            new Item.Settings().maxDamage(1024)
    ));

    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, YetAnotherVanillaPlusMod.makeId(id), item);
    }

    private static void addToNatural(FabricItemGroupEntries entries) {
        entries.add(BANANA_SEEDS);
        entries.add(PEANUT);
    }
    private static void addToFoodAndDrink(FabricItemGroupEntries entries) {
        entries.add(BANANA);
        entries.add(PEANUT);
        entries.add(COOKED_PEANUT);
        entries.add(MOLY);
    }
    private static void addToIngredients(FabricItemGroupEntries entries) {
        entries.add(SOUL_POWDER);
        entries.add(REACTOR);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering items for YAVPM!");

        setUpComponents();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(YavpmItems::addToNatural);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(YavpmItems::addToFoodAndDrink);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(YavpmItems::addToIngredients);
        FuelRegistry.INSTANCE.add(YavpmItems.HEATED_REACTOR, 1600);
    }

    private static void setUpComponents() {
        DefaultItemComponentEvents.MODIFY.register(context ->
                context.modify(Items.GLISTERING_MELON_SLICE, builder ->
                        builder.add(DataComponentTypes.FOOD, YavpmFoods.GLISTERING_MELON_SLICE)
                )
        );
    }
}
