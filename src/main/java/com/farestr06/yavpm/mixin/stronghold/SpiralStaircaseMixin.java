package com.farestr06.yavpm.mixin.stronghold;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StrongholdGenerator.SpiralStaircase.class)
public class SpiralStaircaseMixin {
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;STONE_BRICKS:Lnet/minecraft/block/Block;"))
    private Block injectDeepslateBricks() {
        return Blocks.DEEPSLATE_BRICKS;
    }
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;SMOOTH_STONE_SLAB:Lnet/minecraft/block/Block;"))
    private Block injectPolishedDeepslateSlab() {
        return Blocks.POLISHED_DEEPSLATE_SLAB;
    }
}
