package com.farestr06.yavpm.world;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.entity.mob.SunburnEntity;
import com.farestr06.yavpm.misc.YavpmStats;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;


public class SunburnSpawner implements CustomSpawner {
    private int cooldown = 0;

    @Override
    public void tick(ServerLevel serverLevel, boolean spawnMonsters) {
        if (!(!spawnMonsters || !serverLevel.getGameRules().getBoolean(YavpmGameRules.DO_SUNBURN))) {
            RandomSource rand = serverLevel.getRandom();
            this.cooldown--;
            if (!(this.cooldown > 0)) {
                this.cooldown = this.cooldown + (90 + rand.nextInt(90)) * 20;
                if (!(serverLevel.getSkyDarken() > 10 && !serverLevel.dimensionType().hasSkyLight()))  {
                    for (ServerPlayer serverPlayerEntity : serverLevel.players()) {
                        if (!serverPlayerEntity.isSpectator() ) {
                            BlockPos blockPos = serverPlayerEntity.blockPosition();
                            if (!serverLevel.dimensionType().hasSkyLight() || blockPos.getY() >= serverLevel.getSeaLevel() && serverLevel.canSeeSky(blockPos)) {
                                DifficultyInstance localDifficulty = serverLevel.getCurrentDifficultyAt(blockPos);
                                if (localDifficulty.isHarderThan(rand.nextFloat() * 3.0F)) {
                                    ServerStatsCounter serverStatHandler = serverPlayerEntity.getStats();
                                    int sinceLastRest = Mth.clamp(serverStatHandler.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1, Integer.MAX_VALUE);
                                    if (sinceLastRest >= 24000) {
                                        serverPlayerEntity.resetStat(Stats.CUSTOM.get(YavpmStats.DAYS_SLEPT_THROUGH));
                                    } else if (rand.nextInt(sinceLastRest) <= 24000 && serverStatHandler.getValue(Stats.CUSTOM.get(YavpmStats.DAYS_SLEPT_THROUGH)) >= 10) {
                                        BlockPos blockPos2 = blockPos.above(20 + rand.nextInt(15)).east(-10 + rand.nextInt(21)).south(-10 + rand.nextInt(21));
                                        BlockState blockState = serverLevel.getBlockState(blockPos2);
                                        FluidState fluidState = serverLevel.getFluidState(blockPos2);
                                        if (NaturalSpawner.isValidEmptySpawnBlock(serverLevel, blockPos2, blockState, fluidState, YavpmEntities.SUNBURN)) {
                                            SpawnGroupData entityData = null;
                                            int l = 1 + rand.nextInt(localDifficulty.getDifficulty().getId() + 1);

                                            for (int m = 0; m < l; m++) {
                                                SunburnEntity sunburnEntity = YavpmEntities.SUNBURN.create(serverLevel, EntitySpawnReason.NATURAL);
                                                if (sunburnEntity != null) {
                                                    sunburnEntity.snapTo(blockPos2, 0.0F, 0.0F);
                                                    entityData = sunburnEntity.finalizeSpawn(serverLevel, localDifficulty, EntitySpawnReason.NATURAL, entityData);
                                                    serverLevel.addFreshEntityWithPassengers(sunburnEntity);
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
