package com.farestr06.yavpm.item.custom;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;

import java.util.List;

public class GauntletItem extends Item {
    public GauntletItem(net.minecraft.world.item.Item.Properties settings) {
        super(settings);
    }

    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                5, AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                ).build();
    }

    public static Tool createToolComponent() {
        return new Tool(List.of(), 1.0f, 2, false);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int damage = 1;
        if (attacker.isSprinting()) {
            damage = attacker.getRandom().nextIntBetweenInclusive(1, 4);
        } else if (isAttackCritical(attacker)) {
            damage = attacker.getRandom().nextIntBetweenInclusive(4, 16);
        }
        stack.hurtAndBreak(damage, attacker, EquipmentSlot.MAINHAND);
    }

    private boolean isAttackCritical(LivingEntity attacker) {
        return attacker.fallDistance > 0.0f
                && !attacker.onGround()
                && !attacker.onClimbable()
                && !attacker.isInWater()
                && !attacker.hasEffect(MobEffects.BLINDNESS)
                && !attacker.isPassenger() && !attacker.isSprinting();
    }
}
