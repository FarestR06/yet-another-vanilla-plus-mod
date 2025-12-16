package com.farestr06.yavpm.mixin.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(SmithingTemplateItem.class)
public interface SmithingTemplateItemInvoker {
    @Invoker("createNetheriteUpgradeIconList")
    static List<ResourceLocation> invokeGetNetheriteUpgradeEmptyBaseSlotTextures() {
        throw new AssertionError();
    }

    @Invoker("createNetheriteUpgradeMaterialList")
    static List<ResourceLocation> invokeGetNetheriteUpgradeEmptyAdditionsSlotTextures() {
        throw new AssertionError();
    }
}
