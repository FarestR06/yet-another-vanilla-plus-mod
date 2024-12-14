package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CowEntity.class)
public abstract class CowEntityMixin extends AnimalEntity {
    private CowEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    private final CowEntity thiz = (CowEntity) (Object) this;

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"), cancellable = true)
    private void injected(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, @Local ItemStack stack) {
        if (stack.isOf(Items.NETHER_WART_BLOCK)) {
            thiz.convertTo(YavpmEntities.MOONGUS, false).setVariant(MooshroomEntity.Type.RED);
            cir.setReturnValue(ActionResult.success(thiz.getWorld().isClient));
        } else if (stack.isOf(Items.WARPED_WART_BLOCK)) {
            thiz.convertTo(YavpmEntities.MOONGUS, false).setVariant(MooshroomEntity.Type.BROWN);
            cir.setReturnValue(ActionResult.success(thiz.getWorld().isClient));
        }
    }
}
