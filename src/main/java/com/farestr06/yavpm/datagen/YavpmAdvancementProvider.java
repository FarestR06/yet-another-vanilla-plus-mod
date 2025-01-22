package com.farestr06.yavpm.datagen;

import com.farestr06.api.util.VanillaAdvancements;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.item.YavpmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmAdvancementProvider extends FabricAdvancementProvider {
    private static RegistryWrapper.Impl<Block> BLOCK_LOOKUP;
    private static RegistryWrapper.Impl<Item> ITEM_LOOKUP;
    private static RegistryWrapper.Impl<EntityType<?>> ENTITY_LOOKUP;

    // region Story
    protected static final AdvancementEntry SMELT_KIMBERLITE = Advancement.Builder.create()
            .parent(VanillaAdvancements.Story.MINE_STONE)
            .display(
                    YavpmItems.RAW_DIAMOND,
                    Text.translatable("advancements.story.smelt_kimberlite.title"),
                    Text.translatable("advancements.story.smelt_kimberlite.description"),
                    null,
                    AdvancementFrame.TASK,
                    true,
                    true,
                    true
            )
            .criteriaMerger(AdvancementRequirements.CriterionMerger.OR)
            .criterion("craft_diamonds_via_smelting", RecipeCraftedCriterion.Conditions.create(
                    makeRecipeKey(makeId("diamond_from_smelting_raw_diamond"))
            ))
            .criterion("craft_diamonds_via_blasting", RecipeCraftedCriterion.Conditions.create(
                   makeRecipeKey( makeId("diamond_from_blasting_raw_diamond"))
            ))
            .build(makeId("story/smelt_kimberlite"));
    // endregion
    // region Husbandry
    protected static final AdvancementEntry EAT_FAKE_ANIMAL_PRODUCT = Advancement.Builder.create()
            .parent(VanillaAdvancements.Husbandry.PLANT_SEED)
            .display(
                    YavpmItems.COOKED_FAKE_BEEF,
                    Text.translatable("advancements.husbandry.eat_fake_animal_product.title"),
                    Text.translatable("advancements.husbandry.eat_fake_animal_product.description"),
                    null,
                    AdvancementFrame.TASK,
                    true,
                    true,
                    false
            ).criterion("ate_fake_animal_product", ConsumeItemCriterion.Conditions.predicate(
                    ItemPredicate.Builder.create().items(
                            ITEM_LOOKUP,
                            YavpmItems.FAKE_BEEF,
                            YavpmItems.COOKED_FAKE_BEEF,
                            YavpmItems.FAKE_MILK_BUCKET,
                            YavpmItems.TOFU
                    )
            )).build(makeId("husbandry/eat_fake_animal_product"));

    protected static final AdvancementEntry FED_WOLF_PEANUT = Advancement.Builder.create()
            .parent(VanillaAdvancements.Husbandry.TAME_AN_ANIMAL)
            .display(
                    YavpmItems.COOKED_PEANUT,
                    Text.translatable("advancements.husbandry.fed_wolf_peanut.title"),
                    Text.translatable("advancements.husbandry.fed_wolf_peanut.description"),
                    null,
                    AdvancementFrame.TASK,
                    true,
                    true,
                    true
            ).criterion("fed_wolf_peanut", PlayerInteractedWithEntityCriterion.Conditions.create(
                    ItemPredicate.Builder.create().items(ITEM_LOOKUP, YavpmItems.COOKED_PEANUT),
                    Optional.of(EntityPredicate.contextPredicateFromEntityPredicate(
                            EntityPredicate.Builder.create().type(EntityTypePredicate.create(ENTITY_LOOKUP, EntityType.WOLF))))
            )).build(makeId("husbandry/fed_wolf_peanut"));

    protected static final AdvancementEntry LUCKY_TICKET = Advancement.Builder.create()
            .parent(VanillaAdvancements.Husbandry.PLANT_SEED)
            .display(
                    YavpmItems.FORTUNE_COOKIE,
                    Text.translatable("advancements.husbandry.lucky_ticket.title"),
                    Text.translatable("advancements.husbandry.lucky_ticket.description"),
                    null,
                    AdvancementFrame.TASK,
                    true,
                    true,
                    false
            ).criterion("ate_fortune_cookie", ConsumeItemCriterion.Conditions.predicate(
                    ItemPredicate.Builder.create().items(
                            ITEM_LOOKUP,
                            YavpmItems.FORTUNE_COOKIE
                    )
            )).build(makeId("husbandry/lucky_ticket"));

    protected static final AdvancementEntry EAT_ALL_FOOD_BOWLS =
            requireFoodBowlItemsEaten(Advancement.Builder.create())
                    .parent(VanillaAdvancements.Husbandry.PLANT_SEED)
                    .display(
                            Items.SUSPICIOUS_STEW,
                            Text.translatable("advancements.husbandry.eat_all_food_bowls.title"),
                            Text.translatable("advancements.husbandry.eat_all_food_bowls.description"),
                            null,
                            AdvancementFrame.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .rewards(AdvancementRewards.Builder.experience(50))
                    .build(makeId("husbandry/eat_all_food_bowls"));
    protected static final AdvancementEntry CRAFT_DIAMONDS_FROM_GRAPHENE = Advancement.Builder.create()
            .parent(VanillaAdvancements.Husbandry.BREED_AN_ANIMAL)
            .display(
                    YavpmBlocks.GRAPHENE_BLOCK,
                    Text.translatable("advancements.husbandry.craft_diamonds_from_graphene.title"),
                    Text.translatable("advancements.husbandry.craft_diamonds_from_graphene.description"),
                    null,
                    AdvancementFrame.CHALLENGE,
                    true,
                    true,
                    false
            )
            .criteriaMerger(AdvancementRequirements.CriterionMerger.OR)
            .criterion("craft_diamonds_via_smelting", RecipeCraftedCriterion.Conditions.create(
                    makeRecipeKey(makeId("diamond_from_smelting_graphene_block"))
            ))
            .criterion("craft_diamonds_via_blasting", RecipeCraftedCriterion.Conditions.create(
                    makeRecipeKey(makeId("diamond_from_blasting_graphene_block"))
            ))
            .rewards(AdvancementRewards.Builder.experience(100))
            .build(makeId("husbandry/craft_diamonds_from_graphene"));
    // endregion
    // region Adventure
    protected static final AdvancementEntry LOCK_CONTAINER = Advancement.Builder.create()
            .parent(VanillaAdvancements.Adventure.SUMMON_IRON_GOLEM)
            .display(
                    YavpmItems.BABY_KEY,
                    Text.translatable("advancements.adventure.lock_container.title"),
                    Text.translatable("advancements.adventure.lock_container.description"),
                    null,
                    AdvancementFrame.TASK,
                    true,
                    true,
                    false
            ).criterion(
                    "lock",
                    ItemCriterion.Conditions.createItemUsedOnBlock(
                            LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(
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
                            ItemPredicate.Builder.create().items(ITEM_LOOKUP, YavpmItems.BABY_KEY)
                    ))
            .build(makeId("adventure/lock_container"));
    protected static final AdvancementEntry UPGRADE_TOOL_WITH_RUNE = Advancement.Builder.create()
            .parent(VanillaAdvancements.Adventure.TRADE)
            .display(
                    YavpmItems.RUNE_DURABILITY,
                    Text.translatable("advancements.adventure.upgrade_tool_with_rune.title"),
                    Text.translatable("advancements.adventure.upgrade_tool_with_rune.description"),
                    null,
                    AdvancementFrame.GOAL,
                    true,
                    true,
                    true
            ).criterion("upgrade_tool", RecipeCraftedCriterion.Conditions.create(
                    makeRecipeKey(makeId("rune_upgrade"))
            ))
            .build(makeId("adventure/upgrade_tool_with_rune"));
    // endregion
    // region Nether
    protected static final AdvancementEntry CONVERT_COW_TO_MOONGUS = Advancement.Builder.create()
            .parent(VanillaAdvancements.Nether.BREW_POTION)
            .display(
                    Items.NETHER_WART_BLOCK,
                    Text.translatable("advancements.nether.convert_cow_to_moongus.title"),
                    Text.translatable("advancements.nether.convert_cow_to_moongus.description"),
                    null,
                    AdvancementFrame.GOAL,
                    true,
                    true,
                    false
            ).criterion("fed_cow_wart", PlayerInteractedWithEntityCriterion.Conditions.create(
                    ItemPredicate.Builder.create().items(ITEM_LOOKUP, Items.NETHER_WART_BLOCK, Items.WARPED_WART_BLOCK),
                    Optional.of(EntityPredicate.contextPredicateFromEntityPredicate(
                            EntityPredicate.Builder.create().type(EntityTypePredicate.create(ENTITY_LOOKUP, EntityType.COW))))
            )).build(makeId("nether/convert_cow_to_moongus"));
    // endregion
    // region End
    protected static final AdvancementEntry PLUCK_NEEDLES_FROM_PRICKLE_LOG = Advancement.Builder.create()
            .parent(VanillaAdvancements.End.ENTER_END_GATEWAY)
            .display(
                    Items.SHEARS,
                    Text.translatable("advancements.end.pluck_needles_from_prickle_log.title"),
                    Text.translatable("advancements.end.pluck_needles_from_prickle_log.description"),
                    null,
                    AdvancementFrame.TASK,
                    true,
                    true,
                    false
            ).criterion("pluck", InventoryChangedCriterion.Conditions.items(YavpmBlocks.PRICKLE_SHOOT))
            .build(makeId("end/pluck_needles_from_prickle_log"));

    protected static final AdvancementEntry CRAFT_AN_ELYTRA = Advancement.Builder.create()
            .parent(VanillaAdvancements.End.FIND_ELYTRA)
            .display(
                    YavpmItems.PHANTOM_CHORD,
                    Text.translatable("advancements.end.craft_an_elytra.title"),
                    Text.translatable("advancements.end.craft_an_elytra.description"),
                    null,
                    AdvancementFrame.CHALLENGE,
                    true,
                    true,
                    true
            ).criterion("craft_elytra", RecipeCraftedCriterion.Conditions.create(
                    makeRecipeKey(makeId("elytra"))
            ))
            .rewards(AdvancementRewards.Builder.experience(75))
            .build(makeId("end/craft_an_elytra"));
    // endregion

    protected YavpmAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
        BLOCK_LOOKUP = registryLookup.join().getOrThrow(RegistryKeys.BLOCK);
        ITEM_LOOKUP = registryLookup.join().getOrThrow(RegistryKeys.ITEM);
        ENTITY_LOOKUP = registryLookup.join().getOrThrow(RegistryKeys.ENTITY_TYPE);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        // Story
        consumer.accept(SMELT_KIMBERLITE);
        // Husbandry
        consumer.accept(EAT_FAKE_ANIMAL_PRODUCT);
        consumer.accept(FED_WOLF_PEANUT);
        consumer.accept(LUCKY_TICKET);
        consumer.accept(EAT_ALL_FOOD_BOWLS);
        consumer.accept(CRAFT_DIAMONDS_FROM_GRAPHENE);
        // Adventure
        consumer.accept(LOCK_CONTAINER);
        consumer.accept(UPGRADE_TOOL_WITH_RUNE);
        // Nether
        consumer.accept(CONVERT_COW_TO_MOONGUS);
        // End
        consumer.accept(PLUCK_NEEDLES_FROM_PRICKLE_LOG);
        consumer.accept(CRAFT_AN_ELYTRA);
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
            builder.criterion(Registries.ITEM.getId(item).getPath(), ConsumeItemCriterion.Conditions.item(ITEM_LOOKUP, item));
        }

        return builder;
    }

    private static RegistryKey<Recipe<?>> makeRecipeKey(Identifier id) {
        return RegistryKey.of(RegistryKeys.RECIPE, id);
    }
}
