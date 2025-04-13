package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.mob.MoongusEntity;
import com.farestr06.yavpm.item.YavpmItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.conversion.EntityConversionContext;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
        if (thiz.getType() == EntityType.COW) { // The entity is clearly a cow, but we'll check just in case.
            if (stack.isOf(YavpmItems.CRIMSON_SPORE)) { // If we feed the cow a Crimson Wart Block...
                thiz.convertTo( // We'll turn it into a Crimson Moongus!
                        YavpmEntities.MOONGUS,
                        EntityConversionContext.create(thiz, false, false),
                        convertedEntity -> convertedEntity.setVariant(MoongusEntity.Type.CRIMSON)
                );
                cir.setReturnValue(ActionResult.SUCCESS);
            } else if (stack.isOf(YavpmItems.WARPED_SPORE)) { // Likewise, if we feed it a Warped Wart Block...
                thiz.convertTo( // It'll become a Warped Moongus!
                        YavpmEntities.MOONGUS,
                        EntityConversionContext.create(thiz, false, false),
                        convertedEntity -> convertedEntity.setVariant(MoongusEntity.Type.WARPED)
                );
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
