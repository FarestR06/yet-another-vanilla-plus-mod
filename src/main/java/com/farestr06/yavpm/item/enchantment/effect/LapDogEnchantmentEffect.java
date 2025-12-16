package com.farestr06.yavpm.item.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record LapDogEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LapDogEnchantmentEffect> CODEC = MapCodec.unit(LapDogEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {
        if (user instanceof Wolf wolf) {
            if (wolf.getTarget() == null && wolf.tickCount % getHealRate(level) == 0) {
                wolf.heal(level * 2);
                if (wolf.getOwner() != null) {
                    wolf.getOwner().heal(level * 1.5f);
                }
            }
        }
    }

    private int getHealRate(int level) {
        return 90 / level;
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
