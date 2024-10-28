package com.farestr06.yavpm.mixin.item;

import com.farestr06.yavpm.item.YavpmArmorMaterials;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemModelGenerator.class)
public class ItemModelGeneratorMixin {


    /**
     * The data generator can only register a dye overlay for leather armor.
     * @param instance The armor being checked.
     * @param tRegistryEntry Not used here.
     * @return Whether or not the armor material is leather OR studded
     */
    @SuppressWarnings({"deprecation"})
    @Redirect(method = "registerArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/entry/RegistryEntry;matches(Lnet/minecraft/registry/entry/RegistryEntry;)Z"))
    private boolean redirected(RegistryEntry<ArmorMaterial> instance, RegistryEntry<?> tRegistryEntry) {
        return instance.matches(YavpmArmorMaterials.STUDDED) || instance.matches(ArmorMaterials.LEATHER);
    }
}
