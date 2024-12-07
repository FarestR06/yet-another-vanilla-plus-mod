package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LuckySlipItem extends EnchantedBookItem {
    public LuckySlipItem(Item.Settings settings) {
        super(settings);
    }

    @Nullable
    public static EnchantmentLevelEntry choose(World world) {
        Random rand = world.getRandom();
        Optional<RegistryEntry<Enchantment>> optional = world
                .getRegistryManager()
                .get(RegistryKeys.ENCHANTMENT)
                .getRandomEntry(EnchantmentTags.TRADEABLE, rand);
        if (optional.isPresent()) {

            RegistryEntry<Enchantment> registryEntry = optional.get();
            Enchantment enchantment = registryEntry.value();
            int i = Math.max(enchantment.getMinLevel(), 0);
            int j = Math.min(enchantment.getMaxLevel(), 10);
            int level = MathHelper.nextInt(rand, i, j);

            return new EnchantmentLevelEntry(registryEntry, level);
        }
        return null;
    }

    public static ItemStack forEnchantment(@Nullable EnchantmentLevelEntry info) {
        ItemStack itemStack = new ItemStack(YavpmItems.LUCKY_SLIP);
        if (info != null) {
            itemStack.addEnchantment(info.enchantment, info.level);
        }
        return itemStack;
    }

}
