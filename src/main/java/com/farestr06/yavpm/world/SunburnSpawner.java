package com.farestr06.yavpm.world;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.mob.SunburnEntity;
import com.farestr06.yavpm.misc.YavpmStats;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.fluid.FluidState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.spawner.SpecialSpawner;


public class SunburnSpawner implements SpecialSpawner {
    private int cooldown = 0;
    @Override
    public void spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals) {
        if (!(!spawnMonsters || !world.getGameRules().getBoolean(YavpmGameRules.DO_SUNBURN))) {
            Random rand = world.getRandom();
            this.cooldown--;
            if (!(this.cooldown > 0)) {
                this.cooldown = this.cooldown + (90 + rand.nextInt(90)) * 20;
                if (!(world.getAmbientDarkness() > 10 && !world.getDimension().hasSkyLight()))  {
                    for (ServerPlayerEntity serverPlayerEntity : world.getPlayers()) {
                        if (!serverPlayerEntity.isSpectator() ) {
                            BlockPos blockPos = serverPlayerEntity.getBlockPos();
                            if (!world.getDimension().hasSkyLight() || blockPos.getY() >= world.getSeaLevel() && world.isSkyVisible(blockPos)) {
                                LocalDifficulty localDifficulty = world.getLocalDifficulty(blockPos);
                                if (localDifficulty.isHarderThan(rand.nextFloat() * 3.0F)) {
                                    ServerStatHandler serverStatHandler = serverPlayerEntity.getStatHandler();
                                    int sinceLastRest = MathHelper.clamp(serverStatHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST)), 1, Integer.MAX_VALUE);
                                    if (sinceLastRest >= 24000) {
                                        serverPlayerEntity.resetStat(Stats.CUSTOM.getOrCreateStat(YavpmStats.DAYS_SLEPT_THROUGH));
                                    } else if (rand.nextInt(sinceLastRest) <= 24000 && serverStatHandler.getStat(Stats.CUSTOM.getOrCreateStat(YavpmStats.DAYS_SLEPT_THROUGH)) >= 10) {
                                        BlockPos blockPos2 = blockPos.up(20 + rand.nextInt(15)).east(-10 + rand.nextInt(21)).south(-10 + rand.nextInt(21));
                                        BlockState blockState = world.getBlockState(blockPos2);
                                        FluidState fluidState = world.getFluidState(blockPos2);
                                        if (SpawnHelper.isClearForSpawn(world, blockPos2, blockState, fluidState, YavpmEntities.SUNBURN)) {
                                            EntityData entityData = null;
                                            int l = 1 + rand.nextInt(localDifficulty.getGlobalDifficulty().getId() + 1);

                                            for (int m = 0; m < l; m++) {
                                                SunburnEntity sunburnEntity = YavpmEntities.SUNBURN.create(world, SpawnReason.NATURAL);
                                                if (sunburnEntity != null) {
                                                    sunburnEntity.refreshPositionAndAngles(blockPos2, 0.0F, 0.0F);
                                                    entityData = sunburnEntity.initialize(world, localDifficulty, SpawnReason.NATURAL, entityData);
                                                    world.spawnEntityAndPassengers(sunburnEntity);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
