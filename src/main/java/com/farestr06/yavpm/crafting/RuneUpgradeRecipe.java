package com.farestr06.yavpm.crafting;

import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RuneUpgradeRecipe extends SpecialCraftingRecipe {
    public RuneUpgradeRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        ItemStack tool = ItemStack.EMPTY;
        ItemStack rune = ItemStack.EMPTY;
        for (ItemStack stack : input.getStacks()) {
            if (stack.isIn(ConventionalItemTags.TOOLS)) {
                tool = stack;
                continue;
            }
            if (stack.isIn(YavpmTags.Items.RUNES)) {
                rune = stack;
            }
        }
        return rune.isOf(YavpmItems.RUNE_ATTACK) && tool.isIn(YavpmTags.Items.RUNE_ATTACK_APPLICABLE)
        || rune.isOf(YavpmItems.RUNE_DURABILITY) && tool.isIn(YavpmTags.Items.RUNE_DURABILITY_APPLICABLE)
        || rune.isOf(YavpmItems.RUNE_SPEED) && tool.isIn(YavpmTags.Items.RUNE_SPEED_APPLICABLE)
        || rune.isOf(YavpmItems.RUNE_TOUGHNESS) && tool.isIn(YavpmTags.Items.RUNE_TOUGHNESS_APPLICABLE);
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack tool = ItemStack.EMPTY;
        ItemStack rune = ItemStack.EMPTY;
        for (ItemStack stack : input.getStacks()) {
            if (stack.isIn(ConventionalItemTags.TOOLS)) {
                tool = stack;
                continue;
            }
            if (stack.isIn(YavpmTags.Items.RUNES)) {
                rune = stack;
            }
        }

        if (rune.isOf(YavpmItems.RUNE_ATTACK) && tool.isIn(YavpmTags.Items.RUNE_ATTACK_APPLICABLE)) {
            return attackRuneCalculate(tool);
        }
        if (rune.isOf(YavpmItems.RUNE_DURABILITY) && tool.isIn(YavpmTags.Items.RUNE_DURABILITY_APPLICABLE)) {
            return durabilityRuneCalculate(tool);
        }
        if (rune.isOf(YavpmItems.RUNE_SPEED) && tool.isIn(YavpmTags.Items.RUNE_SPEED_APPLICABLE)) {
            return speedRuneCalculate(tool);
        }
        if (rune.isOf(YavpmItems.RUNE_TOUGHNESS) && tool.isIn(YavpmTags.Items.RUNE_TOUGHNESS_APPLICABLE)) {
            return speedRuneCalculate(tool);
        }
        return ItemStack.EMPTY;
    }

    private ItemStack speedRuneCalculate(ItemStack tool) {
        ComponentMap.Builder map = ComponentMap.builder();
        if (tool.getItem() instanceof ToolItem toolItem) {
            ToolMaterial material = toolItem.getMaterial();
            float originalSpeed = material.getMiningSpeedMultiplier();
            float speed = Math.max(originalSpeed, originalSpeed * 1.5f);
            if (toolItem instanceof SwordItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        SwordItem.createAttributeModifiers(
                                material,
                                MathHelper.ceil(material.getAttackDamage()),
                                speed
                        )
                );
            }
            if (toolItem instanceof ShovelItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        ShovelItem.createAttributeModifiers(
                                material,
                                MathHelper.ceil(material.getAttackDamage()),
                                speed
                        )
                );
            }
            if (toolItem instanceof PickaxeItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        PickaxeItem.createAttributeModifiers(
                                material,
                                MathHelper.ceil(material.getAttackDamage()),
                                speed
                        )
                );
            }
            if (toolItem instanceof AxeItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        AxeItem.createAttributeModifiers(
                                material,
                                MathHelper.ceil(material.getAttackDamage()),
                                speed
                        )
                );
            }
            if (toolItem instanceof HoeItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        HoeItem.createAttributeModifiers(
                                material,
                                MathHelper.ceil(material.getAttackDamage()),
                                speed
                        )
                );
            }
            tool.applyComponentsFrom(map.build());
            return tool;
        }
        return ItemStack.EMPTY;
    }

    private ItemStack durabilityRuneCalculate(ItemStack tool) {
        ComponentMap.Builder map = ComponentMap.builder();
        int durability = MathHelper.ceil(tool.getMaxDamage() * 1.5f);
        map.add(DataComponentTypes.MAX_DAMAGE, durability);
        map.add(DataComponentTypes.DAMAGE, 0);
        tool.applyComponentsFrom(map.build());
        return tool;
    }

    private static ItemStack attackRuneCalculate(ItemStack tool) {
        ComponentMap.Builder map = ComponentMap.builder();
        if (tool.getItem() instanceof SwordItem sword) {
            ToolMaterial material = sword.getMaterial();
            int damage = MathHelper.ceil(material.getAttackDamage() * 1.5f);
            float speed = material.getMiningSpeedMultiplier();
            map.add(DataComponentTypes.ATTRIBUTE_MODIFIERS, SwordItem.createAttributeModifiers(material, damage, speed));
            tool.applyComponentsFrom(map.build());
            return tool;
        }
        if (tool.getItem() instanceof AxeItem axe) {
            ToolMaterial material = axe.getMaterial();
            int damage = MathHelper.ceil(material.getAttackDamage() * 1.5f);
            float originalSpeed = material.getMiningSpeedMultiplier();
            float speed = Math.max(originalSpeed, originalSpeed * 1.5f);
            map.add(DataComponentTypes.ATTRIBUTE_MODIFIERS, SwordItem.createAttributeModifiers(material, damage, speed));
            tool.applyComponentsFrom(map.build());
            return tool;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}
