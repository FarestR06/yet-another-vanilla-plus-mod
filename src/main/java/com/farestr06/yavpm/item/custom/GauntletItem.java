package com.farestr06.yavpm.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GauntletItem extends Item {
    public GauntletItem(Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                3.5f, EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                ).build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0f, 2);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            if (isAttackCriticalOrKnockback(player)) {
                int damage = player.getRandom().nextBetween(2, 16);
                stack.damage(damage, player, EquipmentSlot.MAINHAND);
                return;
            }
        }
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    private boolean isAttackCriticalOrKnockback(PlayerEntity attacker) {
        boolean critical = attacker.fallDistance > 0.0f
                && !attacker.isOnGround()
                && !attacker.isClimbing()
                && !attacker.isTouchingWater()
                && !attacker.hasStatusEffect(StatusEffects.BLINDNESS)
                && !attacker.hasVehicle() && !attacker.isSprinting();
        return critical || attacker.isSprinting();
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
