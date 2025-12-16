package com.farestr06.yavpm.block.custom.entity;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.recycler.RecyclerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmBlockEntities {
    public static final BlockEntityType<RecyclerBlockEntity> RECYCLER = register("recycler", RecyclerBlockEntity::new, YavpmBlocks.RECYCLER);
    public static final BlockEntityType<PinataBlockEntity> PINATA = register("pinata", PinataBlockEntity::new, YavpmBlocks.PINATA);

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
        ResourceLocation id = makeId(name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering block entities for YAVPM!");
    }
}
