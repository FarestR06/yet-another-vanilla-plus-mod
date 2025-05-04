package com.farestr06.yavpm.misc.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class FakeBlockDestroyedCriterion extends AbstractCriterion<FakeBlockDestroyedCriterion.Conditions> {

    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, BlockState state, ItemStack stack) {
        this.trigger(player, conditions -> conditions.test(state, stack));
    }

    public record Conditions(
            Optional<LootContextPredicate> player, Optional<RegistryEntry<Block>> block, Optional<ItemPredicate> item
    ) implements AbstractCriterion.Conditions {
        public static final Codec<FakeBlockDestroyedCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(FakeBlockDestroyedCriterion.Conditions::player),
                                Registries.BLOCK.getEntryCodec().optionalFieldOf("block").forGetter(FakeBlockDestroyedCriterion.Conditions::block),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(FakeBlockDestroyedCriterion.Conditions::item)
                        )
                        .apply(instance, FakeBlockDestroyedCriterion.Conditions::new)
        );

        @SuppressWarnings("deprecation")
        public static AdvancementCriterion<FakeBlockDestroyedCriterion.Conditions> create(
                Block block, ItemPredicate.Builder itemPredicateBuilder
        ) {
            return YavpmCriteria.FAKE_BLOCK_DESTROYED
                    .create(
                            new FakeBlockDestroyedCriterion.Conditions(Optional.empty(), Optional.of(block.getRegistryEntry()), Optional.of(itemPredicateBuilder.build()))
                    );
        }

        public boolean test(BlockState state, ItemStack stack) {
            if (this.block.isPresent() && !state.isOf(this.block.get())) {
                return false;
            } else {
                return this.item.isPresent() && !this.item.get().test(stack);
            }
        }
    }
}
