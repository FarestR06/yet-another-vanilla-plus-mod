package com.farestr06.yavpm.item;

import com.farestr06.yavpm.mixin.item.SmithingTemplateItemInvoker;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class DensititeSmithingTemplateHelper {
    private static final ChatFormatting DESCRIPTION_FORMATTING = ChatFormatting.BLUE;

    private static final Component DENSITITE_UPGRADE_APPLIES_TO_TEXT = Component.translatable(
                    Util.makeDescriptionId("item", ResourceLocation.withDefaultNamespace("smithing_template.netherite_upgrade.applies_to"))
            )
            .withStyle(DESCRIPTION_FORMATTING);
    private static final Component DENSITITE_UPGRADE_INGREDIENTS_TEXT = Component.translatable(
                    Util.makeDescriptionId("item", makeId("smithing_template.densitite_upgrade.ingredients"))
            )
            .withStyle(DESCRIPTION_FORMATTING);
    private static final Component DENSITITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.withDefaultNamespace("smithing_template.netherite_upgrade.base_slot_description"))
    );
    private static final Component DENSITITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(
            Util.makeDescriptionId("item", makeId("smithing_template.densitite_upgrade.additions_slot_description"))
    );

    public static SmithingTemplateItem createDensititeUpgrade(Item.Properties settings) {
        return new SmithingTemplateItem(
                DENSITITE_UPGRADE_APPLIES_TO_TEXT,
                DENSITITE_UPGRADE_INGREDIENTS_TEXT,
                DENSITITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                DENSITITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                SmithingTemplateItemInvoker.invokeGetNetheriteUpgradeEmptyBaseSlotTextures(),
                SmithingTemplateItemInvoker.invokeGetNetheriteUpgradeEmptyAdditionsSlotTextures(),
                settings
        );
    }
}
