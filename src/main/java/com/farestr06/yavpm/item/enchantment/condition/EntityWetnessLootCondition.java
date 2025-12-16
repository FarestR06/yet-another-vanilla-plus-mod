package com.farestr06.yavpm.item.enchantment.condition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record EntityWetnessLootCondition(LootContext.EntityTarget entity) implements LootItemCondition {
    public static final MapCodec<EntityWetnessLootCondition> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            LootContext.EntityTarget.CODEC.fieldOf("entity").forGetter(EntityWetnessLootCondition::entity)
                    )
                    .apply(instance, EntityWetnessLootCondition::new)
    );

    @Override
    public LootItemConditionType getType() {
        return YavpmLootConditions.ENTITY_WETNESS;
    }

    @Override
    public boolean test(LootContext lootContext) {
        Entity entity = lootContext.getOptionalParameter(this.entity.getParam());
        if (entity instanceof LivingEntity livingEntity) {
            return livingEntity.isInWaterOrRain();
        }
        return false;
    }

    public static LootItemCondition.Builder builder(LootContext.EntityTarget entity) {
        return () -> new EntityWetnessLootCondition(entity);
    }
}
