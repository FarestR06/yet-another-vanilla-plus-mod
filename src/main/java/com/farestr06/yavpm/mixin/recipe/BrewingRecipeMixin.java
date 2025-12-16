package com.farestr06.yavpm.mixin.recipe;

import com.farestr06.yavpm.config.YavpmConfig;
import com.farestr06.yavpm.item.YavpmPotions;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionBrewing.class)
public class BrewingRecipeMixin {
    @Redirect(method = "addVanillaMixes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addStartMix(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V", ordinal = 0))
    private static void windCharged(PotionBrewing.Builder instance, Item ingredient, Holder<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.BREEZE_ROD, Potions.WIND_CHARGED);
        } else {
            instance.addStartMix(ingredient, potion);
        }
    }
    @Redirect(method = "addVanillaMixes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addStartMix(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V", ordinal = 1))
    private static void oozing(PotionBrewing.Builder instance, Item ingredient, Holder<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.SLIME_BLOCK, Potions.OOZING);
        } else {
            instance.addStartMix(ingredient, potion);
        }
    }

    @Redirect(method = "addVanillaMixes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addStartMix(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V", ordinal = 2))
    private static void infested(PotionBrewing.Builder instance, Item ingredient, Holder<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.STONE, Potions.INFESTED);
        } else {
        instance.addStartMix(ingredient, potion);
        }
    }
    @Redirect(method = "addVanillaMixes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addStartMix(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V", ordinal = 3))
    private static void weaving(PotionBrewing.Builder instance, Item ingredient, Holder<Potion> potion) {
        if (YavpmConfig.HANDLER.instance().weirdTrialChamberPotions) {
            registerRecipes(instance, Items.COBWEB, Potions.WEAVING);
        } else {
            instance.addStartMix(ingredient, potion);
        }
    }

    @Unique
    private static void registerRecipes(PotionBrewing.Builder builder, Item ingredient, Holder<Potion> potion) {
        if (potion.value().isEnabled(builder.getEnabledFeatures())) {
            builder.addMix(Potions.WATER, ingredient, Potions.MUNDANE);
            builder.addMix(YavpmPotions.WEIRD, ingredient, potion);
        }
    }
}
