package com.farestr06.yavpm.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FortuneCookieItem extends Item {
    public FortuneCookieItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity playerEntity) {
            playerEntity.giveItemStack(LuckySlipItem.forEnchantment(LuckySlipItem.choose(world)));
        }
        return super.finishUsing(stack, world, user);
    }
}
