package com.farestr06.yavpm.block.custom.recycler.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.Map;

public class RecyclingResultRegistryImpl implements RecyclingResultRegistry {
    private final Map<ItemConvertible, RecyclingResult> results = new HashMap<>();

    @Override
    public RecyclingResult get(ItemConvertible item) {
        return results.get(item);
    }

    @Override
    public void add(ItemConvertible item, RecyclingResult value) {
        results.put(item, value);
    }

    @Override
    public void add(TagKey<Item> tag, RecyclingResult value) {
        throw new UnsupportedOperationException("Tags currently not supported!");
    }

    @Override
    public void remove(ItemConvertible item) {
        add(item, RecyclingResult.EMPTY);
    }

    @Override
    public void remove(TagKey<Item> tag) {
        throw new UnsupportedOperationException("Tags currently not supported!");
    }

    @Override
    public void clear(ItemConvertible item) {
        results.remove(item);
    }

    @Override
    public void clear(TagKey<Item> tag) {
        throw new UnsupportedOperationException("Tags currently not supported!");
    }
}
