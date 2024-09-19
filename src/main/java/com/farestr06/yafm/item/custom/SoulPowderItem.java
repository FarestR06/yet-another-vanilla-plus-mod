package com.farestr06.yafm.item.custom;

import com.farestr06.yafm.util.YavpmSounds;
import com.farestr06.yafm.util.YavpmTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SoulPowderItem extends Item {
    public SoulPowderItem(Settings settings) {
        super(settings);
    }

    private static final List<EntityType<? extends MobEntity>> ZOMBIE_TYPES = List.of(
            EntityType.ZOMBIE,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.HUSK,
            EntityType.DROWNED
    );
    private static final List<EntityType<? extends MobEntity>> SKELETON_TYPES = List.of(
            EntityType.SKELETON,
            EntityType.STRAY,
            EntityType.BOGGED,
            EntityType.WITHER_SKELETON
    );

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof SheepEntity sheep) {
            sheep.setColor(randomizeColor(sheep.getRandom()));
            return useSuccess(stack, user);
        }

        if (entity.getType().equals(EntityType.COW)) {
            ((CowEntity) entity).convertTo(EntityType.MOOSHROOM, false);
            return useSuccess(stack, user);
        }

        if (entity.getType().equals(EntityType.MOOSHROOM)) {
            ((MooshroomEntity) entity).convertTo(EntityType.COW, false);
            return useSuccess(stack, user);
        }

        if (entity instanceof AllayEntity allay) {
            allay.convertTo(EntityType.VEX, true);
            return useSuccess(stack, user);
        }
        if (entity instanceof VexEntity vex) {
            vex.convertTo(EntityType.ALLAY, true);
            return useSuccess(stack, user);
        }
        if (entity.getType().isIn(EntityTypeTags.ILLAGER)) {
            ((MobEntity) entity).convertTo(EntityType.VILLAGER, false);
            return useSuccess(stack, user);
        }
        if (entity.getType().isIn(YavpmTags.EntityTypes.HUMANOID_ZOMBIES)) {
            ((MobEntity) entity).convertTo(
                    ZOMBIE_TYPES.get(entity.getRandom().nextInt(ZOMBIE_TYPES.size())),
                    true
            );
        }
        if (entity.getType().isIn(YavpmTags.EntityTypes.HUMANOID_SKELETONS)) {
            ((MobEntity) entity).convertTo(
                    SKELETON_TYPES.get(entity.getRandom().nextInt(SKELETON_TYPES.size())),
                    true
            );
        }

        return ActionResult.PASS;
    }

    private static @NotNull ActionResult useSuccess(ItemStack stack, PlayerEntity user) {
        user.playSound(YavpmSounds.ITEM_SOUL_POWDER_USE);
        stack.decrementUnlessCreative(1, user);
        return ActionResult.SUCCESS;
    }

    private DyeColor randomizeColor(Random rand) {
        return DyeColor.byId(rand.nextBetween(0, 15));
    }
}
