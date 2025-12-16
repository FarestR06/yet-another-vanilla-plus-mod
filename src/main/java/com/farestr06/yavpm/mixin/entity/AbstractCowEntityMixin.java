package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.mob.FungusCowEntity;
import com.farestr06.yavpm.item.YavpmItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractCow;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractCow.class)
public abstract class AbstractCowEntityMixin extends Animal {
    private AbstractCowEntityMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Unique
    private final AbstractCow self = (AbstractCow) (Object) this;

    @Inject(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"), cancellable = true)
    private void injected(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir, @Local ItemStack stack) {
        if (self.getType() == EntityType.COW) { // The entity is clearly a cow, but we'll check just in case.
            if (stack.is(YavpmItems.CRIMSON_SPORE)) { // If we feed the cow a Crimson Wart Block...
                self.convertTo( // We'll turn it into a Crimson Moongus!
                        YavpmEntities.MOONGUS,
                        ConversionParams.single(self, false, false),
                        convertedEntity -> convertedEntity.setMoongusVariant(FungusCowEntity.Variant.CRIMSON)
                );
                cir.setReturnValue(InteractionResult.SUCCESS);
            } else if (stack.is(YavpmItems.WARPED_SPORE)) { // Likewise, if we feed it a Warped Wart Block...
                self.convertTo( // It'll become a Warped Moongus!
                        YavpmEntities.MOONGUS,
                        ConversionParams.single(self, false, false),
                        convertedEntity -> convertedEntity.setMoongusVariant(FungusCowEntity.Variant.WARPED)
                );
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}
