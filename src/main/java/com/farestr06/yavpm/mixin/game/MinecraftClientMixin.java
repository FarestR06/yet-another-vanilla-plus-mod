package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.MusicInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public ClientPlayerEntity player;

    @Shadow @Nullable public ClientWorld world;

    @Redirect(method = "getMusicInstance", at = @At(value = "NEW", target = "(Lnet/minecraft/sound/MusicSound;F)Lnet/minecraft/client/sound/MusicInstance;", ordinal = 3))
    private MusicInstance redirected(MusicSound musicSound, float f) {
        assert player != null;
        assert world != null;
        BlockPos pos = player.getBlockPos();
        if ( // Player is underground, right? ...Right??
                (world.getBlockState(pos).isOf(Blocks.CAVE_AIR) || (!world.isSkyVisible(pos) || player.getY() < 63))
                        && world.getLightLevel(pos) < 7
        ) {
            return new MusicInstance(YavpmSounds.CAVE, f);
        }
        return new MusicInstance(musicSound, f);
    }
}
