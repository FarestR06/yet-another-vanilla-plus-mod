package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.mob.YavpmMobs;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.custom.*;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.MapColor;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.HashMap;
import java.util.Map;

import static com.farestr06.api.item.ItemHelper.*;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.entity.YavpmBoats.*;
import static com.farestr06.yavpm.item.YavpmArmorMaterials.STUDDED;

public class YavpmItems {

    public static final Map<Item, RegistryEntry<Potion>> CRIMSON_MOONGUS_FOOD = new HashMap<>();
    public static final Map<Item, RegistryEntry<Potion>> WARPED_MOONGUS_FOOD = new HashMap<>();

    public static final Item WARPED_WART = makeAdvancedItem(
            makeId("warped_wart"),
            new AliasedBlockItem(YavpmBlocks.WARPED_WART, new Item.Settings())
    );

    // region Runes

    public static final Item RUNE_ATTACK = makeAdvancedItem(
            makeId("rune_attack"),
            new RuneItem(Text.translatable("item.yavpm.rune_attack.tooltip").formatted(Formatting.GRAY))
    );
    public static final Item RUNE_DURABILITY = makeAdvancedItem(
            makeId("rune_durability"),
            new RuneItem(Text.translatable("item.yavpm.rune_durability.tooltip").formatted(Formatting.GRAY))
    );
    public static final Item RUNE_SPEED = makeAdvancedItem(
            makeId("rune_speed"),
            new RuneItem(Text.translatable("item.yavpm.rune_speed.tooltip").formatted(Formatting.GRAY))
    );
    public static final Item RUNE_TOUGHNESS = makeAdvancedItem(
            makeId("rune_toughness"),
            new RuneItem(Text.translatable("item.yavpm.rune_toughness.tooltip").formatted(Formatting.GRAY))
    );

    // endregion

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
    // region Food ingredients
    public static final Item TRUFFLE = makeItem(
            makeId("truffle"),
            new Item.Settings().food(YavpmFoods.TRUFFLE)
    );

    public static final Item RICE = makeItem(
            makeId("rice"),
            new Item.Settings()
    );

    public static final Item MAGIC_BEAN = makeAdvancedItem(
            makeId("magic_bean"),
            new AliasedBlockItem(YavpmBlocks.MAGIC_BEAN_CROP, new Item.Settings().food(YavpmFoods.MAGIC_BEAN))
    );
    // endregion

    public static final Item SEA_SOUP = makeItem(
            makeId("sea_soup"),
            new Item.Settings().food(YavpmFoods.SEA_SOUP).maxCount(1)
    );

    public static final Item CHOCOLATE = makeItem(
            makeId("chocolate"),
            new Item.Settings().food(YavpmFoods.CHOCOLATE)
    );

    public static final Item FORTUNE_COOKIE = makeAdvancedItem(
            makeId("fortune_cookie"),
            new FortuneCookieItem(new Item.Settings().food(FoodComponents.COOKIE))
    );

    public static final Item LUCKY_SLIP = makeAdvancedItem(
            makeId("lucky_slip"),
            new LuckySlipItem(
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .maxCount(1)
                            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                            .component(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT)
            )
    );

    public static final Item CHEESE = makeItem(makeId("cheese"), new Item.Settings().food(YavpmFoods.CHEESE));

