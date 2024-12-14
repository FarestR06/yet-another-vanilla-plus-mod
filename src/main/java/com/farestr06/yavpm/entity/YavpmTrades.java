package com.farestr06.yavpm.entity;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.List;
import java.util.Optional;

public class YavpmTrades {
    private static final float LOW_MULTIPLIER = 0.05f;
    private static final float HIGH_MULTIPLIER = 0.2f;

    public static void init() {
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
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, random.nextBetween(3, 32)),
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
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5, factories ->
                factories.add((entity, random) -> new TradeOffer(
                new TradedItem(Items.EMERALD, random.nextBetween(1, 16)),
                new ItemStack(YavpmItems.FORTUNE_COOKIE),
                3,
                30,
                HIGH_MULTIPLIER
        )));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 4),
                    new ItemStack(YavpmItems.SUSHI, 4),
                    12,
                    15,
                    LOW_MULTIPLIER
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 4),
                    Optional.of(new TradedItem(Items.BOWL)),
                    new ItemStack(YavpmItems.SEA_SOUP),
                    12,
                    15,
                    LOW_MULTIPLIER
            ));
        });
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
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
                    new TradedItem(Items.EMERALD, 24),
                    chooseRune(random),
                    1,
                    0,
                    0f
            ));
        });
    }

    private static ItemStack chooseRune(Random rand) {
        List<Item> items = List.of(
                YavpmItems.RUNE_ATTACK,
                YavpmItems.RUNE_DURABILITY,
                YavpmItems.RUNE_SPEED
        );

        Item item = items.get(rand.nextInt(4));
        return new ItemStack(item);
    }
}
