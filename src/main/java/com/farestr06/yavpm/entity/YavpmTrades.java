package com.farestr06.yavpm.entity;

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

import java.util.List;
import java.util.Optional;

public class YavpmTrades {
    private static final float LOW_MULTIPLIER = 0.05f;
    private static final float HIGH_MULTIPLIER = 0.2f;

    private static final List<Item> WANDERING_TRADER_DEFAULT_DISCS = List.of(
            Items.MUSIC_DISC_13,
            Items.MUSIC_DISC_CAT,
            Items.MUSIC_DISC_WAIT,
            Items.MUSIC_DISC_MELLOHI
    );

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering trade offers for YAVPM!");

        // region Farmer
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
        // endregion
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
        // region Armorer
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
        // endregion
        // region Wandering Trader
        YetAnotherVanillaPlusMod.LOGGER.debug("Creating Wandering Trader trades...");
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> {
                if (random.nextFloat() <= 0.19f) {
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, 12),
                            new ItemStack(YavpmItems.MUSIC_DISC_HALLAND_DALARNA),
                            1,
                            0,
                            0f
                    );
                } else {
                    int index = random.nextInt(4);
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, 8),
                            new ItemStack(WANDERING_TRADER_DEFAULT_DISCS.get(index)),
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
                    new TradedItem(Items.EMERALD, 3),
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
        // endregion
    }
}
