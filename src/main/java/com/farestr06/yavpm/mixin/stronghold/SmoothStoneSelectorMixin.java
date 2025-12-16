package com.farestr06.yavpm.mixin.stronghold;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StrongholdPieces.SmoothStoneSelector.class)
public class SmoothStoneSelectorMixin {

    @Redirect(method = "next", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;STONE_BRICKS:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC))
    private Block injectedDeepslateBricks() {
        return Blocks.DEEPSLATE_BRICKS;
    }
    @Redirect(method = "next", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;MOSSY_STONE_BRICKS:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC))
    private Block injectedSculkyDeepslateBricks() {
        
        return YavpmBlocks.SCULKY_DEEPSLATE_BRICKS;
    }
    @Redirect(method = "next", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;CRACKED_STONE_BRICKS:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC))
    private Block injectedCrackedDeepslateBricks() {
        return Blocks.CRACKED_DEEPSLATE_BRICKS;
    }
    @Redirect(method = "next", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;INFESTED_STONE_BRICKS:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC))
    private Block injectedInfestedDeepslateBricks() {
        return YavpmBlocks.INFESTED_DEEPSLATE_BRICKS;
    }
}