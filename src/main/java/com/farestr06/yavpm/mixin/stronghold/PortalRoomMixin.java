package com.farestr06.yavpm.mixin.stronghold;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class PortalRoomMixin {
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;STONE_BRICK_STAIRS:Lnet/minecraft/block/Block;"))
    private Block injectDeepslateBricks() {
        return Blocks.DEEPSLATE_BRICK_STAIRS;
    }
}
