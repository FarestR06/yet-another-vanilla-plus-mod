package com.farestr06.yavpm.mixin.stronghold;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StrongholdGenerator.StoneBrickRandomizer.class)
public class StoneBrickRandomizerMixin {

    @Redirect(method = "setBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;STONE_BRICKS:Lnet/minecraft/block/Block;"))
    private Block injectedDeepslateBricks() {
        return Blocks.DEEPSLATE_BRICKS;
    }
    @Redirect(method = "setBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;MOSSY_STONE_BRICKS:Lnet/minecraft/block/Block;"))
    private Block injectedSculkyDeepslateBricks() {
        
        return YavpmBlocks.SCULKY_DEEPSLATE_BRICKS;
    }
    @Redirect(method = "setBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;CRACKED_STONE_BRICKS:Lnet/minecraft/block/Block;"))
    private Block injectedCrackedDeepslateBricks() {
        return Blocks.CRACKED_DEEPSLATE_BRICKS;
    }
    @Redirect(method = "setBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;INFESTED_STONE_BRICKS:Lnet/minecraft/block/Block;"))
    private Block injectedInfestedDeepslateBricks() {
        return YavpmBlocks.INFESTED_DEEPSLATE_BRICKS;
    }
}