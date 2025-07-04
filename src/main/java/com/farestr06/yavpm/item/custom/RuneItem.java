package com.farestr06.yavpm.item.custom;

import net.minecraft.item.Item;
import net.minecraft.text.Text;

@Deprecated(forRemoval = true)
public class RuneItem extends Item {
    protected final Text tooltip;

    public RuneItem(Settings settings, Text tooltip) {
        super(settings);
        this.tooltip = tooltip;
    }
}
