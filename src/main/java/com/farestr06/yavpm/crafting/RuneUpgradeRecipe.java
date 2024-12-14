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
import net.minecraft.util.Rarity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

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
        || rune.isOf(YavpmItems.RUNE_SPEED) && tool.isIn(YavpmTags.Items.RUNE_SPEED_APPLICABLE);
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
        return ItemStack.EMPTY;
    }

    private ItemStack speedRuneCalculate(ItemStack tool) {
        ComponentMap.Builder map = ComponentMap.builder();
        ItemStack newTool = tool.copy();
        newTool.set(DataComponentTypes.RARITY, Rarity.UNCOMMON);
        if (tool.getItem() instanceof ToolItem toolItem) {
            ToolMaterial material = toolItem.getMaterial();
            float originalSpeed = material.getMiningSpeedMultiplier();
            float speed = Math.max(originalSpeed, originalSpeed * HANDLER.instance().runeSpeedUpgradeFactor);
            if (toolItem instanceof SwordItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        SwordItem.createAttributeModifiers(
                                material,
                                3,
                                speed
                        )
                );
            }
            if (toolItem instanceof ShovelItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        ShovelItem.createAttributeModifiers(
                                material,
                                1.5f,
                                speed
                        )
                );
            }
            if (toolItem instanceof PickaxeItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        PickaxeItem.createAttributeModifiers(
                                material,
                                1f,
                                speed
                        )
                );
            }
            if (toolItem instanceof AxeItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        AxeItem.createAttributeModifiers(
                                material,
                                6f,
                                speed
                        )
                );
            }
            if (toolItem instanceof HoeItem) {
                map.add(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        HoeItem.createAttributeModifiers(
                                material,
                                0f,
                                speed
                        )
                );
            }
            newTool.applyComponentsFrom(map.build());
            return newTool;
        }
        return ItemStack.EMPTY;
    }

    private ItemStack durabilityRuneCalculate(ItemStack tool) {
        ComponentMap.Builder map = ComponentMap.builder();
        ItemStack newTool = tool.copy();
        newTool.set(DataComponentTypes.RARITY, Rarity.UNCOMMON);
        int durability = MathHelper.ceil(tool.getMaxDamage() * HANDLER.instance().runeDurabilityUpgradeFactor);
        map.add(DataComponentTypes.MAX_DAMAGE, durability);
        map.add(DataComponentTypes.DAMAGE, 0);
        newTool.applyComponentsFrom(map.build());
        return newTool;
    }

    private static ItemStack attackRuneCalculate(ItemStack tool) {
        ComponentMap.Builder map = ComponentMap.builder();
        ItemStack newTool = tool.copy();
        newTool.set(DataComponentTypes.RARITY, Rarity.UNCOMMON);
        if (tool.getItem() instanceof SwordItem sword) {
            ToolMaterial material = sword.getMaterial();
            int damage = MathHelper.ceil(material.getAttackDamage() * HANDLER.instance().runeAttackUpgradeFactor);
            float speed = material.getMiningSpeedMultiplier();
            map.add(DataComponentTypes.ATTRIBUTE_MODIFIERS, SwordItem.createAttributeModifiers(material, damage, speed));
            newTool.applyComponentsFrom(map.build());
            return newTool;
        }
        if (tool.getItem() instanceof AxeItem axe) {
            ToolMaterial material = axe.getMaterial();
            int damage = MathHelper.ceil(material.getAttackDamage() * HANDLER.instance().runeAttackUpgradeFactor);
            float speed = material.getMiningSpeedMultiplier();
            map.add(DataComponentTypes.ATTRIBUTE_MODIFIERS, SwordItem.createAttributeModifiers(material, damage, speed));
            newTool.applyComponentsFrom(map.build());
            return newTool;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return YavpmRecipeSerializers.RUNE_UPGRADE;
    }
}
