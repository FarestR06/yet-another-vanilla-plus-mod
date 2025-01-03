package com.farestr06.yavpm.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
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
                                3.5, EntityAttributeModifier.Operation.ADD_VALUE
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
        int damage = 1;
        if (attacker.isSprinting()) {
            damage = attacker.getRandom().nextBetween(1, 4);
        } else if (isAttackCritical(attacker)) {
            damage = attacker.getRandom().nextBetween(4, 16);
        }
        stack.damage(damage, attacker, EquipmentSlot.MAINHAND);
    }

    private boolean isAttackCritical(LivingEntity attacker) {
        return attacker.fallDistance > 0.0f
                && !attacker.isOnGround()
                && !attacker.isClimbing()
                && !attacker.isTouchingWater()
                && !attacker.hasStatusEffect(StatusEffects.BLINDNESS)
                && !attacker.hasVehicle() && !attacker.isSprinting();
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
