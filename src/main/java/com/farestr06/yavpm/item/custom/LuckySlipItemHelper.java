package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LuckySlipItemHelper {

    @Nullable
    public static EnchantmentInstance choose(Level world) {
        RandomSource rand = world.getRandom();
        Optional<Holder<Enchantment>> optional = world
                .registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getRandomElementOf(EnchantmentTags.TRADEABLE, rand);
        if (optional.isPresent()) {

            Holder<Enchantment> registryEntry = optional.get();
            Enchantment enchantment = registryEntry.value();
            int i = Math.max(enchantment.getMinLevel(), 0);
            int j = Math.min(enchantment.getMaxLevel(), 10);
            int level = Mth.nextInt(rand, i, j);

            return new EnchantmentInstance(registryEntry, level);
        }
        return null;
    }

    public static ItemStack forEnchantment(@Nullable EnchantmentInstance info) {
        ItemStack itemStack = new ItemStack(YavpmItems.LUCKY_SLIP);
        if (info != null) {
            itemStack.enchant(info.enchantment(), info.level());
        }
        return itemStack;
    }

}
