package com.farestr06.yafm.datagen;

import com.farestr06.yafm.block.YavpmBlocks;
import com.farestr06.yafm.item.YavpmItems;
import com.farestr06.yafm.util.YavpmTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class YavpmLangProvider extends FabricLanguageProvider {
    protected YavpmLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        builder.add("resourcePack.yavpm.programmer_art.name", "YAVPM Programmer Art");
        builder.add(YavpmBlocks.GLOWING_OBSIDIAN, "Glowing Obsidian");
        builder.add(YavpmBlocks.SOUL_GLOWING_OBSIDIAN, "Soul Glowing Obsidian");
        builder.add(YavpmItems.SOUL_POWDER, "Soul Powder");

        builder.add(YavpmBlocks.POLISHED_GRANITE_BRICKS, "Polished Granite Bricks");
        builder.add(YavpmBlocks.POLISHED_DIORITE_BRICKS, "Polished Diorite Bricks");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_BRICKS, "Polished Andesite Bricks");
        builder.add(YavpmBlocks.POLISHED_GRANITE_BRICK_STAIRS, "Polished Granite Brick Stairs");
        builder.add(YavpmBlocks.POLISHED_DIORITE_BRICK_STAIRS, "Polished Diorite Brick Stairs");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_BRICK_STAIRS, "Polished Andesite Brick Stairs");
        builder.add(YavpmBlocks.POLISHED_GRANITE_BRICK_SLAB, "Polished Granite Brick Slab");
        builder.add(YavpmBlocks.POLISHED_DIORITE_BRICK_SLAB, "Polished Diorite Brick Slab");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_BRICK_SLAB, "Polished Andesite Brick Slab");
        builder.add(YavpmBlocks.POLISHED_GRANITE_BRICK_WALL, "Polished Granite Brick Wall");
        builder.add(YavpmBlocks.POLISHED_DIORITE_BRICK_WALL, "Polished Diorite Brick Wall");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_BRICK_WALL, "Polished Andesite Brick Wall");

        builder.add("subtitles.item.reactor.charge", "Reactor charges");
        builder.add(YavpmItems.REACTOR, "Reactor");
        builder.add(YavpmItems.HEATED_REACTOR, "Reactor");

        builder.add(YavpmItems.MOLY, "Magic Herb");

        builder.add(YavpmBlocks.PEANUT_CROP, "Peanut Crops");
        builder.add(YavpmItems.PEANUT, "Peanut");
        builder.add(YavpmItems.COOKED_PEANUT, "Roasted Peanut");

        builder.add(YavpmBlocks.BANANA_CROP, "Banana Crops");
        builder.add(YavpmItems.BANANA, "Banana Bunch");
        builder.add(YavpmItems.BANANA_SEEDS, "Banana Seeds");

        builder.add(YavpmItems.CHOCOLATE, "Chocolate Bar");

        builder.add(YavpmTags.Items.REACTOR_RECHARGERS, "Reactor Rechargers");

        builder.add("subtitles.entity.chicken.egg_break", "Egg breaks");
    }
}
