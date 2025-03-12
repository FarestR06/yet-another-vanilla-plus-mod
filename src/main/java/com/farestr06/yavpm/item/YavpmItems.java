package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.custom.*;
import com.farestr06.yavpm.util.YavpmSounds;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.HashMap;
import java.util.Map;

import static com.farestr06.api.item.ItemHelper.*;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.item.StuddedMaterial.ARMOR_MATERIAL;

public class YavpmItems {

    public static final Map<Item, RegistryEntry<Potion>> CRIMSON_MOONGUS_FOOD = new HashMap<>();
    public static final Map<Item, RegistryEntry<Potion>> CRIMSON_MOONGUS_FOOD_CORRUPTED = new HashMap<>();
    public static final Map<Item, RegistryEntry<Potion>> WARPED_MOONGUS_FOOD = new HashMap<>();

    public static final Item PHANTOM_CHORD = makeItem(
            makeId("phantom_chord"),
            new Item.Settings().rarity(Rarity.RARE)
    );

    public static final Item THUNDER_SHARD = makeItem(
            makeId("thunder_shard"),
            new Item.Settings().rarity(Rarity.UNCOMMON).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
    );

    public static final Item WARPED_WART = YavpmBlocks.WARPED_WART_CROP.asItem();
    public static final Item BANANA_SEEDS = YavpmBlocks.BANANA_CROP.asItem();
    public static final Item RICE_SEEDS = YavpmBlocks.RICE_CROP.asItem();
    public static final Item PEANUT = YavpmBlocks.PEANUT_CROP.asItem();
    public static final Item ACORN = YavpmBlocks.OAK_SAPLING_CROP.asItem();
    public static final Item MAGIC_BEAN = YavpmBlocks.MAGIC_BEAN_CROP.asItem();
    public static final Item BITTER_BERRIES = YavpmBlocks.BITTER_BERRY_BUSH.asItem();

    public static final Item COOKED_PEANUT = makeItem(makeId("cooked_peanut"), new Item.Settings().food(YavpmFoods.COOKED_PEANUT, ConsumableComponents.DRIED_KELP));
    public static final Item BREADING = makeSimpleItem(makeId("breading"));
    public static final Item FRIED_BANANA = makeItem(
            makeId("fried_banana"),
            new Item.Settings().food(YavpmFoods.FRIED_BANANA)
    );
    public static final Item FRIED_COD = makeItem(
            makeId("fried_cod"),
            new Item.Settings().food(YavpmFoods.FRIED_COD)
    );
    public static final Item DIAMOND_ACORN = makeItem(
            makeId("diamond_acorn"),
            new Item.Settings()
                    .food(YavpmFoods.DIAMOND_ACORN, ConsumableComponents.ENCHANTED_GOLDEN_APPLE)
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
    );
    // endregion
    // region Persimmon Fruit
    public static final Item PERSIMMON = makeItem(
            makeId("persimmon"),
            new Item.Settings().food(FoodComponents.APPLE)
    );
    public static final Item GOLDEN_PERSIMMON = makeItem(
            makeId("golden_persimmon"),
            new Item.Settings().food(FoodComponents.GOLDEN_APPLE).rarity(Rarity.RARE)
    );
    // endregion
    // region Food ingredients
    public static final Item TRUFFLE = makeItem(
            makeId("truffle"),
            new Item.Settings().food(YavpmFoods.TRUFFLE)
    );
    public static final Item BANANA = makeItem(
            makeId("banana"),
            new Item.Settings().food(YavpmFoods.BANANA)
    );
    public static final Item RICE = makeItem(
            makeId("rice"),
            new Item.Settings()
    );
    // endregion
    public static final Item JELLY = makeItem(
            makeId("jelly"),
            new Item.Settings().food(YavpmFoods.JELLY)
    );
    public static final Item SWEET_BERRY_JELLY = makeItem(
            makeId("sweet_berry_jelly"),
            new Item.Settings().food(YavpmFoods.SWEET_BERRY_JELLY)
    );
    public static final Item RICE_BAR = makeItem(
            makeId("rice_bar"),
            new Item.Settings().food(YavpmFoods.RICE_BAR)
    );
    public static final Item RICE_PASTRY = makeItem(
            makeId("rice_pastry"),
            new Item.Settings().food(YavpmFoods.RICE_PASTRY)
    );
    public static final Item SUSHI = makeItem(
            makeId("sushi"),
            new Item.Settings().food(YavpmFoods.SUSHI)
    );
    public static final Item SEA_SOUP = makeItem(
            makeId("sea_soup"),
            new Item.Settings().food(YavpmFoods.SEA_SOUP).maxCount(1)
    );
    public static final Item CHICKEN_SOUP = makeItem(
            makeId("chicken_soup"),
            new Item.Settings().food(YavpmFoods.CHICKEN_SOUP).maxCount(1)
    );
    public static final Item FANCY_MUSHROOM_STEW = makeItem(
            makeId("fancy_mushroom_stew"),
            new Item.Settings().food(YavpmFoods.FANCY_MUSHROOM_STEW, YavpmFoods.ConsumableComponents.FANCY_MUSHROOM_STEW)
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .rarity(Rarity.EPIC)
                    .maxCount(1)
    );

