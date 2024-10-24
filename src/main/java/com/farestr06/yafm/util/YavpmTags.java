package com.farestr06.yafm.util;

import com.farestr06.yafm.YetAnotherVanillaPlusMod;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class YavpmTags {
    public static class Items {
        public static final TagKey<Item> REACTOR_RECHARGERS = of("reactor_rechargers");
        public static final TagKey<Item> APPLE_LOGS = of("apple_logs");

        private static TagKey<Item> of(String path) {
            return TagKey.of(RegistryKeys.ITEM, YetAnotherVanillaPlusMod.makeId(path));
        }
    }
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> HUMANOID_ZOMBIES = of("humanoid_zombies");
        public static final TagKey<EntityType<?>> HUMANOID_SKELETONS = of("humanoid_skeletons");

        private static TagKey<EntityType<?>> of(String path) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, YetAnotherVanillaPlusMod.makeId(path));
        }
    }
}
