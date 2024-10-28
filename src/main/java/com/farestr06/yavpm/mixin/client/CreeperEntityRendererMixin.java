package com.farestr06.yavpm.mixin.client;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

@Mixin(CreeperEntityRenderer.class)
public class CreeperEntityRendererMixin {

    // region Creeper Variants
    @Unique
    private static final Identifier ACACIA = makeId("textures/entity/creeper/acacia.png");
    @Unique
    private static final Identifier AZALEA = makeId("textures/entity/creeper/azalea.png");
    @Unique
    private static final Identifier BIRCH = makeId("textures/entity/creeper/birch.png");
    @Unique
    private static final Identifier CHERRY = makeId("textures/entity/creeper/cherry.png");
    @Unique
    private static final Identifier DARK_OAK = makeId("textures/entity/creeper/dark_oak.png");
    @Unique
    private static final Identifier JUNGLE = makeId("textures/entity/creeper/jungle.png");
    @Unique
    private static final Identifier MANGROVE = makeId("textures/entity/creeper/mangrove.png");
    @Unique
    private static final Identifier OAK = makeId("textures/entity/creeper/oak.png");
    @Unique
    private static final Identifier SPRUCE = makeId("textures/entity/creeper/spruce.png");
    // endregion

    // Set Creeper variant depending on biome
    @Inject(method = "getTexture(Lnet/minecraft/entity/mob/CreeperEntity;)Lnet/minecraft/util/Identifier;", at = @At(value = "HEAD"), cancellable = true)
    private void injected(CreeperEntity creeperEntity, CallbackInfoReturnable<Identifier> cir) {
        BlockPos pos = creeperEntity.getBlockPos();
        RegistryEntry<Biome> biome = creeperEntity.getWorld().getBiome(pos);
        if (biome.isIn(ConventionalBiomeTags.IS_SAVANNA_TREE)) {
            cir.setReturnValue(ACACIA);
        } else if (biome.matchesKey(BiomeKeys.LUSH_CAVES)) {
            cir.setReturnValue(AZALEA);
        } else if (biome.isIn(ConventionalBiomeTags.IS_BIRCH_FOREST)) {
            cir.setReturnValue(BIRCH);
        } else if (biome.matchesKey(BiomeKeys.CHERRY_GROVE)) {
            cir.setReturnValue(CHERRY);
        } else if (biome.matchesKey(BiomeKeys.DARK_FOREST)) {
            cir.setReturnValue(DARK_OAK);
        } else if (biome.isIn(ConventionalBiomeTags.IS_JUNGLE_TREE)) {
            cir.setReturnValue(JUNGLE);
        } else if (biome.matchesKey(BiomeKeys.MANGROVE_SWAMP)) {
            cir.setReturnValue(MANGROVE);
        } else if (biome.isIn(ConventionalBiomeTags.IS_CONIFEROUS_TREE)) {
            cir.setReturnValue(SPRUCE);
        } else {
            cir.setReturnValue(OAK);
        }
    }
}
