package com.farestr06.yavpm.block.custom.entity;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBlockEntities {
    public static final BlockEntityType<KeylockBlockEntity> KEYLOCK = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            makeId("keylock"),
            BlockEntityType.Builder.create(KeylockBlockEntity::new, YavpmBlocks.KEYLOCK).build()
    );

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering block entities for YAVPM!");
    }
}
