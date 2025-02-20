package com.farestr06.yavpm.block.custom.entity;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.recycler.RecyclerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBlockEntities {
    public static final BlockEntityType<RecyclerBlockEntity> RECYCLER = register("recycler", RecyclerBlockEntity::new, YavpmBlocks.RECYCLER);

            /*
            Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            makeId("keylock"),
            BlockEntityType.Builder.create(KeylockBlockEntity::new, YavpmBlocks.KEYLOCK).build()
    );
             */

    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
                                                                       FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
                                                                       Block... blocks) {
        Identifier id = makeId(name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering block entities for YAVPM!");
    }
}