    public static final Item FAKE_BEEF = makeItem(
            makeId("fake_beef"),
            new Item.Settings().food(FoodComponents.BEEF)
    );
    public static final Item COOKED_FAKE_BEEF = makeItem(
            makeId("cooked_fake_beef"),
            new Item.Settings().food(FoodComponents.COOKED_BEEF)
    );
    public static final Item FAKE_MILK_BUCKET = makeAdvancedItem(
            makeId("fake_milk_bucket"),
            new MilkBucketItem(new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1))
    );

    public static final Item TOFU = makeItem(makeId("tofu"), new Item.Settings().food(YavpmFoods.CHEESE));

    public static final Item GRAPHITE = makeSimpleItem(makeId("graphite"));

    public static final Item SOUL_POWDER = makeAdvancedItem(
            makeId("soul_powder"),
            new SoulPowderItem(new Item.Settings())
    );

    // Magic Herb
    public static final Item MOLY = makeAdvancedItem(
            makeId("moly"),
            new MolyItem(new Item.Settings().maxCount(16))
    );

    // region Reactor
    public static final Item REACTOR = makeAdvancedItem(
            makeId("reactor"),
            new ReactorItem(new Item.Settings().maxDamage(1024))
    );
    public static final Item HEATED_REACTOR = makeAdvancedItem(
            makeId("heated_reactor"),
            new ReactorItem(new Item.Settings().maxDamage(1024))
    );
    // endregion

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

    // region Terraform Block Items
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
    public static final Item PRICKLE_SIGN = makeAdvancedItem(
            makeId("prickle_sign"),
            new SignItem(
                    new Item.Settings().maxCount(16),
                    YavpmBlocks.PRICKLE_SIGN,
                    YavpmBlocks.PRICKLE_WALL_SIGN
            )
    );
    public static final Item PRICKLE_HANGING_SIGN = makeAdvancedItem(
            makeId("prickle_hanging_sign"),
            new HangingSignItem(
                    YavpmBlocks.PRICKLE_HANGING_SIGN,
                    YavpmBlocks.PRICKLE_WALL_HANGING_SIGN,
                    new Item.Settings().maxCount(16)
            )
    );

    public static final Item APPLE_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_BOAT_ID, APPLE_BOAT_KEY, false);
    public static final Item APPLE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_CHEST_BOAT_ID, APPLE_BOAT_KEY, true);
    // endregion

    // region Spawn Eggs
    public static final Item MOONGUS_SPAWN_EGG = makeAdvancedItem(
            makeId("moongus_spawn_egg"),
            new SpawnEggItem(YavpmMobs.MOONGUS, MapColor.BRIGHT_TEAL.color, MapColor.RED.color, new Item.Settings())
    );

    public static final Item CARBONFOWL_SPAWN_EGG = makeAdvancedItem(
            makeId("carbonfowl_spawn_egg"),
            new SpawnEggItem(YavpmMobs.CARBONFOWL, 0x191919, 0x4aedd9, new Item.Settings())
    );
    // endregion
    public static final Item VOID_WATER_BUCKET = makeAdvancedItem(
            makeId("void_water_bucket"),
            new BucketItem(
                    YavpmFluids.STILL_VOID_WATER,
                    new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)
            )
    );

    private static void addToNatural(FabricItemGroupEntries entries) {
        entries.add(YavpmBlocks.APPLE_LOG);
        entries.add(YavpmBlocks.APPLE_LEAVES);
        entries.add(YavpmBlocks.APPLE_SAPLING);
        entries.add(BANANA_SEEDS);
        entries.add(PEANUT);
        entries.add(MAGIC_BEAN);
        entries.add(ACORN);
    }
    private static void addToFunctional(FabricItemGroupEntries entries) {
        entries.add(APPLE_SIGN);
        entries.add(APPLE_HANGING_SIGN);
    }
    private static void addToFoodAndDrink(FabricItemGroupEntries entries) {
        entries.add(TRUFFLE);
        entries.add(CHOCOLATE);
        entries.add(CHEESE);
        entries.add(BANANA);
        entries.add(PEANUT);
        entries.add(COOKED_PEANUT);
        entries.add(ACORN);
        entries.add(DIAMOND_ACORN);
        entries.add(MOLY);
        entries.add(SEA_SOUP);
        entries.add(MAGIC_BEAN);
        entries.add(FAKE_BEEF);
        entries.add(COOKED_FAKE_BEEF);
        entries.add(FAKE_MILK_BUCKET);
        entries.add(TOFU);
    }
    private static void addToIngredients(FabricItemGroupEntries entries) {
        entries.add(GRAPHITE);
        entries.add(YavpmBlocks.GRAPHITE_BLOCK);
        entries.add(YavpmBlocks.GRAPHENE_BLOCK);

        entries.add(RICE);
        entries.add(MAGIC_BEAN);
        entries.add(SOUL_POWDER);
        entries.add(REACTOR);

        entries.add(RUNE_ATTACK);
        entries.add(RUNE_DURABILITY);
        entries.add(RUNE_SPEED);
        entries.add(RUNE_TOUGHNESS);
    }
    private static void addToTools(FabricItemGroupEntries entries) {
        entries.add(VOID_WATER_BUCKET);
        entries.add(FAKE_MILK_BUCKET);
        entries.add(FORTUNE_COOKIE);
        entries.add(APPLE_BOAT);
        entries.add(APPLE_CHEST_BOAT);
    }
    private static void addToCombat(FabricItemGroupEntries entries) {
        entries.add(STUDDED_HELMET);
        entries.add(STUDDED_CHESTPLATE);
        entries.add(STUDDED_LEGGINGS);
        entries.add(STUDDED_BOOTS);
    }
    private static void addToSpawnEggs(FabricItemGroupEntries entries) {
        entries.add(CARBONFOWL_SPAWN_EGG);
        entries.add(MOONGUS_SPAWN_EGG);
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering items for YAVPM!");

        setUpComponents();
        setUpItemGroups();
        setUpRegistries();
        setUpMoongusFood();
    }

    private static void setUpMoongusFood() {
        CRIMSON_MOONGUS_FOOD.put(Items.SUGAR, Potions.SWIFTNESS);
        CRIMSON_MOONGUS_FOOD.put(Items.RABBIT_FOOT, Potions.LEAPING);
        CRIMSON_MOONGUS_FOOD.put(Items.BLAZE_POWDER, Potions.STRENGTH);
        CRIMSON_MOONGUS_FOOD.put(Items.GLISTERING_MELON_SLICE, Potions.HEALING);
        CRIMSON_MOONGUS_FOOD.put(Items.SPIDER_EYE, Potions.POISON);
        CRIMSON_MOONGUS_FOOD.put(Items.GHAST_TEAR, Potions.REGENERATION);
        CRIMSON_MOONGUS_FOOD.put(Items.MAGMA_CREAM, Potions.HEALING);
        CRIMSON_MOONGUS_FOOD.put(Items.PUFFERFISH, Potions.WATER_BREATHING);
        CRIMSON_MOONGUS_FOOD.put(Items.GOLDEN_CARROT, Potions.NIGHT_VISION);
        CRIMSON_MOONGUS_FOOD.put(Items.TURTLE_HELMET, Potions.TURTLE_MASTER);
        CRIMSON_MOONGUS_FOOD.put(Items.PHANTOM_MEMBRANE, Potions.SLOW_FALLING);
        CRIMSON_MOONGUS_FOOD.put(Items.BREEZE_ROD, Potions.WIND_CHARGED);
        CRIMSON_MOONGUS_FOOD.put(Items.COBWEB, Potions.WEAVING);
        CRIMSON_MOONGUS_FOOD.put(Items.SLIME_BLOCK, Potions.OOZING);
        CRIMSON_MOONGUS_FOOD.put(Items.STONE, Potions.INFESTED);
        CRIMSON_MOONGUS_FOOD.put(Items.WITHER_SKELETON_SKULL, YavpmPotions.DECAY);
        CRIMSON_MOONGUS_FOOD.put(Items.ENDER_EYE, YavpmPotions.VOIDED);

        WARPED_MOONGUS_FOOD.put(Items.FERMENTED_SPIDER_EYE, Potions.WEAKNESS);
        WARPED_MOONGUS_FOOD.put(Items.WITHER_SKELETON_SKULL, YavpmPotions.DECAY);
        WARPED_MOONGUS_FOOD.put(Items.ENDER_EYE, YavpmPotions.VOIDED);
    }

    private static void setUpRegistries() {
        // Make Heated Reactor usable as fuel
        FuelRegistry.INSTANCE.add(YavpmItems.HEATED_REACTOR, 1600);

        // Make new crops compostable
        CompostingChanceRegistry compostables = CompostingChanceRegistry.INSTANCE;
        compostables.add(YavpmBlocks.APPLE_LEAVES.asItem(), 0.3f);
        compostables.add(BANANA_SEEDS, 0.3f);
        compostables.add(ACORN, 0.3f);
        compostables.add(PEANUT, 0.5f);
        compostables.add(COOKED_PEANUT, 0.5f);
        compostables.add(BANANA, 0.65f);
        compostables.add(MOLY, 1f);
        compostables.add(MAGIC_BEAN, 0.65f);
        compostables.add(TRUFFLE, 0.65f);
        compostables.add(SEA_SOUP, 0.65f);
    }

    private static void setUpItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(YavpmItems::addToNatural);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(YavpmItems::addToFunctional);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(YavpmItems::addToFoodAndDrink);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(YavpmItems::addToIngredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(YavpmItems::addToTools);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(YavpmItems::addToCombat);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(YavpmItems::addToSpawnEggs);
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
