package com.farestr06.yavpm.mixin.entity;

import com.farestr06.yavpm.entity.mob.YavpmMobs;
import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntityMixin extends AnimalEntity {
    private ChickenEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/ChickenEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;"))
    private ItemEntity injected(ChickenEntity instance, ItemConvertible itemConvertible) {
        if (instance.getType() == YavpmMobs.CARBONFOWL) return instance.dropItem(YavpmItems.GRAPHITE);
        else return instance.dropItem(Items.EGG);
    }
}
