package com.farestr06.yavpm.item;

import com.farestr06.yavpm.mixin.item.SmithingTemplateItemInvoker;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class DensititeSmithingTemplateHelper {
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    private static final Text DENSITITE_UPGRADE_APPLIES_TO_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.netherite_upgrade.applies_to"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    private static final Text DENSITITE_UPGRADE_INGREDIENTS_TEXT = Text.translatable(
                    Util.createTranslationKey("item", makeId("smithing_template.densitite_upgrade.ingredients"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    private static final Text DENSITITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.netherite_upgrade.base_slot_description"))
    );
    private static final Text DENSITITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", makeId("smithing_template.densitite_upgrade.additions_slot_description"))
    );

    public static SmithingTemplateItem createDensititeUpgrade(Item.Settings settings) {
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
