package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SculkShriekerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

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
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = context.getStack();
        PlayerEntity player = context.getPlayer();

        if (player == null) return ActionResult.PASS;

        if (state.isOf(Blocks.SCULK_SHRIEKER)) {
            world.setBlockState(pos, state.with(SculkShriekerBlock.CAN_SUMMON, true));
            return useSuccess(stack, player);
        }

        if (state.isOf(Blocks.CAMPFIRE)) {
            world.setBlockState(pos, Blocks.SOUL_CAMPFIRE.getDefaultState());
            return useSuccess(stack, player);
        }

        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof SheepEntity sheep) {
            sheep.setColor(randomizeColor(sheep.getRandom()));
            return useSuccess(stack, user);
        }

        if (entity.getType().equals(EntityType.COW)) {
            Objects.requireNonNull(((CowEntity) entity).convertTo(EntityType.MOOSHROOM, false))
                    .setVariant(chooseMooshroomType(entity.getRandom()));
            return useSuccess(stack, user);
        }
        if (entity.getType().equals(EntityType.MOOSHROOM)) {
            ((MooshroomEntity) entity).convertTo(EntityType.COW, false);
            return useSuccess(stack, user);
        }
        if (entity instanceof PigEntity pig) {
            pig.convertTo(EntityType.PIGLIN, false);
            return useSuccess(stack, user);
        }
        if (entity instanceof PiglinEntity piglin) {
            piglin.convertTo(EntityType.PIG, false);
            return useSuccess(stack, user);
        }
        if (entity instanceof ZombifiedPiglinEntity zombiePiglin) {
            zombiePiglin.convertTo(EntityType.PIG, false);
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
            return useSuccess(stack, user);
        }
        if (entity.getType().isIn(YavpmTags.EntityTypes.HUMANOID_SKELETONS)) {
            ((MobEntity) entity).convertTo(
                    SKELETON_TYPES.get(entity.getRandom().nextInt(SKELETON_TYPES.size())),
                    true
            );
            return useSuccess(stack, user);
        }

        return ActionResult.PASS;
    }

    private MooshroomEntity.Type chooseMooshroomType(Random rand) {
        return (rand.nextFloat() < 0.9) ? MooshroomEntity.Type.RED : MooshroomEntity.Type.BROWN;
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
