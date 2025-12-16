package com.farestr06.yavpm.datagen;

import com.farestr06.api.util.datagen.VanillaAdvancements;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.datagen.condition.RareEquipmentRecipesEnabledResourceCondition;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.misc.criterion.FakeBlockDestroyedCriterion;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmAdvancementProvider extends FabricAdvancementProvider {
    private static HolderLookup.RegistryLookup<Block> BLOCK_LOOKUP;
    private static HolderLookup.RegistryLookup<Item> ITEM_LOOKUP;
    private static HolderLookup.RegistryLookup<EntityType<?>> ENTITY_LOOKUP;

    // region Story
    protected static final AdvancementHolder SMELT_KIMBERLITE = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Story.MINE_STONE)
            .display(
                    YavpmItems.RAW_DIAMOND,
                    Component.translatable("advancements.story.smelt_kimberlite.title"),
                    Component.translatable("advancements.story.smelt_kimberlite.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    true
            )
            .requirements(AdvancementRequirements.Strategy.OR)
            .addCriterion("craft_diamonds_via_smelting", RecipeCraftedTrigger.TriggerInstance.craftedItem(
                    makeRecipeKey(makeId("diamond_from_smelting_raw_diamond"))
            ))
            .addCriterion("craft_diamonds_via_blasting", RecipeCraftedTrigger.TriggerInstance.craftedItem(
                   makeRecipeKey( makeId("diamond_from_blasting_raw_diamond"))
            ))
            .build(makeId("story/smelt_kimberlite"));
    // endregion
    // region Husbandry
    protected static final AdvancementHolder EAT_FAKE_ANIMAL_PRODUCT = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Husbandry.PLANT_SEED)
            .display(
                    YavpmItems.COOKED_FAKE_BEEF,
                    Component.translatable("advancements.husbandry.eat_fake_animal_product.title"),
                    Component.translatable("advancements.husbandry.eat_fake_animal_product.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
            ).addCriterion("ate_fake_animal_product", ConsumeItemTrigger.TriggerInstance.usedItem(
                    ItemPredicate.Builder.item().of(
                            ITEM_LOOKUP,
                            YavpmItems.FAKE_BEEF,
                            YavpmItems.COOKED_FAKE_BEEF,
                            YavpmItems.FAKE_MILK_BUCKET,
                            YavpmItems.TOFU
                    )
            )).build(makeId("husbandry/eat_fake_animal_product"));

    protected static final AdvancementHolder FED_WOLF_PEANUT = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Husbandry.TAME_AN_ANIMAL)
            .display(
                    YavpmItems.COOKED_PEANUT,
                    Component.translatable("advancements.husbandry.fed_wolf_peanut.title"),
                    Component.translatable("advancements.husbandry.fed_wolf_peanut.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    true
            ).addCriterion("fed_wolf_peanut", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                    ItemPredicate.Builder.item().of(ITEM_LOOKUP, YavpmItems.COOKED_PEANUT),
                    Optional.of(EntityPredicate.wrap(
                            EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(ENTITY_LOOKUP, EntityType.WOLF))))
            )).build(makeId("husbandry/fed_wolf_peanut"));

    protected static final AdvancementHolder LUCKY_TICKET = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Husbandry.PLANT_SEED)
            .display(
                    YavpmItems.FORTUNE_COOKIE,
                    Component.translatable("advancements.husbandry.lucky_ticket.title"),
                    Component.translatable("advancements.husbandry.lucky_ticket.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
            ).addCriterion("ate_fortune_cookie", ConsumeItemTrigger.TriggerInstance.usedItem(
                    ItemPredicate.Builder.item().of(
                            ITEM_LOOKUP,
                            YavpmItems.FORTUNE_COOKIE
                    )
            )).build(makeId("husbandry/lucky_ticket"));

    protected static final AdvancementHolder EAT_ALL_FOOD_BOWLS =
            requireFoodBowlItemsEaten(Advancement.Builder.advancement())
                    .parent(VanillaAdvancements.Husbandry.PLANT_SEED)
                    .display(
                            Items.SUSPICIOUS_STEW,
                            Component.translatable("advancements.husbandry.eat_all_food_bowls.title"),
                            Component.translatable("advancements.husbandry.eat_all_food_bowls.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .rewards(AdvancementRewards.Builder.experience(50))
                    .build(makeId("husbandry/eat_all_food_bowls"));

    protected static final AdvancementHolder MINE_FAKE_BLOCK = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Husbandry.ROOT)
            .display(
                    Blocks.CHERRY_LEAVES,
                    Component.translatable("advancements.husbandry.mine_fake_block.title"),
                    Component.translatable("advancements.husbandry.mine_fake_block.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    true
            )
            .requirements(AdvancementRequirements.Strategy.OR)
            .addCriterion(
                    "mine_fake_log",
                    FakeBlockDestroyedCriterion.Conditions.create(YavpmBlocks.FAKE_LOG, ItemPredicate.Builder.item())
            )
            .addCriterion(
                    "mine_fake_ore",
                    FakeBlockDestroyedCriterion.Conditions.create(YavpmBlocks.FAKE_ORE, ItemPredicate.Builder.item())
            )
            .build(makeId("husbandry/mine_fake_block"));

    protected static final AdvancementHolder CRAFT_DIAMONDS_FROM_GRAPHENE = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Husbandry.BREED_AN_ANIMAL)
            .display(
                    YavpmBlocks.GRAPHENE_BLOCK,
                    Component.translatable("advancements.husbandry.craft_diamonds_from_graphene.title"),
                    Component.translatable("advancements.husbandry.craft_diamonds_from_graphene.description"),
                    null,
                    AdvancementType.CHALLENGE,
                    true,
                    true,
                    false
            )
            .requirements(AdvancementRequirements.Strategy.OR)
            .addCriterion("craft_diamonds_via_smelting", RecipeCraftedTrigger.TriggerInstance.craftedItem(
                    makeRecipeKey(makeId("diamond_from_smelting_graphene_block"))
            ))
            .addCriterion("craft_diamonds_via_blasting", RecipeCraftedTrigger.TriggerInstance.craftedItem(
                    makeRecipeKey(makeId("diamond_from_blasting_graphene_block"))
            ))
            .rewards(AdvancementRewards.Builder.experience(100))
            .build(makeId("husbandry/craft_diamonds_from_graphene"));
    // endregion
    // region Adventure
    protected static final AdvancementHolder LOCK_CONTAINER = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Adventure.SUMMON_IRON_GOLEM)
            .display(
                    YavpmItems.BABY_KEY,
                    Component.translatable("advancements.adventure.lock_container.title"),
                    Component.translatable("advancements.adventure.lock_container.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
            ).addCriterion(
                    "lock",
                    ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                            LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(
                                    BLOCK_LOOKUP,
                                    Blocks.BARREL,
                                    Blocks.BLAST_FURNACE,
                                    Blocks.BREWING_STAND,
                                    Blocks.CHEST,
                                    Blocks.CRAFTER,
                                    Blocks.DISPENSER,
                                    Blocks.DROPPER,
                                    Blocks.FURNACE,
                                    Blocks.HOPPER,
                                    Blocks.SHULKER_BOX,
                                    Blocks.SMOKER,
                                    Blocks.TRAPPED_CHEST
                            )),
                            ItemPredicate.Builder.item().of(ITEM_LOOKUP, YavpmItems.BABY_KEY)
                    ))
            .build(makeId("adventure/lock_container"));
    // endregion
    // region Nether
    protected static final AdvancementHolder CONVERT_COW_TO_MOONGUS = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.Nether.BREW_POTION)
            .display(
                    YavpmItems.CRIMSON_SPORE,
                    Component.translatable("advancements.nether.convert_cow_to_moongus.title"),
                    Component.translatable("advancements.nether.convert_cow_to_moongus.description"),
                    null,
                    AdvancementType.GOAL,
                    true,
                    true,
                    false
            ).addCriterion("fed_cow_wart", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                    ItemPredicate.Builder.item().of(ITEM_LOOKUP, YavpmItems.CRIMSON_SPORE, YavpmItems.WARPED_SPORE),
                    Optional.of(EntityPredicate.wrap(
                            EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(ENTITY_LOOKUP, EntityType.COW))))
            )).build(makeId("nether/convert_cow_to_moongus"));
    // endregion
    // region End
    protected static final AdvancementHolder PLUCK_NEEDLES_FROM_PRICKLE_LOG = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.End.ENTER_END_GATEWAY)
            .display(
                    Items.SHEARS,
                    Component.translatable("advancements.end.pluck_needles_from_prickle_log.title"),
                    Component.translatable("advancements.end.pluck_needles_from_prickle_log.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
            ).addCriterion("pluck", InventoryChangeTrigger.TriggerInstance.hasItems(YavpmBlocks.PRICKLE_SHOOT))
            .build(makeId("end/pluck_needles_from_prickle_log"));

    protected static final AdvancementHolder CRAFT_AN_ELYTRA = Advancement.Builder.advancement()
            .parent(VanillaAdvancements.End.FIND_ELYTRA)
            .display(
                    YavpmItems.PHANTOM_CHORD,
                    Component.translatable("advancements.end.craft_an_elytra.title"),
                    Component.translatable("advancements.end.craft_an_elytra.description"),
                    null,
                    AdvancementType.CHALLENGE,
                    true,
                    true,
                    true
            ).addCriterion("craft_elytra", RecipeCraftedTrigger.TriggerInstance.craftedItem(
                    makeRecipeKey(makeId("elytra"))
            ))
            .rewards(AdvancementRewards.Builder.experience(75))
            .build(makeId("end/craft_an_elytra"));
    // endregion

    protected YavpmAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
        BLOCK_LOOKUP = registryLookup.join().lookupOrThrow(Registries.BLOCK);
        ITEM_LOOKUP = registryLookup.join().lookupOrThrow(Registries.ITEM);
        ENTITY_LOOKUP = registryLookup.join().lookupOrThrow(Registries.ENTITY_TYPE);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {

        final Consumer<AdvancementHolder> rareEquipmentConsumer
                = withConditions(consumer, new RareEquipmentRecipesEnabledResourceCondition());
        // Story
        consumer.accept(SMELT_KIMBERLITE);
        // Husbandry
        consumer.accept(EAT_FAKE_ANIMAL_PRODUCT);
        consumer.accept(FED_WOLF_PEANUT);
        consumer.accept(LUCKY_TICKET);
        consumer.accept(EAT_ALL_FOOD_BOWLS);
        consumer.accept(MINE_FAKE_BLOCK);
        consumer.accept(CRAFT_DIAMONDS_FROM_GRAPHENE);
        // Adventure
        consumer.accept(LOCK_CONTAINER);
        // Nether
        consumer.accept(CONVERT_COW_TO_MOONGUS);
        // End
        consumer.accept(PLUCK_NEEDLES_FROM_PRICKLE_LOG);
        rareEquipmentConsumer.accept(CRAFT_AN_ELYTRA);
    }

    private static Advancement.Builder requireFoodBowlItemsEaten(Advancement.Builder builder) {
        List<Item> bowls = List.of(
                Items.BEETROOT_SOUP,
                Items.MUSHROOM_STEW,
                Items.RABBIT_STEW,
                Items.SUSPICIOUS_STEW,
                YavpmItems.SEA_SOUP,
                YavpmItems.CHICKEN_SOUP,
                YavpmItems.FANCY_MUSHROOM_STEW
        );
        for (Item item : bowls) {
            builder.addCriterion(BuiltInRegistries.ITEM.getKey(item).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(ITEM_LOOKUP, item));
        }

        return builder;
    }

    private static ResourceKey<Recipe<?>> makeRecipeKey(ResourceLocation id) {
        return ResourceKey.create(Registries.RECIPE, id);
    }
}
