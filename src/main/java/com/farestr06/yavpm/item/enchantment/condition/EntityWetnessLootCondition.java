package com.farestr06.yavpm.item.enchantment.condition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;

public record EntityWetnessLootCondition(LootContext.EntityTarget entity) implements LootCondition {
    public static final MapCodec<EntityWetnessLootCondition> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            LootContext.EntityTarget.CODEC.fieldOf("entity").forGetter(EntityWetnessLootCondition::entity)
                    )
                    .apply(instance, EntityWetnessLootCondition::new)
    );

    @Override
    public LootConditionType getType() {
        return YavpmLootConditions.ENTITY_WETNESS;
    }

    @Override
    public boolean test(LootContext lootContext) {
        Entity entity = lootContext.get(this.entity.getParameter());
        if (entity instanceof LivingEntity livingEntity) {
            return livingEntity.isTouchingWaterOrRain();
        }
        return false;
    }

    public static LootCondition.Builder builder(LootContext.EntityTarget entity) {
        return () -> new EntityWetnessLootCondition(entity);
    }
}
