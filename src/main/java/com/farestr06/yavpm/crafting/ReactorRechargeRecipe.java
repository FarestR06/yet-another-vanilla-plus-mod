package com.farestr06.yavpm.crafting;

import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class ReactorRechargeRecipe extends SpecialCraftingRecipe {
    public ReactorRechargeRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        boolean isReactor = false;
        boolean isRecharger = false;
        for (ItemStack stack : input.getStacks()) {
            if (stack.isOf(YavpmItems.REACTOR)) {
                isReactor = true;
                continue;
            }
            if (stack.isIn(YavpmTags.Items.REACTOR_RECHARGERS)) {
                isRecharger = true;
            }
        }
        return isReactor && isRecharger;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack reactor = ItemStack.EMPTY;
        boolean isRecharger = false;
        for (ItemStack stack : input.getStacks()) {
            if (stack.isOf(YavpmItems.REACTOR)) {
                reactor = stack.withItem(YavpmItems.HEATED_REACTOR);
                continue;
            }
            if (stack.isIn(YavpmTags.Items.REACTOR_RECHARGERS)) {
                isRecharger = true;
            }
        }
        if (isRecharger) {
            return reactor;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return YavpmRecipeSerializers.REACTOR_RECHARGE;
    }
}
