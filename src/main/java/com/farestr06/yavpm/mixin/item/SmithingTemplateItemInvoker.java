package com.farestr06.yavpm.mixin.item;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(SmithingTemplateItem.class)
public interface SmithingTemplateItemInvoker {
    @Invoker("getNetheriteUpgradeEmptyBaseSlotTextures")
    static List<Identifier> invokeGetNetheriteUpgradeEmptyBaseSlotTextures() {
        throw new AssertionError();
    }

    @Invoker("getNetheriteUpgradeEmptyAdditionsSlotTextures")
    static List<Identifier> invokeGetNetheriteUpgradeEmptyAdditionsSlotTextures() {
        throw new AssertionError();
    }
}
