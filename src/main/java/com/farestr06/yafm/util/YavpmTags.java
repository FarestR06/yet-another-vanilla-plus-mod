package com.farestr06.yafm.util;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class YavpmTags {
    public static class Items {
        public static TagKey<Item> REACTOR_RECHARGERS = register("reactor_rechargers");

        private static TagKey<Item> register(String path) {
            return TagKey.of(RegistryKeys.ITEM, YetAnotherVanillaPlusMod.makeId(path));
        }
    }
    public static class EntityTypes {
        public static TagKey<EntityType<?>> HUMANOID_ZOMBIES = register("humanoid_zombies");
        public static TagKey<EntityType<?>> HUMANOID_SKELETONS = register("humanoid_skeletons");

        private static TagKey<EntityType<?>> register(String path) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, YetAnotherVanillaPlusMod.makeId(path));
        }
    }
}
