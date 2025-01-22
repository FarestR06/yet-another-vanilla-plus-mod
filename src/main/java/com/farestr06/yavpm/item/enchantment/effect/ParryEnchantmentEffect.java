package com.farestr06.yavpm.item.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.entity.DamageEntityEnchantmentEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public record ParryEnchantmentEffect(EnchantmentLevelBasedValue minDamage, EnchantmentLevelBasedValue maxDamage, RegistryEntry<DamageType> damageType) implements EnchantmentEntityEffect {
    public static final MapCodec<ParryEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            EnchantmentLevelBasedValue.CODEC.fieldOf("min_damage").forGetter(ParryEnchantmentEffect::minDamage),
                            EnchantmentLevelBasedValue.CODEC.fieldOf("max_damage").forGetter(ParryEnchantmentEffect::maxDamage),
                            DamageType.ENTRY_CODEC.fieldOf("damage_type").forGetter(ParryEnchantmentEffect::damageType)
                    )
                    .apply(instance, ParryEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (context.owner() != null) {
            LivingEntity owner = context.owner();
            if (owner.isBlocking()) {
                float f = MathHelper.nextBetween(user.getRandom(), this.minDamage.getValue(level), this.maxDamage.getValue(level));
                user.clientDamage(new DamageSource(this.damageType, context.owner()), f);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
