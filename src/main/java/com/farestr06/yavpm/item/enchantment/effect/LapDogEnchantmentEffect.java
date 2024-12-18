package com.farestr06.yavpm.item.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record LapDogEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LapDogEnchantmentEffect> CODEC = MapCodec.unit(LapDogEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof WolfEntity wolf) {
            if (wolf.getTarget() == null && wolf.age % getHealRate(level) == 0) {
                wolf.heal(level * 2);
                if (wolf.getOwner() != null) {
                    wolf.getOwner().heal(level * 1.5f);
                }
            }
        }
    }

    private int getHealRate(int level) {
        return Math.round(75f / level);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
