package com.farestr06.yavpm.mixin.stronghold;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StrongholdPieces.StraightStairsDown.class)
public class StraightStairsDownMixin {
    @Redirect(method = "postProcess", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;COBBLESTONE_STAIRS:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC))
    private Block injectCobbledDeepslateStairs() {
        return Blocks.COBBLED_DEEPSLATE_STAIRS;
    }
    @Redirect(method = "postProcess", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;STONE_BRICKS:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC))
    private Block injectDeepslateBricks() {
        return Blocks.DEEPSLATE_BRICKS;
    }
}
