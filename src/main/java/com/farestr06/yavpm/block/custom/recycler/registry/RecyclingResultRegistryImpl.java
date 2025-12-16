package com.farestr06.yavpm.block.custom.recycler.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public class RecyclingResultRegistryImpl implements RecyclingResultRegistry {
    private final Map<ItemLike, RecyclingResult> results = new HashMap<>();

    @Override
    public RecyclingResult get(ItemLike item) {
        return results.get(item);
    }

    @Override
    public void add(ItemLike item, RecyclingResult value) {
        results.put(item, value);
    }

    @Override
    public void add(TagKey<Item> tag, RecyclingResult value) {
        throw new UnsupportedOperationException("Tags currently not supported!");
    }

    @Override
    public void remove(ItemLike item) {
        add(item, RecyclingResult.EMPTY);
    }

    @Override
    public void remove(TagKey<Item> tag) {
        throw new UnsupportedOperationException("Tags currently not supported!");
    }

    @Override
    public void clear(ItemLike item) {
        results.remove(item);
    }

    @Override
    public void clear(TagKey<Item> tag) {
        throw new UnsupportedOperationException("Tags currently not supported!");
    }
}