    public static final Item CHOCOLATE = makeItem(
            makeId("chocolate"),
            new Item.Settings().food(YavpmFoods.CHOCOLATE)
    );

    public static final Item FORTUNE_COOKIE = makeAdvancedItem(
            makeId("fortune_cookie"),
            FortuneCookieItem::new,
            new Item.Settings().food(FoodComponents.COOKIE)
    );

    public static final Item LUCKY_SLIP = makeItem(
            makeId("lucky_slip"),
            new Item.Settings()
                    .rarity(Rarity.UNCOMMON)
                    .maxCount(1)
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .component(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT)
    );

    public static final Item CHEESE = makeItem(makeId("cheese"), new Item.Settings().food(YavpmFoods.CHEESE));

    public static final Item BEAN_TOAST = makeItem(makeId("bean_toast"), new Item.Settings().food(YavpmFoods.BEAN_TOAST));
    public static final Item COOKED_EGG = makeItem(makeId("cooked_egg"), new Item.Settings().food(YavpmFoods.COOKED_EGG));

    // region Fake Animal Product
    public static final Item FAKE_BEEF = makeItem(
            makeId("fake_beef"),
            new Item.Settings().food(FoodComponents.BEEF)
    );
    public static final Item COOKED_FAKE_BEEF = makeItem(
            makeId("cooked_fake_beef"),
            new Item.Settings().food(FoodComponents.COOKED_BEEF)
    );
    public static final Item FAKE_MILK_BUCKET = makeItem(
            makeId("fake_milk_bucket"),
            new Item.Settings().recipeRemainder(Items.BUCKET)
                    .component(DataComponentTypes.CONSUMABLE, ConsumableComponents.MILK_BUCKET)
                    .useRemainder(Items.BUCKET).maxCount(1)
    );
    // endregion

    public static final Item TOFU = makeItem(makeId("tofu"), new Item.Settings().food(YavpmFoods.CHEESE));

    public static final Item GAUNTLET_FRAGMENT = makeItem(
            makeId("gauntlet_fragment"),
            new Item.Settings().fireproof().rarity(Rarity.RARE)
    );

    public static final Item GAUNTLET = makeAdvancedItem(
            makeId("gauntlet"),
            GauntletItem::new,
            new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .maxDamage(575)
                    .attributeModifiers(GauntletItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, GauntletItem.createToolComponent())
    );

    public static final Item RAW_DIAMOND = makeSimpleItem(makeId("raw_diamond"));
    public static final Item GRAPHITE = makeSimpleItem(makeId("graphite"));

    public static final Item CHAINMAIL = makeSimpleItem(makeId("chainmail"));

    public static final Item MOLY = makeItem(
            makeId("moly"),
            new Item.Settings().rarity(Rarity.UNCOMMON)
                    .food(YavpmFoods.MOLY, YavpmFoods.ConsumableComponents.MOLY_COMPONENT).maxCount(16)
    );

    // region Wood
    public static final Item APPLE_SIGN = makeAdvancedItem(
            makeId("apple_sign"),
            settings -> new SignItem(
                    YavpmBlocks.APPLE_SIGN, YavpmBlocks.APPLE_WALL_SIGN, settings
            ), new Item.Settings()
    );
    public static final Item APPLE_HANGING_SIGN = makeAdvancedItem(
            makeId("apple_hanging_sign"),
            settings -> new HangingSignItem(
                    YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.APPLE_WALL_HANGING_SIGN, settings
            ), new Item.Settings()
    );

