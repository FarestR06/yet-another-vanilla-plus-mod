package com.farestr06.yavpm.item;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.fluid.YavpmFluids;
import com.farestr06.yavpm.item.custom.*;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.world.component.YavpmDataComponentTypes;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.HashMap;
import java.util.Map;

import static com.farestr06.api.item.ItemHelper.*;
import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class YavpmItems {

    public static final Map<Item, Holder<Potion>> CRIMSON_MOONGUS_FOOD = new HashMap<>();
    public static final Map<Item, Holder<Potion>> CRIMSON_MOONGUS_FOOD_CORRUPTED = new HashMap<>();
    public static final Map<Item, Holder<Potion>> WARPED_MOONGUS_FOOD = new HashMap<>();

    public static final Item PHANTOM_CHORD = makeItem(
            makeId("phantom_chord"),
            new Item.Properties().rare()
    );

    public static final Item THUNDER_SHARD = makeItem(
            makeId("thunder_shard"),
            new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );

    public static final Item BAKING_SODA = makeSimpleItem(makeId("baking_soda"));
    public static final Item PRETZEL = makeItem(makeId("pretzel"), new Item.Properties().food(YavpmFoods.PRETZEL));

    public static final Item WARPED_WART = YavpmBlocks.WARPED_WART_CROP.asItem();
    public static final Item BANANA_SEEDS = YavpmBlocks.BANANA_CROP.asItem();
    public static final Item RICE_SEEDS = YavpmBlocks.RICE_CROP.asItem();
    public static final Item PEANUT = YavpmBlocks.PEANUT_CROP.asItem();
    public static final Item MAGIC_BEAN = YavpmBlocks.MAGIC_BEAN_CROP.asItem();
    public static final Item BITTER_BERRIES = YavpmBlocks.BITTER_BERRY_BUSH.asItem();

    public static final ResourceKey<Item> CANTALOUPE_SEEDS_KEY = ResourceKey.create(Registries.ITEM, makeId("cantaloupe_seeds"));
    public static final Item CANTALOUPE_SEEDS = register(CANTALOUPE_SEEDS_KEY, settings -> new BlockItem(YavpmBlocks.CANTALOUPE_STEM, settings.useItemDescriptionPrefix()));
    public static final Item CANTALOUPE_SLICE = makeItem(makeId("cantaloupe_slice"), new Item.Properties().food(Foods.MELON_SLICE));

    public static final Item ACORN = YavpmBlocks.OAK_SAPLING_CROP.asItem();
    public static final Item BIRCH_SEEDS = YavpmBlocks.BIRCH_SAPLING_CROP.asItem();
    public static final Item SPRUCE_CONE = makeSimpleItem(makeId("spruce_cone")); // TODO: Make Spruce Crop
    public static final Item CRIMSON_SPORE = YavpmBlocks.CRIMSON_FUNGUS_CROP.asItem();
    public static final Item WARPED_SPORE = YavpmBlocks.WARPED_FUNGUS_CROP.asItem();

    public static final Item COOKED_PEANUT = makeItem(makeId("cooked_peanut"), new Item.Properties().food(YavpmFoods.COOKED_PEANUT, Consumables.DRIED_KELP));
    public static final Item BREADING = makeSimpleItem(makeId("breading"));
    public static final Item FRIED_BANANA = makeItem(
            makeId("fried_banana"),
            new Item.Properties().food(YavpmFoods.FRIED_BANANA)
    );
    public static final Item FRIED_COD = makeItem(
            makeId("fried_cod"),
            new Item.Properties().food(YavpmFoods.FRIED_COD)
    );
    public static final Item DIAMOND_ACORN = makeItem(
            makeId("diamond_acorn"),
            new Item.Properties()
                    .food(YavpmFoods.DIAMOND_ACORN, Consumables.ENCHANTED_GOLDEN_APPLE)
                    .rare()
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );
    // endregion
    // region Persimmon Fruit
    public static final Item PERSIMMON = makeItem(
            makeId("persimmon"),
            new Item.Properties().food(Foods.APPLE)
    );
    public static final Item GOLDEN_PERSIMMON = makeItem(
            makeId("golden_persimmon"),
            new Item.Properties().food(Foods.GOLDEN_APPLE).rare()
    );
    // endregion
    // region Food ingredients
    public static final Item TRUFFLE = makeItem(
            makeId("truffle"),
            new Item.Properties().food(YavpmFoods.TRUFFLE)
    );
    public static final Item BANANA = makeItem(
            makeId("banana"),
            new Item.Properties().food(YavpmFoods.BANANA)
    );
    public static final Item RICE = makeItem(
            makeId("rice"),
            new Item.Properties()
    );
    // endregion
    public static final Item JELLY = makeItem(
            makeId("jelly"),
            new Item.Properties().food(YavpmFoods.JELLY)
    );
    public static final Item SWEET_BERRY_JELLY = makeItem(
            makeId("sweet_berry_jelly"),
            new Item.Properties().food(YavpmFoods.SWEET_BERRY_JELLY)
    );
    public static final Item RICE_BAR = makeItem(
            makeId("rice_bar"),
            new Item.Properties().food(YavpmFoods.RICE_BAR)
    );
    public static final Item RICE_PASTRY = makeItem(
            makeId("rice_pastry"),
            new Item.Properties().food(YavpmFoods.RICE_PASTRY)
    );
    public static final Item SUSHI = makeItem(
            makeId("sushi"),
            new Item.Properties().food(YavpmFoods.SUSHI)
    );
    public static final Item SEA_SOUP = makeItem(
            makeId("sea_soup"),
            new Item.Properties().food(YavpmFoods.SEA_SOUP).stacksTo(1)
    );
    public static final Item CHICKEN_SOUP = makeItem(
            makeId("chicken_soup"),
            new Item.Properties().food(YavpmFoods.CHICKEN_SOUP).stacksTo(1)
    );
    public static final Item FANCY_MUSHROOM_STEW = makeItem(
            makeId("fancy_mushroom_stew"),
            new Item.Properties().food(YavpmFoods.FANCY_MUSHROOM_STEW, YavpmFoods.ConsumableComponents.FANCY_MUSHROOM_STEW)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .rarity(Rarity.EPIC)
                    .stacksTo(1)
    );

    public static final Item CHOCOLATE = makeItem(
            makeId("chocolate"),
            new Item.Properties().food(YavpmFoods.CHOCOLATE)
    );

    public static final Item FORTUNE_COOKIE = makeAdvancedItem(
            makeId("fortune_cookie"),
            FortuneCookieItem::new,
            new Item.Properties().food(Foods.COOKIE)
    );

    public static final Item LUCKY_SLIP = makeItem(
            makeId("lucky_slip"),
            new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .stacksTo(1)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .component(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY)
    );

    public static final Item CHEESE = makeItem(makeId("cheese"), new Item.Properties().food(YavpmFoods.CHEESE));

    public static final Item BEAN_TOAST = makeItem(makeId("bean_toast"), new Item.Properties().food(YavpmFoods.BEAN_TOAST));
    public static final Item COOKED_EGG = makeItem(makeId("cooked_egg"), new Item.Properties().food(YavpmFoods.COOKED_EGG));

    // region Fake Animal Product
    public static final Item FAKE_BEEF = makeItem(
            makeId("fake_beef"),
            new Item.Properties().food(Foods.BEEF)
    );
    public static final Item COOKED_FAKE_BEEF = makeItem(
            makeId("cooked_fake_beef"),
            new Item.Properties().food(Foods.COOKED_BEEF)
    );
    public static final Item FAKE_MILK_BUCKET = makeItem(
            makeId("fake_milk_bucket"),
            new Item.Properties().craftRemainder(Items.BUCKET)
                    .component(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET)
                    .usingConvertsTo(Items.BUCKET).stacksTo(1)
    );
    // endregion

    public static final Item TOFU = makeItem(makeId("tofu"), new Item.Properties().food(YavpmFoods.CHEESE));

    public static final Item GAUNTLET_FRAGMENT = makeItem(
            makeId("gauntlet_fragment"),
            new Item.Properties().fireResistant().rare()
    );

    public static final Item GAUNTLET = makeAdvancedItem(
            makeId("gauntlet"),
            GauntletItem::new,
            new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .durability(575)
                    .attributes(GauntletItem.createAttributeModifiers())
                    .component(DataComponents.TOOL, GauntletItem.createToolComponent())
    );

    public static final Item RAW_DIAMOND = makeSimpleItem(makeId("raw_diamond"));
    public static final Item GRAPHITE = makeSimpleItem(makeId("graphite"));

    public static final Item CARBON_EGG = makeAdvancedItem(makeId("carbon_egg"), EggItem::new, new Item.Properties()
            .stacksTo(16).component(YavpmDataComponentTypes.Item.HATCHES_CARBONFOWL, Unit.INSTANCE)
    );

    public static final Item CHAINMAIL = makeSimpleItem(makeId("chainmail"));

    public static final Item MOLY = makeItem(
            makeId("moly"),
            new Item.Properties().rarity(Rarity.UNCOMMON)
                    .food(YavpmFoods.MOLY, YavpmFoods.ConsumableComponents.MOLY_COMPONENT).stacksTo(16)
    );

    // region Wood
    public static final Item APPLE_SIGN = makeAdvancedItem(
            makeId("apple_sign"),
            settings -> new SignItem(
                    YavpmBlocks.APPLE_SIGN, YavpmBlocks.APPLE_WALL_SIGN, settings
            ), new Item.Properties()
    );
    public static final Item APPLE_HANGING_SIGN = makeAdvancedItem(
            makeId("apple_hanging_sign"),
            settings -> new HangingSignItem(
                    YavpmBlocks.APPLE_HANGING_SIGN, YavpmBlocks.APPLE_WALL_HANGING_SIGN, settings
            ), new Item.Properties()
    );

    public static final ResourceLocation APPLE_BOAT_ID = makeId("apple");
    public static final Item APPLE_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_BOAT_ID, false);
    public static final Item APPLE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(APPLE_BOAT_ID, true);

    public static final Item PERSIMMON_SIGN = makeAdvancedItem(
            makeId("persimmon_sign"),
            settings -> new SignItem(
                    YavpmBlocks.PERSIMMON_SIGN, YavpmBlocks.PERSIMMON_WALL_SIGN, settings
            ), new Item.Properties()
    );
    public static final Item PERSIMMON_HANGING_SIGN = makeAdvancedItem(
            makeId("persimmon_hanging_sign"),
            settings -> new HangingSignItem(
                    YavpmBlocks.PERSIMMON_HANGING_SIGN, YavpmBlocks.PERSIMMON_WALL_HANGING_SIGN, settings
            ), new Item.Properties()
    );
    public static final ResourceLocation PERSIMMON_BOAT_ID = makeId("persimmon");
    public static final Item PERSIMMON_BOAT = TerraformBoatItemHelper.registerBoatItem(PERSIMMON_BOAT_ID, false);
    public static final Item PERSIMMON_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(PERSIMMON_BOAT_ID, true);

    public static final Item PRICKLE_SIGN = makeAdvancedItem(
            makeId("prickle_sign"),
            settings -> new SignItem(
                    YavpmBlocks.PRICKLE_SIGN, YavpmBlocks.PRICKLE_WALL_SIGN, settings
            ), new Item.Properties()
    );
    public static final Item PRICKLE_HANGING_SIGN = makeAdvancedItem(
            makeId("prickle_hanging_sign"),
            settings -> new HangingSignItem(
                    YavpmBlocks.PRICKLE_HANGING_SIGN, YavpmBlocks.PRICKLE_WALL_HANGING_SIGN, settings
            ), new Item.Properties()
    );
    // endregion

    // region Reactor
    public static final Item REACTOR = makeAdvancedItem(
            makeId("reactor"),
            ReactorItem::new, new Item.Properties().durability(1024)
    );
    public static final Item HEATED_REACTOR = makeAdvancedItem(
            makeId("heated_reactor"),
            ReactorItem::new, new Item.Properties().durability(1024)
    );
    // endregion

    public static final Item BABY_KEY = makeAdvancedItem(
            makeId("baby_key"),
            BabyKeyItem::new,
            new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)
    );

    // region Densitite
    public static final Item DENSITITE_INGOT = makeItem(
            makeId("densitite_ingot"),
            new Item.Properties().rarity(Rarity.UNCOMMON)
    );
    public static final Item DENSITITE_UPGRADE_SMITHING_TEMPLATE = makeAdvancedItem(
            makeId("densitite_upgrade_smithing_template"), DensititeSmithingTemplateHelper::createDensititeUpgrade,
            new Item.Properties().rarity(Rarity.UNCOMMON)
    );


    public static final Item DENSITITE_SWORD = makeAdvancedItem(
            makeId("densitite_sword"),
            Item::new,
            new Item.Properties().sword(DensititeMaterial.TOOL_MATERIAL, 3f, -2.4f).rare()
    );
    public static final Item DENSITITE_SHOVEL = makeAdvancedItem(
            makeId("densitite_shovel"),
            Item::new,
            new Item.Properties().shovel(DensititeMaterial.TOOL_MATERIAL, 1.5f, -3f).rare()
    );
    public static final Item DENSITITE_PICKAXE = makeAdvancedItem(
            makeId("densitite_pickaxe"),
            Item::new,
            new Item.Properties().pickaxe(DensititeMaterial.TOOL_MATERIAL, 1f, -2.8f).rare()
    );
    public static final Item DENSITITE_AXE = makeAdvancedItem(
            makeId("densitite_axe"),
            Item::new,
            new Item.Properties().axe(DensititeMaterial.TOOL_MATERIAL, 5f, -3f).rare()
    );
    public static final Item DENSITITE_HOE = makeAdvancedItem(
            makeId("densitite_hoe"),
            Item::new,
            new Item.Properties().hoe(DensititeMaterial.TOOL_MATERIAL, -5f, -3f).rare()
    );

    public static final Item DENSITITE_HELMET = makeAdvancedItem(
            makeId("densitite_helmet"), Item::new,
            new Item.Properties().humanoidArmor(DensititeMaterial.ARMOR_MATERIAL, ArmorType.HELMET).rare()
    );
    public static final Item DENSITITE_CHESTPLATE = makeAdvancedItem(
            makeId("densitite_chestplate"), Item::new,
            new Item.Properties().humanoidArmor(DensititeMaterial.ARMOR_MATERIAL, ArmorType.CHESTPLATE).rare()
    );
    public static final Item DENSITITE_LEGGINGS = makeAdvancedItem(
            makeId("densitite_leggings"), Item::new,
            new Item.Properties().humanoidArmor(DensititeMaterial.ARMOR_MATERIAL, ArmorType.LEGGINGS).rare()
    );
    public static final Item DENSITITE_BOOTS = makeAdvancedItem(
            makeId("densitite_boots"), Item::new,
            new Item.Properties().humanoidArmor(DensititeMaterial.ARMOR_MATERIAL, ArmorType.BOOTS).rare()
    );
    // endregion

    public static final Item NULLIUM_NUGGET = HANDLER.instance().nulliumExperiment ? makeItem(
            makeId("nullium_nugget"), new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    ) : Items.POISONOUS_POTATO;

    public static final Item COPPER_HORN = makeAdvancedItem(
            makeId("copper_horn"),
            CopperHornItem::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)
    );

    // region Studded Armor
    public static final Item STUDDED_HELMET = makeAdvancedItem(
            makeId("studded_helmet"), Item::new,
            new Item.Properties().humanoidArmor(StuddedMaterial.ARMOR_MATERIAL, ArmorType.HELMET)
    );
    public static final Item STUDDED_CHESTPLATE = makeAdvancedItem(
            makeId("studded_chestplate"), Item::new,
            new Item.Properties().humanoidArmor(StuddedMaterial.ARMOR_MATERIAL, ArmorType.CHESTPLATE)
    );
    public static final Item STUDDED_LEGGINGS = makeAdvancedItem(
            makeId("studded_leggings"), Item::new,
            new Item.Properties().humanoidArmor(StuddedMaterial.ARMOR_MATERIAL, ArmorType.LEGGINGS)
    );
    public static final Item STUDDED_BOOTS = makeAdvancedItem(
            makeId("studded_boots"), Item::new,
            new Item.Properties().humanoidArmor(StuddedMaterial.ARMOR_MATERIAL, ArmorType.BOOTS)
    );
    // endregion

    public static final Item MUSIC_DISC_MAGNETIC_CIRCUIT = makeItem(
            makeId("music_disc_magnetic_circuit"),
            new Item.Properties().rare().jukeboxPlayable(YavpmSounds.MAGNETIC_CIRCUIT_KEY).stacksTo(1)
    );
    public static final Item DISC_FRAGMENT_MAGNETIC_CIRCUIT = makeAdvancedItem(
            makeId("disc_fragment_magnetic_circuit"),
            DiscFragmentItem::new, new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final Item MUSIC_DISC_HALLAND_DALARNA = makeItem(
            makeId("music_disc_halland_dalarna"),
            new Item.Properties().rarity(Rarity.UNCOMMON).jukeboxPlayable(YavpmSounds.HALLAND_DALARNA_KEY).stacksTo(1)
    );

    // region Spawn Eggs
    public static final Item CARBONFOWL_SPAWN_EGG = makeAdvancedItem(
            makeId("carbonfowl_spawn_egg"),
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(YavpmEntities.CARBONFOWL)
    );

    public static final Item MOONGUS_SPAWN_EGG = makeAdvancedItem(
            makeId("moongus_spawn_egg"),
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(YavpmEntities.MOONGUS)
    );

    public static final Item TANUKI_SPAWN_EGG = makeAdvancedItem(
            makeId("tanuki_spawn_egg"),
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(YavpmEntities.TANUKI)
    );

    public static final Item SUNBURN_SPAWN_EGG = makeAdvancedItem(
            makeId("sunburn_spawn_egg"),
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(YavpmEntities.SUNBURN)
    );

    public static final Item VOID_PHANTOM_SPAWN_EGG = makeAdvancedItem(
            makeId("void_phantom_spawn_egg"),
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(YavpmEntities.VOID_PHANTOM)
    );
    // endregion
    public static final Item VOID_WATER_BUCKET = makeAdvancedItem(
            makeId("void_water_bucket"),
            settings -> new BucketItem(
                    YavpmFluids.STILL_VOID_WATER,
                    settings
            ),
            new Item.Properties().craftRemainder(Items.BUCKET)
    );

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering items for YAVPM!");

        makeBlockItem(YavpmBlocks.CANTALOUPE, new Item.Properties());

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
        WARPED_MOONGUS_FOOD.put(Items.FERMENTED_SPIDER_EYE, Potions.WEAKNESS);
        WARPED_MOONGUS_FOOD.put(Items.WITHER_ROSE, YavpmPotions.DECAY);
        WARPED_MOONGUS_FOOD.put(Items.DRAGON_BREATH, YavpmPotions.VOID_TOUCHED);
        WARPED_MOONGUS_FOOD.put(BITTER_BERRIES, YavpmPotions.HASTE);
        WARPED_MOONGUS_FOOD.put(Items.SWEET_BERRIES, YavpmPotions.INTOXICATION);
    }

    private static void setUpRegistries() {
        // Make Heated Reactor usable as fuel
        YetAnotherVanillaPlusMod.LOGGER.debug("Making Reactor usable as fuel...");
        FuelRegistryEvents.BUILD.register((builder, context) ->
                builder.add(HEATED_REACTOR, context.baseSmeltTime() * 16));

        // Make new crops compostable
        YetAnotherVanillaPlusMod.LOGGER.debug("Making items compostable...");
        final CompostingChanceRegistry compostables = CompostingChanceRegistry.INSTANCE;
        compostables.add(YavpmBlocks.FLOWERING_APPLE_LEAVES.asItem(), 0.3f);
        compostables.add(YavpmBlocks.PERSIMMON_LEAVES.asItem(), 0.3f);
        compostables.add(YavpmBlocks.APPLE_SAPLING.asItem(), 0.3f);
        compostables.add(YavpmBlocks.PERSIMMON_SAPLING.asItem(), 0.3f);
        compostables.add(YavpmBlocks.PRICKLE_SHOOT.asItem(), 0.3f);
        compostables.add(BITTER_BERRIES, 0.3f);
        compostables.add(BANANA_SEEDS, 0.3f);
        compostables.add(RICE_SEEDS, 0.3f);
        compostables.add(ACORN, 0.3f);

        compostables.add(PEANUT, 0.5f);
        compostables.add(COOKED_PEANUT, 0.5f);
        compostables.add(MAGIC_BEAN, 0.5f);

        compostables.add(WARPED_WART, 0.65f);
        compostables.add(BANANA, 0.65f);
        compostables.add(RICE, 0.65f);
        compostables.add(PERSIMMON, 0.65f);

        compostables.add(RICE_BAR, 0.85f);
        compostables.add(RICE_PASTRY, 0.85f);

        compostables.add(MOLY, 1f);
        compostables.add(TRUFFLE, 1f);

        ComponentTooltipAppenderRegistry.addAfter(DataComponents.INSTRUMENT, YavpmDataComponentTypes.Item.COPPER_INSTRUMENT);
    }

    private static void setUpComponents() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Modifying default item components...");
        // make Glistering Melon edible
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.GLISTERING_MELON_SLICE, builder -> {
                builder.set(DataComponents.FOOD, YavpmFoods.GLISTERING_MELON_SLICE);
                builder.set(DataComponents.CONSUMABLE, Consumables.DEFAULT_FOOD);
            });
            if (HANDLER.instance().potionStacking) {
                context.modify(Items.POTION, builder -> builder.set(DataComponents.MAX_STACK_SIZE, 16));
                context.modify(Items.SPLASH_POTION, builder -> builder.set(DataComponents.MAX_STACK_SIZE, 16));
                context.modify(Items.LINGERING_POTION, builder -> builder.set(DataComponents.MAX_STACK_SIZE, 16));
            }
        });
    }
}
