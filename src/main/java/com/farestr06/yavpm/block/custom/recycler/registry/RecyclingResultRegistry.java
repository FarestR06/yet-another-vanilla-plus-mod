package com.farestr06.yavpm.block.custom.recycler.registry;

import net.fabricmc.fabric.api.util.Item2ObjectMap;

public interface RecyclingResultRegistry extends Item2ObjectMap<RecyclingResult> {
    RecyclingResultRegistry INSTANCE = new RecyclingResultRegistryImpl();
}
