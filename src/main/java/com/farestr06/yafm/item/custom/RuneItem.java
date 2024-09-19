package com.farestr06.yafm.item.custom;

import net.minecraft.item.Item;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RuneItem extends Item {
    public RuneItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName() {
        return super.getName().copy().setStyle(
                Style.EMPTY.withFont(
                        Identifier.ofVanilla("illageralt")
                )
        );
    }
}