    public static final Identifier APPLE_BOAT_ID = makeId("apple");
    public static final Item APPLE_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_BOAT_ID, false);
    public static final Item APPLE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_BOAT_ID, true);

    public static final Item PERSIMMON_SIGN = makeAdvancedItem(
            makeId("persimmon_sign"),
            settings -> new SignItem(
                    YavpmBlocks.PERSIMMON_SIGN, YavpmBlocks.PERSIMMON_WALL_SIGN, settings
            ), new Item.Settings()
    );
    public static final Item PERSIMMON_HANGING_SIGN = makeAdvancedItem(
            makeId("persimmon_hanging_sign"),
            settings -> new HangingSignItem(
                    YavpmBlocks.PERSIMMON_HANGING_SIGN, YavpmBlocks.PERSIMMON_WALL_HANGING_SIGN, settings
            ), new Item.Settings()
    );
    public static final Identifier PERSIMMON_BOAT_ID = makeId("persimmon");
    public static final Item PERSIMMON_BOAT = TerraformBoatItemHelper.registerBoatItem(PERSIMMON_BOAT_ID, false);
    public static final Item PERSIMMON_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(PERSIMMON_BOAT_ID, true);

    public static final Item PRICKLE_SIGN = makeAdvancedItem(
            makeId("prickle_sign"),
            settings -> new SignItem(
                    YavpmBlocks.PRICKLE_SIGN, YavpmBlocks.PRICKLE_WALL_SIGN, settings
            ), new Item.Settings()
    );
    public static final Item PRICKLE_HANGING_SIGN = makeAdvancedItem(
            makeId("prickle_hanging_sign"),
            settings -> new HangingSignItem(
                    YavpmBlocks.PRICKLE_HANGING_SIGN, YavpmBlocks.PRICKLE_WALL_HANGING_SIGN, settings
            ), new Item.Settings()
    );
    // endregion

    // region Reactor
    public static final Item REACTOR = makeAdvancedItem(
            makeId("reactor"),
            ReactorItem::new, new Item.Settings().maxDamage(1024)
    );
    public static final Item HEATED_REACTOR = makeAdvancedItem(
            makeId("heated_reactor"),
            ReactorItem::new, new Item.Settings().maxDamage(1024)
    );
    // endregion

    public static final Item BABY_KEY = makeAdvancedItem(
            makeId("baby_key"),
            BabyKeyItem::new,
            new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)
    );

    // region Densitite
    public static final Item DENSITITE_INGOT = makeItem(
            makeId("densitite_ingot"),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    public static final Item DENSITITE_UPGRADE_SMITHING_TEMPLATE = makeAdvancedItem(
            makeId("densitite_upgrade_smithing_template"), DensititeSmithingTemplateHelper::createDensititeUpgrade,
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );


    public static final Item DENSITITE_SWORD = makeAdvancedItem(
            makeId("densitite_sword"),
            settings -> new SwordItem(DensititeMaterial.TOOL_MATERIAL, 3f, -2.4f, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    public static final Item DENSITITE_SHOVEL = makeAdvancedItem(
            makeId("densitite_shovel"),
            settings -> new ShovelItem(DensititeMaterial.TOOL_MATERIAL, 1.5f, -3f, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    public static final Item DENSITITE_PICKAXE = makeAdvancedItem(
            makeId("densitite_pickaxe"),
            settings -> new PickaxeItem(DensititeMaterial.TOOL_MATERIAL, 1f, -2.8f, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    public static final Item DENSITITE_AXE = makeAdvancedItem(
            makeId("densitite_axe"),
            settings -> new AxeItem(DensititeMaterial.TOOL_MATERIAL, 5f, -3f, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    public static final Item DENSITITE_HOE = makeAdvancedItem(
            makeId("densitite_hoe"),
            settings -> new HoeItem(DensititeMaterial.TOOL_MATERIAL, -5f, -3f, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );

    public static final Item DENSITITE_HELMET = makeAdvancedItem(
            makeId("densitite_helmet"),
            settings -> new ArmorItem(DensititeMaterial.ARMOR_MATERIAL, EquipmentType.HELMET, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    public static final Item DENSITITE_CHESTPLATE = makeAdvancedItem(
            makeId("densitite_chestplate"),
            settings -> new ArmorItem(DensititeMaterial.ARMOR_MATERIAL, EquipmentType.CHESTPLATE, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    public static final Item DENSITITE_LEGGINGS = makeAdvancedItem(
            makeId("densitite_leggings"),
            settings -> new ArmorItem(DensititeMaterial.ARMOR_MATERIAL, EquipmentType.LEGGINGS, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    public static final Item DENSITITE_BOOTS = makeAdvancedItem(
            makeId("densitite_boots"),
            settings -> new ArmorItem(DensititeMaterial.ARMOR_MATERIAL, EquipmentType.BOOTS, settings),
            new Item.Settings().rarity(Rarity.RARE)
    );
    // endregion

    public static final Item COPPER_HORN = makeAdvancedItem(
            makeId("copper_horn"),
            CopperHornItem::new,
            new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)
    );

    // region Studded Armor
    public static final Item STUDDED_HELMET = makeAdvancedItem(
            makeId("studded_helmet"),
            settings -> new ArmorItem(
                    ARMOR_MATERIAL,
                    EquipmentType.HELMET,
                    settings
            ),
            new Item.Settings().maxDamage(EquipmentType.HELMET.getMaxDamage(20))
    );
    public static final Item STUDDED_CHESTPLATE = makeAdvancedItem(
            makeId("studded_chestplate"),
            settings -> new ArmorItem(
                    ARMOR_MATERIAL,
                    EquipmentType.CHESTPLATE,
                    settings
            ),
            new Item.Settings().maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(20))
    );
    public static final Item STUDDED_LEGGINGS = makeAdvancedItem(
            makeId("studded_leggings"),
            settings -> new ArmorItem(
                    ARMOR_MATERIAL,
                    EquipmentType.LEGGINGS,
                    settings
            ),
            new Item.Settings().maxDamage(EquipmentType.LEGGINGS.getMaxDamage(20))
    );
    public static final Item STUDDED_BOOTS = makeAdvancedItem(
            makeId("studded_boots"),
            settings -> new ArmorItem(
                    ARMOR_MATERIAL,
                    EquipmentType.BOOTS,
                    settings
            ),
            new Item.Settings().maxDamage(EquipmentType.BOOTS.getMaxDamage(20))
    );
    // endregion

    public static final Item MUSIC_DISC_MAGNETIC_CIRCUIT = makeItem(
            makeId("music_disc_magnetic_circuit"),
            new Item.Settings().rarity(Rarity.RARE).jukeboxPlayable(YavpmSounds.MAGNETIC_CIRCUIT_KEY).maxCount(1)
    );
    public static final Item DISC_FRAGMENT_MAGNETIC_CIRCUIT = makeAdvancedItem(
            makeId("disc_fragment_magnetic_circuit"),
            DiscFragmentItem::new, new Item.Settings().rarity(Rarity.UNCOMMON)
    );

    public static final Item MUSIC_DISC_HALLAND_DALARNA = makeItem(
            makeId("music_disc_halland_dalarna"),
            new Item.Settings().rarity(Rarity.UNCOMMON).jukeboxPlayable(YavpmSounds.HALLAND_DALARNA_KEY).maxCount(1)
    );

    // region Spawn Eggs
    public static final Item CARBONFOWL_SPAWN_EGG = makeAdvancedItemWithDefaultSettings(
            makeId("carbonfowl_spawn_egg"),
            settings -> new SpawnEggItem(YavpmEntities.CARBONFOWL, settings)
    );

    public static final Item MOONGUS_SPAWN_EGG = makeAdvancedItemWithDefaultSettings(
            makeId("moongus_spawn_egg"),
            settings -> new SpawnEggItem(YavpmEntities.MOONGUS, settings)
    );

    public static final Item TANUKI_SPAWN_EGG = makeAdvancedItemWithDefaultSettings(
            makeId("tanuki_spawn_egg"),
            settings -> new SpawnEggItem(YavpmEntities.TANUKI, settings)
    );

    public static final Item VOID_PHANTOM_SPAWN_EGG = makeAdvancedItemWithDefaultSettings(
            makeId("void_phantom_spawn_egg"),
            settings -> new SpawnEggItem(YavpmEntities.VOID_PHANTOM, settings)
    );
    // endregion
    public static final Item VOID_WATER_BUCKET = makeAdvancedItem(
            makeId("void_water_bucket"),
            settings -> new BucketItem(
                    YavpmFluids.STILL_VOID_WATER,
                    settings
            ),
            new Item.Settings().recipeRemainder(Items.BUCKET)
    );

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering items for YAVPM!");

        setUpComponents();
        setUpRegistries();
        setUpMoongusFood();
        CopperInstruments.init();
    }

    private static void setUpMoongusFood() {
        // Crimson Moongi create potions brewed from awkward potions.
        YetAnotherVanillaPlusMod.LOGGER.debug("Registering Crimson Moongus foods...");
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

        // Crimson Moongi create different potions when fed a Fermented Spider Eye.
        YetAnotherVanillaPlusMod.LOGGER.debug("Registering corrupted Crimson Moongus foods...");
        CRIMSON_MOONGUS_FOOD_CORRUPTED.put(Items.SUGAR, Potions.SLOWNESS);
        CRIMSON_MOONGUS_FOOD_CORRUPTED.put(Items.RABBIT_FOOT, Potions.SLOWNESS);
        CRIMSON_MOONGUS_FOOD_CORRUPTED.put(Items.GLISTERING_MELON_SLICE, Potions.HARMING);
        CRIMSON_MOONGUS_FOOD_CORRUPTED.put(Items.SPIDER_EYE, Potions.HARMING);
        CRIMSON_MOONGUS_FOOD_CORRUPTED.put(Items.GOLDEN_CARROT, Potions.INVISIBILITY);
        CRIMSON_MOONGUS_FOOD_CORRUPTED.put(Items.PUFFERFISH, YavpmPotions.CHOKING);

        // Warped Moongi create potions brewed from weird potions.
        YetAnotherVanillaPlusMod.LOGGER.debug("Registering Warped Moongus foods...");
        WARPED_MOONGUS_FOOD.put(Items.BREEZE_ROD, Potions.WIND_CHARGED);
        WARPED_MOONGUS_FOOD.put(Items.COBWEB, Potions.WEAVING);
        WARPED_MOONGUS_FOOD.put(Items.SLIME_BLOCK, Potions.OOZING);
        WARPED_MOONGUS_FOOD.put(Items.STONE, Potions.INFESTED);
        WARPED_MOONGUS_FOOD.put(Items.WITHER_ROSE, YavpmPotions.DECAY);
        WARPED_MOONGUS_FOOD.put(VOID_WATER_BUCKET, YavpmPotions.VOID_TOUCHED);
        WARPED_MOONGUS_FOOD.put(BITTER_BERRIES.asItem(), YavpmPotions.HASTE);
        WARPED_MOONGUS_FOOD.put(Items.SWEET_BERRIES, YavpmPotions.INTOXICATION);
    }

    private static void setUpRegistries() {
        // Make Heated Reactor usable as fuel
        YetAnotherVanillaPlusMod.LOGGER.debug("Making Reactor usable as fuel...");
        FuelRegistryEvents.BUILD.register((builder, context) ->
                builder.add(HEATED_REACTOR, context.baseSmeltTime() * 16));

        // Make new crops compostable
        YetAnotherVanillaPlusMod.LOGGER.debug("Making items compostable...");
        CompostingChanceRegistry compostables = CompostingChanceRegistry.INSTANCE;
        compostables.add(YavpmBlocks.APPLE_LEAVES.asItem(), 0.3f);
        compostables.add(YavpmBlocks.PERSIMMON_LEAVES.asItem(), 0.3f);
        compostables.add(YavpmBlocks.APPLE_SAPLING.asItem(), 0.3f);
        compostables.add(YavpmBlocks.PERSIMMON_SAPLING.asItem(), 0.3f);
        compostables.add(YavpmBlocks.PRICKLE_SHOOT.asItem(), 0.3f);
        compostables.add(YavpmBlocks.BANANA_CROP.asItem(), 0.3f);
        compostables.add(YavpmBlocks.RICE_CROP.asItem(), 0.3f);
        compostables.add(YavpmBlocks.OAK_SAPLING_CROP.asItem(), 0.3f);

        compostables.add(YavpmBlocks.PEANUT_CROP.asItem(), 0.5f);
        compostables.add(COOKED_PEANUT, 0.5f);
        compostables.add(YavpmBlocks.MAGIC_BEAN_CROP.asItem(), 0.5f);

        compostables.add(BANANA, 0.65f);
        compostables.add(RICE, 0.65f);
        compostables.add(PERSIMMON, 0.65f);

        compostables.add(RICE_BAR, 0.85f);
        compostables.add(RICE_PASTRY, 0.85f);

        compostables.add(MOLY, 1f);
        compostables.add(TRUFFLE, 1f);
    }

    private static void setUpComponents() {

        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying default item components...");
        // make Glistering Melon edible
        DefaultItemComponentEvents.MODIFY.register(context ->
                context.modify(Items.GLISTERING_MELON_SLICE, builder ->
                        builder.add(DataComponentTypes.FOOD, YavpmFoods.GLISTERING_MELON_SLICE)
                )
        );
    }
}
