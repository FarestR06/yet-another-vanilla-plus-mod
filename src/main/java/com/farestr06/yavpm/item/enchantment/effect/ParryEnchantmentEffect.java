package com.farestr06.yavpm.item.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ParryEnchantmentEffect(LevelBasedValue minDamage, LevelBasedValue maxDamage, Holder<DamageType> damageType) implements EnchantmentEntityEffect {
    public static final MapCodec<ParryEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            LevelBasedValue.CODEC.fieldOf("min_damage").forGetter(ParryEnchantmentEffect::minDamage),
                            LevelBasedValue.CODEC.fieldOf("max_damage").forGetter(ParryEnchantmentEffect::maxDamage),
                            DamageType.CODEC.fieldOf("damage_type").forGetter(ParryEnchantmentEffect::damageType)
                    )
                    .apply(instance, ParryEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {
        if (context.owner() != null) {
            LivingEntity owner = context.owner();
            if (owner.isBlocking()) {
                float f = Mth.randomBetween(user.getRandom(), this.minDamage.calculate(level), this.maxDamage.calculate(level));
                user.hurtServer(world, new DamageSource(this.damageType, context.owner()), f);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
