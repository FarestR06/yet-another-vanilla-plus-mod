package com.farestr06.yavpm.village;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class YavpmTrades {
    private static final float LOW_MULTIPLIER = 0.05f;
    private static final float HIGH_MULTIPLIER = 0.2f;

    private static final List<Item> WANDERING_TRADER_DISCS = new ArrayList<>();

    private static final List<Item> NOVICE_PLANK_BUY_OFFERS = List.of(
            Items.OAK_PLANKS,
            Items.SPRUCE_PLANKS,
            Items.BIRCH_PLANKS,
            YavpmBlocks.APPLE_PLANKS.asItem()
    );
    private static final List<Item> APPRENTICE_PLANK_BUY_OFFERS = List.of(
            Items.JUNGLE_PLANKS,
            Items.ACACIA_PLANKS,
            Items.DARK_OAK_PLANKS
    );
    private static final List<Item> EXPERT_PLANK_BUY_OFFERS = List.of(
            Items.MANGROVE_PLANKS,
            Items.CHERRY_PLANKS,
            YavpmBlocks.PERSIMMON_PLANKS.asItem(),
            Items.PALE_OAK_PLANKS
    );
    private static final List<Item> MASTER_PLANK_BUY_OFFERS = List.of(
            Items.CRIMSON_PLANKS,
            Items.WARPED_PLANKS,
            YavpmBlocks.PRICKLE_PLANKS.asItem()
    );
    
    private static final List<Item> NOVICE_SAPLING_SELL_OFFERS = List.of(
            Items.OAK_SAPLING,
            Items.SPRUCE_SAPLING,
            Items.BIRCH_SAPLING,
            YavpmBlocks.APPLE_SAPLING.asItem()
    );
    private static final List<Item> APPRENTICE_SAPLING_SELL_OFFERS = List.of(
            Items.JUNGLE_SAPLING,
            Items.ACACIA_SAPLING,
            Items.DARK_OAK_SAPLING
    );
    private static final List<Item> EXPERT_SAPLING_SELL_OFFERS = List.of(
            Items.MANGROVE_PROPAGULE,
            Items.CHERRY_SAPLING,
            YavpmBlocks.PERSIMMON_SAPLING.asItem(),
            Items.PALE_OAK_SAPLING
    );

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering trade offers for YAVPM!");
        farmer();
        mason();
        butcher();
        fisherman();
        armorer();
        wanderingTrader();

        // Custom professions
        lumberjack();
    }

    private static void lumberjack() {
        // region Novice
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Lumberjack Novice trades...");
        TradeOfferHelper.registerVillagerOffers(YavpmProfessions.LUMBERJACK, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(NOVICE_PLANK_BUY_OFFERS.get(random.nextInt(NOVICE_PLANK_BUY_OFFERS.size())), 8),
                    new ItemStack(Items.EMERALD), 16, 2, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 4),
                    new ItemStack(NOVICE_SAPLING_SELL_OFFERS.get(
                            random.nextInt(NOVICE_SAPLING_SELL_OFFERS.size())
                    ), 3),
                    16, 1, LOW_MULTIPLIER
            ));
        });
        // endregion
        // region Apprentice
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Lumberjack Apprentice trades...");
        TradeOfferHelper.registerVillagerOffers(YavpmProfessions.LUMBERJACK, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    new ItemStack(Items.BOWL, 3),
                    12, 5, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(APPRENTICE_PLANK_BUY_OFFERS.get(random.nextInt(APPRENTICE_PLANK_BUY_OFFERS.size())), 8),
                    new ItemStack(Items.EMERALD), 16, 10, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 4),
                    new ItemStack(APPRENTICE_SAPLING_SELL_OFFERS.get(
                            random.nextInt(APPRENTICE_SAPLING_SELL_OFFERS.size())
                    ), 3),
                    16, 10, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    Optional.of(new TradedItem(
                            NOVICE_PLANK_BUY_OFFERS.get(random.nextInt(NOVICE_PLANK_BUY_OFFERS.size())), 2
                    )),
                    new ItemStack(Items.STICK, 7),
                    12, 10, LOW_MULTIPLIER
            ));
        });
        // endregion
        // region Journeyman
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Lumberjack Journeyman trades...");
        TradeOfferHelper.registerVillagerOffers(YavpmProfessions.LUMBERJACK, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(Items.CHISELED_BOOKSHELF),
                    12,
                    10, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 3),
                    new ItemStack(Items.CHARCOAL, 12),
                    12, 10, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.BAMBOO, 15),
                    new ItemStack(Items.EMERALD),
                    12, 20, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    Optional.of(new TradedItem(
                            APPRENTICE_PLANK_BUY_OFFERS.get(random.nextInt(APPRENTICE_PLANK_BUY_OFFERS.size())), 2
                    )),
                    new ItemStack(Items.STICK, 7),
                    12, 20, LOW_MULTIPLIER
            ));
        });
        // endregion
        // region Expert
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Lumberjack Expert trades...");
        TradeOfferHelper.registerVillagerOffers(YavpmProfessions.LUMBERJACK, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 4),
                    new ItemStack(random.nextBoolean() ? Items.CHEST : Items.BARREL, 2),
                    12,
                    15, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(EXPERT_PLANK_BUY_OFFERS.get(random.nextInt(EXPERT_PLANK_BUY_OFFERS.size())), 8),
                    new ItemStack(Items.EMERALD), 16, 30, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 4),
                    new ItemStack(EXPERT_SAPLING_SELL_OFFERS.get(
                            random.nextInt(EXPERT_SAPLING_SELL_OFFERS.size())
                    ), 3),
                    16, 15, LOW_MULTIPLIER
            ));
        });
        // endregion
        // region Master
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Lumberjack Master trades...");
        TradeOfferHelper.registerVillagerOffers(YavpmProfessions.LUMBERJACK, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 11),
                    new ItemStack(Items.JUKEBOX), 12, 30, HIGH_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(MASTER_PLANK_BUY_OFFERS.get(random.nextInt(MASTER_PLANK_BUY_OFFERS.size())), 8),
                    new ItemStack(Items.EMERALD, 2), 16, 30, LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    Optional.of(new TradedItem(
                            EXPERT_PLANK_BUY_OFFERS.get(random.nextInt(EXPERT_PLANK_BUY_OFFERS.size())), 2
                    )),
                    new ItemStack(Items.STICK, 7),
                    12, 30, LOW_MULTIPLIER
            ));
        });
        // endregion
    }

    private static void wanderingTrader() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Wandering Trader trades...");
        setUpDiscList();
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> {
                if (random.nextFloat() <= 0.19f) {
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, random.nextBetween(12, 16)),
                            new ItemStack(YavpmItems.MUSIC_DISC_HALLAND_DALARNA),
                            1,
                            0,
                            0f
                    );
                } else {
                    int randInt = random.nextInt(WANDERING_TRADER_DISCS.size());
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, random.nextBetween(7, 10)),
                            new ItemStack(WANDERING_TRADER_DISCS.get(randInt)),
                            1,
                            0,
                            0f
                    );
                }
            });
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    new ItemStack(YavpmItems.BITTER_BERRIES),
                    12,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    new ItemStack(YavpmItems.BANANA_SEEDS),
                    8,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    new ItemStack(YavpmItems.PEANUT),
                    12,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 2),
                    new ItemStack(YavpmItems.ACORN),
                    8,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD),
                    new ItemStack(YavpmItems.MAGIC_BEAN),
                    12,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(YavpmItems.MOLY),
                    16,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(YavpmBlocks.APPLE_SAPLING),
                    8,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(YavpmBlocks.PERSIMMON_SAPLING),
                    8,
                    0,
                    0f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 8),
                    new ItemStack(YavpmItems.MOLY),
                    3,
                    0,
                    0f
            ));
        });
    }

    private static void armorer() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Armorer Expert trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, random.nextBetween(7, 18)),
                    Optional.of(new TradedItem(Items.LEATHER_HELMET)),
                    new ItemStack(YavpmItems.STUDDED_HELMET),
                    5,
                    15,
                    HIGH_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, random.nextBetween(7, 18)),
                    Optional.of(new TradedItem(Items.LEATHER_LEGGINGS)),
                    new ItemStack(YavpmItems.STUDDED_LEGGINGS),
                    5,
                    15,
                    HIGH_MULTIPLIER
            ));
        });
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Armorer Master trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, random.nextBetween(7, 18)),
                    Optional.of(new TradedItem(Items.LEATHER_CHESTPLATE)),
                    new ItemStack(YavpmItems.STUDDED_CHESTPLATE),
                    5,
                    15,
                    HIGH_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, random.nextBetween(7, 18)),
                    Optional.of(new TradedItem(Items.LEATHER_BOOTS)),
                    new ItemStack(YavpmItems.STUDDED_BOOTS),
                    5,
                    15,
                    HIGH_MULTIPLIER
            ));
        });
    }

    private static void fisherman() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Fisherman Expert trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 3),
                    new ItemStack(YavpmItems.SUSHI, 4),
                    12,
                    15,
                    LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(YavpmItems.SEA_SOUP),
                    12,
                    15,
                    LOW_MULTIPLIER
            ));
        });
    }

    private static void mason() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Mason Master trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 5, factories ->
                factories.add((entity, random) -> {
                    int amount = random.nextBetween(8, 16);
                    return new TradeOffer(
                        new TradedItem(Items.EMERALD, 20),
                        Optional.of(new TradedItem(YavpmBlocks.KIMBERLITE, amount)),
                        new ItemStack(YavpmItems.RAW_DIAMOND, amount),
                        12,
                        30,
                        HIGH_MULTIPLIER
                    );
                }));
    }

    private static void butcher() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Butcher Novice trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 1, factories ->
                factories.add((entity, random) -> new TradeOffer(
                        new TradedItem(Items.EMERALD),
                        new ItemStack(YavpmItems.CHICKEN_SOUP),
                        12,
                        1,
                        LOW_MULTIPLIER
                ))
        );
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Butcher Master trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 5, factories ->
                factories.add((entity, random) -> new TradeOffer(
                new TradedItem(Items.EMERALD, 3),
                new ItemStack(YavpmItems.CHEESE, 6),
                12,
                30,
                LOW_MULTIPLIER
        )));
    }

    private static void farmer() {
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Farmer Novice trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(YavpmItems.MAGIC_BEAN, 24),
                    new ItemStack(Items.EMERALD),
                    16,
                    2,
                    LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(YavpmItems.PEANUT, 28),
                    new ItemStack(Items.EMERALD),
                    16,
                    2,
                    LOW_MULTIPLIER
            ));
        });
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Farmer Journeyman trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, random.nextBetween(24, 32)),
                    new ItemStack(YavpmItems.FORTUNE_COOKIE),
                    4,
                    10,
                    HIGH_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 3),
                    new ItemStack(YavpmItems.RICE, 16),
                    12,
                    10,
                    LOW_MULTIPLIER
            ));
        });
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Farmer Master trades...");
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5, factories ->
                factories.add((entity, random) -> new TradeOffer(
                new TradedItem(Items.EMERALD, random.nextBetween(8, 16)),
                new ItemStack(YavpmItems.FORTUNE_COOKIE),
                1,
                30,
                HIGH_MULTIPLIER
        )));
    }

    private static void setUpDiscList() {
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_13, 8);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_CAT, 8);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_WAIT, 6);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_MELLOHI, 6);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_STAL, 5);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_STRAD, 5);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_BLOCKS, 4);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_CHIRP, 4);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_MALL, 3);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_WARD, 3);
        addABunchOfItemsToDiscList(Items.MUSIC_DISC_OTHERSIDE, 1);
        Collections.shuffle(WANDERING_TRADER_DISCS);
    }

    private static void addABunchOfItemsToDiscList(Item item, int count) {
        for (int i = 0; i < count; i++) {
            WANDERING_TRADER_DISCS.add(item);
        }
    }
}
