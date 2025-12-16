package com.farestr06.yavpm.misc.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class FakeBlockDestroyedCriterion extends SimpleCriterionTrigger<FakeBlockDestroyedCriterion.Conditions> {

    @Override
    public Codec<com.farestr06.yavpm.misc.criterion.FakeBlockDestroyedCriterion.Conditions> codec() {
        return com.farestr06.yavpm.misc.criterion.FakeBlockDestroyedCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayer player, BlockState state, ItemStack stack) {
        this.trigger(player, conditions -> conditions.test(state, stack));
    }

    public record Conditions(
            Optional<ContextAwarePredicate> player, Optional<Holder<Block>> block, Optional<ItemPredicate> item
    ) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<FakeBlockDestroyedCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(FakeBlockDestroyedCriterion.Conditions::player),
                                BuiltInRegistries.BLOCK.holderByNameCodec().optionalFieldOf("block").forGetter(FakeBlockDestroyedCriterion.Conditions::block),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(FakeBlockDestroyedCriterion.Conditions::item)
                        )
                        .apply(instance, FakeBlockDestroyedCriterion.Conditions::new)
        );

        @SuppressWarnings("deprecation")
        public static Criterion<FakeBlockDestroyedCriterion.Conditions> create(
                Block block, ItemPredicate.Builder itemPredicateBuilder
        ) {
            return YavpmCriteria.FAKE_BLOCK_DESTROYED
                    .createCriterion(
                            new FakeBlockDestroyedCriterion.Conditions(Optional.empty(), Optional.of(block.builtInRegistryHolder()), Optional.of(itemPredicateBuilder.build()))
                    );
        }

        public boolean test(BlockState state, ItemStack stack) {
            if (this.block.isPresent() && !state.is(this.block.get())) {
                return false;
            } else {
                return this.item.isPresent() && !this.item.get().test(stack);
            }
        }
    }
}
