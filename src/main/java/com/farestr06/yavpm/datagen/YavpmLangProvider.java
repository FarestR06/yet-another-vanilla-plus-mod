package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.entity.mob.YavpmMobs;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
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
        builder.add("advancements.adventure.transform_mob.title", "Vanilla Plus Tweakin'");
        builder.add("advancements.adventure.transform_mob.description", "Transform a mob with some Soul Powder");

        builder.add("resourcePack.yavpm.programmer_art.name", "YAVPM Programmer Art");

        builder.add(YavpmBlocks.GLOWING_OBSIDIAN, "Glowing Obsidian");
        builder.add(YavpmBlocks.SOUL_GLOWING_OBSIDIAN, "Soul Glowing Obsidian");

        builder.add(YavpmBlocks.ELECTRO_GLASS, "Psychic Glass");

        builder.add("subtitles.item.soul_powder.use", "Soul Powder whooshes");
        builder.add(YavpmItems.SOUL_POWDER, "Soul Powder");


        builder.add(YavpmStatusEffects.VOID_TOUCHED.value(), "Void Touched");

        builder.add("enchantment.yavpm.void_strike", "Void Strike");
        builder.add("enchantment.yavpm.void_strike.desc", "Attacking a target applies a short damage multiplier.");

        builder.add("enchantment.yavpm.critical_hit", "Critical Hit");
        builder.add("enchantment.yavpm.critical_hit.desc", "Gives you a chance to inflict critical hits dealing triple damage.");

        builder.add("enchantment.yavpm.tempo_theft", "Tempo Theft");
        builder.add("enchantment.yavpm.tempo_theft.desc", "Steals a small amount of a mob's movement speed and gives it to the player for a short time.");

        builder.add("enchantment.yavpm.longstrider", "Longstrider");
        builder.add("enchantment.yavpm.longstrider.desc", "Increases the player's stride, letting them climb higher steps and increasing their speed.");


        builder.add(YavpmBlocks.COBBLED_GRANITE, "Cobbled Granite");
        builder.add(YavpmBlocks.COBBLED_DIORITE, "Cobbled Diorite");
        builder.add(YavpmBlocks.COBBLED_ANDESITE, "Cobbled Andesite");
        builder.add(YavpmBlocks.COBBLED_GRANITE_STAIRS, "Cobbled Granite Stairs");
        builder.add(YavpmBlocks.COBBLED_DIORITE_STAIRS, "Cobbled Diorite Stairs");
        builder.add(YavpmBlocks.COBBLED_ANDESITE_STAIRS, "Cobbled Andesite Stairs");
        builder.add(YavpmBlocks.COBBLED_GRANITE_SLAB, "Cobbled Granite Slab");
        builder.add(YavpmBlocks.COBBLED_DIORITE_SLAB, "Cobbled Diorite Slab");
        builder.add(YavpmBlocks.COBBLED_ANDESITE_SLAB, "Cobbled Andesite Slab");
        builder.add(YavpmBlocks.COBBLED_GRANITE_WALL, "Cobbled Granite Wall");
        builder.add(YavpmBlocks.COBBLED_DIORITE_WALL, "Cobbled Diorite Wall");
        builder.add(YavpmBlocks.COBBLED_ANDESITE_WALL, "Cobbled Andesite Wall");

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

        builder.add(YavpmBlocks.POLISHED_GRANITE_TILES, "Polished Granite Tiles");
        builder.add(YavpmBlocks.POLISHED_DIORITE_TILES, "Polished Diorite Tiles");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_TILES, "Polished Andesite Tiles");
        builder.add(YavpmBlocks.POLISHED_GRANITE_TILE_STAIRS, "Polished Granite Tile Stairs");
        builder.add(YavpmBlocks.POLISHED_DIORITE_TILE_STAIRS, "Polished Diorite Tile Stairs");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_TILE_STAIRS, "Polished Andesite Tile Stairs");
        builder.add(YavpmBlocks.POLISHED_GRANITE_TILE_SLAB, "Polished Granite Tile Slab");
        builder.add(YavpmBlocks.POLISHED_DIORITE_TILE_SLAB, "Polished Diorite Tile Slab");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_TILE_SLAB, "Polished Andesite Tile Slab");
        builder.add(YavpmBlocks.POLISHED_GRANITE_TILE_WALL, "Polished Granite Tile Wall");
        builder.add(YavpmBlocks.POLISHED_DIORITE_TILE_WALL, "Polished Diorite Tile Wall");
        builder.add(YavpmBlocks.POLISHED_ANDESITE_TILE_WALL, "Polished Andesite Tile Wall");

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

        builder.add(YavpmBlocks.OAK_SAPLING_CROP, "Oak Sapling Crops");
        builder.add(YavpmItems.ACORN, "Acorn");
        builder.add(YavpmItems.DIAMOND_ACORN, "Diamond Acorn");

        builder.add(YavpmItems.TRUFFLE, "Truffle");

        builder.add(YavpmItems.RICE, "Rice");
        builder.add(YavpmItems.MAGIC_BEANS, "Magic Beans");

        builder.add(YavpmItems.SEA_SOUP, "Sea Soup");

        builder.add(YavpmItems.STUDDED_HELMET, "Studded Cap");
        builder.add(YavpmItems.STUDDED_CHESTPLATE, "Studded Tunic");
        builder.add(YavpmItems.STUDDED_LEGGINGS, "Studded Pants");
        builder.add(YavpmItems.STUDDED_BOOTS, "Studded Boots");
        builder.add("subtitles.item.armor.equip_studded", "Studded armor jingles");

        builder.add("item.minecraft.potion.effect.voided", "Potion of the Void");
        builder.add("item.minecraft.potion.effect.long_voided", "Potion of the Void");
        builder.add("item.minecraft.potion.effect.strong_voided", "Potion of the Void");
        builder.add("item.minecraft.splash_potion.effect.voided", "Splash Potion of the Void");
        builder.add("item.minecraft.splash_potion.effect.long_voided", "Splash Potion of the Void");
        builder.add("item.minecraft.splash_potion.effect.strong_voided", "Splash Potion of the Void");
        builder.add("item.minecraft.lingering_potion.effect.voided", "Lingering Potion of the Void");
        builder.add("item.minecraft.lingering_potion.effect.long_voided", "Lingering Potion of the Void");
        builder.add("item.minecraft.lingering_potion.effect.strong_voided", "Lingering Potion of the Void");
        builder.add("item.minecraft.tipped_arrow.effect.voided", "Arrow of the Void");
        builder.add("item.minecraft.tipped_arrow.effect.long_voided", "Arrow of the Void");
        builder.add("item.minecraft.tipped_arrow.effect.strong_voided", "Arrow of the Void");

        builder.add("item.minecraft.potion.effect.decay", "Potion of Decay");
        builder.add("item.minecraft.potion.effect.long_decay", "Potion of Decay");
        builder.add("item.minecraft.potion.effect.strong_decay", "Potion of Decay");
        builder.add("item.minecraft.splash_potion.effect.decay", "Splash Potion of Decay");
        builder.add("item.minecraft.splash_potion.effect.long_decay", "Splash Potion of Decay");
        builder.add("item.minecraft.splash_potion.effect.strong_decay", "Splash Potion of Decay");
        builder.add("item.minecraft.lingering_potion.effect.decay", "Lingering Potion of Decay");
        builder.add("item.minecraft.lingering_potion.effect.long_decay", "Lingering Potion of Decay");
        builder.add("item.minecraft.lingering_potion.effect.strong_decay", "Lingering Potion of Decay");
        builder.add("item.minecraft.tipped_arrow.effect.decay", "Arrow of Decay");
        builder.add("item.minecraft.tipped_arrow.effect.long_decay", "Arrow of Decay");
        builder.add("item.minecraft.tipped_arrow.effect.strong_decay", "Arrow of Decay");

        builder.add(YavpmItems.CHOCOLATE, "Chocolate Bar");

        builder.add(YavpmBlocks.NETHER_REACTOR_CORE, "Nether Reactor Core");
        builder.add("block.yavpm.nether_reactor_core.success", "Active!");
        builder.add("block.yavpm.nether_reactor_core.bad_structure", "Not the correct pattern!");
        builder.add("block.yavpm.nether_reactor_core.reactor_dead", "Reactor has already been used!");
        builder.add("block.yavpm.nether_reactor_core.already_running", "Reactor is already running!");
        builder.add(YavpmTags.Items.REACTOR_RECHARGERS, "Reactor Rechargers");

        builder.add("subtitles.entity.chicken.egg_break", "Egg breaks");

        builder.add(YavpmBlocks.APPLE_LOG, "Apple Log");
        builder.add(YavpmBlocks.APPLE_WOOD, "Apple Wood");
        builder.add(YavpmBlocks.STRIPPED_APPLE_LOG, "Stripped Apple Log");
        builder.add(YavpmBlocks.STRIPPED_APPLE_WOOD, "Stripped Apple Wood");
        builder.add(YavpmBlocks.APPLE_LEAVES, "Apple Leaves");
        builder.add(YavpmBlocks.APPLE_PLANKS, "Apple Planks");
        builder.add(YavpmBlocks.APPLE_STAIRS, "Apple Stairs");
        builder.add(YavpmBlocks.APPLE_SLAB, "Apple Slab");
        builder.add(YavpmBlocks.APPLE_FENCE, "Apple Fence");
        builder.add(YavpmBlocks.APPLE_FENCE_GATE, "Apple Fence Gate");
        builder.add(YavpmBlocks.APPLE_DOOR, "Apple Door");
        builder.add(YavpmBlocks.APPLE_TRAPDOOR, "Apple Trapdoor");
        builder.add(YavpmBlocks.APPLE_PRESSURE_PLATE, "Apple Pressure Plate");
        builder.add(YavpmBlocks.APPLE_BUTTON, "Apple Button");
        builder.add(YavpmBlocks.APPLE_SIGN, "Apple Sign");
        builder.add(YavpmBlocks.APPLE_HANGING_SIGN, "Apple Hanging Sign");
        builder.add(YavpmItems.APPLE_BOAT, "Apple Boat");
        builder.add(YavpmItems.APPLE_CHEST_BOAT, "Apple Boat with Chest");
        builder.add(YavpmBlocks.APPLE_SAPLING, "Apple Sapling");
        builder.add(YavpmTags.Items.APPLE_LOGS, "Apple Logs");

        builder.add(YavpmItems.MOONGUS_SPAWN_EGG, "Moongus Spawn Egg");
        builder.add(YavpmMobs.MOONGUS, "Moongus");
        builder.add(YavpmTags.Items.CRIMSON_MOONGUS_FOOD, "Crimson Moongus Food");
        builder.add(YavpmTags.Items.WARPED_MOONGUS_FOOD, "Warped Moongus Food");
        builder.add("subtitles.entity.moongus.eat", "Moongus eats");
        builder.add("subtitles.entity.moongus.milk.crimson", "Moongus gets milked awkwardly");
        builder.add("subtitles.entity.moongus.milk.warped", "Moongus gets milked mundanely");
    }
}
