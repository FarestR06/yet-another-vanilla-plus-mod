package com.farestr06.yavpm.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;

import java.util.List;

public class RuneItem extends Item {
    protected final Text tooltip;

    public RuneItem(Settings settings, Text tooltip) {
        super(settings);
        this.tooltip = tooltip;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(this.tooltip);
    }
}
