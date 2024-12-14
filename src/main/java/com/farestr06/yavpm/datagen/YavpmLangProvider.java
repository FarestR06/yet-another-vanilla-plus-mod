package com.farestr06.yavpm.datagen;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.entity.effect.YavpmStatusEffects;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.item.enchantment.YavpmEnchantments;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class YavpmLangProvider extends FabricLanguageProvider {
    protected YavpmLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        builder.add("option.yavpm.title", "Yet Another Vanilla Plus Mod Options");

        builder.add("option.yavpm.blocks_and_fluids", "Blocks/Fluids");
        builder.add("option.yavpm.blocks_and_fluids.glowing_obsidian", "Glowing Obsidian");
        builder.add("option.yavpm.blocks_and_fluids.void", "Void Block and Void Water");

        builder.add("option.yavpm.items", "Items");
        builder.add("option.yavpm.items.runes", "Upgrade Runes");

        builder.add("option.yavpm.entities_and_effects", "Entities/Mob Effects");

        builder.add("option.yavpm.entities_and_effects.tanuki", "Tanuki");

        builder.add("option.yavpm.tanuki_base_transform_delay.title", "Tanuki Transformation Base Delay");
        builder.add("option.yavpm.tanuki_base_transform_delay.desc", "Tanukis will attempt to transform after at least this many ticks. Default is 2000.");

        builder.add("option.yavpm.tanuki_random_transform_delay.title", "Tanuki Transformation Additional Delay");
        builder.add("option.yavpm.tanuki_random_transform_delay.desc", "In addition to the base delay, Tanukis will also wait up to this many ticks. Default is 4000.");

        builder.add("option.yavpm.tanuki_transform_chance.title", "Tanuki Transformation Success Rate");
        builder.add("option.yavpm.tanuki_transform_chance.desc", "When a Tanuki tries to transform, the chance of it succeeding is determined by this value. Default is 0.33f.");

        builder.add("option.yavpm.entities_and_effects.void_touched", "Void Touched Mob Effect");

        builder.add("option.yavpm.void_touched_damage_multiplier.title", "Void Touched Damage Multiplier");
        builder.add("option.yavpm.void_touched_damage_multiplier.desc", "When the Void Touched effect is applied, any damage taken is multiplied by this for each level of the effect. Default is 1.5f.");

        builder.add("option.yavpm.void_touched_dragon_fireball.title", "Void-touched Dragon Fireball");
        builder.add("option.yavpm.void_touched_dragon_fireball.desc", "When set to false, the dragon fireball will behave like it does in vanilla, dealing damage instead of applying Void Touched. Default is true.");

        builder.add("option.yavpm.glowing_obsidian_luminance.title", "Glowing Obsidian Luminance");
        builder.add("option.yavpm.glowing_obsidian_luminance.desc", "Glowing Obsidian blocks will emit the specified light level. Default is 15.");

        builder.add("option.yavpm.soul_glowing_obsidian_luminance.title", "Soul Glowing Obsidian Luminance");
        builder.add("option.yavpm.soul_glowing_obsidian_luminance.desc", "Soul Glowing Obsidian blocks will emit the specified light level. Default is 11.");

        builder.add("option.yavpm.void_water_source_conversion.title", "Void Water Converts To Source");
        builder.add("option.yavpm.void_water_source_conversion.desc", "When flowing void water is surrounded on two sides by water sources it converts into a source. Default is false.");

        builder.add("option.yavpm.rune_attack_upgrade_factor.title", "Attack Up Rune Upgrade Factor");
        builder.add("option.yavpm.rune_attack_upgrade_factor.desc", "When an Attack Up Rune is applied to equipment, its attack power is multiplied by this factor. Default is 1.2f,");
        builder.add("option.yavpm.rune_durability_upgrade_factor.title", "Durability Up Rune Upgrade Factor");
        builder.add("option.yavpm.rune_durability_upgrade_factor.desc", "When an Durability Up Rune is applied to equipment, its maximum durability is multiplied by this factor. Default is 1.2f,");
        builder.add("option.yavpm.rune_speed_upgrade_factor.title", "Speed Up Rune Upgrade Factor");
        builder.add("option.yavpm.rune_speed_upgrade_factor.desc", "When an Speed Up Rune is applied to equipment, its mining speed is multiplied by this factor. Default is 1.2f,");

        builder.add("advancements.husbandry.eat_fake_animal_product.title", "Steak n' Fake");
        builder.add("advancements.husbandry.eat_fake_animal_product.description", "Spare a cow's life and eat a Magic Bean-based alternative");
        builder.add("advancements.husbandry.lip_smacker.title", "Lip Smacker");
        builder.add("advancements.husbandry.lip_smacker.description", "You'd be surprised by how much wolves enjoy peanuts");
        builder.add("advancements.husbandry.lucky_ticket.title", "Lucky Ticket");
        builder.add("advancements.husbandry.lucky_ticket.description", "Eat a Fortune Cookie for a Lucky Slip");
        builder.add("advancements.husbandry.craft_diamonds_from_graphene.title", "Infinite Diamond Glitch");
        builder.add("advancements.husbandry.craft_diamonds_from_graphene.description", "Collect Graphite from a Carbonfowl and turn it into Diamonds");

        builder.add("advancements.adventure.upgrade_tool_with_rune.title", "Power Up");
        builder.add("advancements.adventure.upgrade_tool_with_rune.description", "Upgrade your equipment with a Rune");

        builder.add("advancements.nether.convert_cow_to_moongus.title", "Fungus Amongus");
        builder.add("advancements.nether.convert_cow_to_moongus.description", "Convert a Cow into a Moongus");

        builder.add("advancements.end.pluck_needles_from_prickle_log.title", "Plucky Prickle");
        builder.add("advancements.end.pluck_needles_from_prickle_log.description", "Pluck the needles off a Prickle Tree");

        builder.add("resourcePack.yavpm.programmer_art.name", "YAVPM Programmer Art");

        builder.add(YavpmBlocks.VOID_WATER, "Void Water");
        builder.add(YavpmItems.VOID_WATER_BUCKET, "Void Water Bucket");

        builder.add(YavpmTags.Items.RUNES, "Runes");
        builder.add(YavpmTags.Items.RUNE_ATTACK_APPLICABLE, "Upgradable with Attack Up Rune");
        builder.add(YavpmTags.Items.RUNE_DURABILITY_APPLICABLE, "Upgradable with Durability Up Rune");
        builder.add(YavpmTags.Items.RUNE_SPEED_APPLICABLE, "Upgradable with Speed Up Rune");

        builder.add(YavpmItems.RUNE_ATTACK, "Attack Up Rune");
        builder.add("item.yavpm.rune_attack.tooltip", "Increases a weapon's base damage by 50 percent when used in a Crafting Table.");
        builder.add(YavpmItems.RUNE_DURABILITY, "Durability Up Rune");
        builder.add("item.yavpm.rune_durability.tooltip", "Increases a tool's maximum durability by 50 percent when used in a Crafting Table.");
        builder.add(YavpmItems.RUNE_SPEED, "Speed Up Rune");
        builder.add("item.yavpm.rune_speed.tooltip", "Increases a tool's mining speed by 50 percent when used in a Crafting Table.");

        builder.add(YavpmBlocks.GLOWING_OBSIDIAN, "Glowing Obsidian");
        builder.add(YavpmBlocks.SOUL_GLOWING_OBSIDIAN, "Soul Glowing Obsidian");

        builder.add(YavpmBlocks.ELECTRO_GLASS, "Psychic Glass");

        builder.add(YavpmItems.FORTUNE_COOKIE, "Fortune Cookie");
        builder.add(YavpmItems.LUCKY_SLIP, "Lucky Slip");

        builder.add(YavpmStatusEffects.VOID_TOUCHED.value(), "Void Touched");

        builder.addEnchantment(YavpmEnchantments.VOID_STRIKE, "Void Strike");
        builder.add("enchantment.yavpm.void_strike.desc", "Attacking a target applies a short damage multiplier.");
        builder.addEnchantment(YavpmEnchantments.ILLAGERS_BANE, "Illager's Bane");
        builder.add("enchantment.yavpm.illagers_bane.desc", "Attacks deal extra damage to Illagers and their allies.");
        builder.addEnchantment(YavpmEnchantments.ENDERBANE, "Enderbane");
        builder.add("enchantment.yavpm.enderbane.desc", "Extra damage is dealt to mobs under the influence of the §kEnder Dragon§r.");
        builder.addEnchantment(YavpmEnchantments.STIFFNESS, "Stiffness");
        builder.add("enchantment.yavpm.stiffness.desc", "Applies a protection effect to Elytra, reducing damage from most sources.");

        builder.add("enchantment.yavpm.critical_hit", "Critical Hit");
        builder.add("enchantment.yavpm.critical_hit.desc", "Gives you a chance to inflict critical hits dealing triple damage.");

        builder.add("enchantment.yavpm.tempo_theft", "Tempo Theft");
        builder.add("enchantment.yavpm.tempo_theft.desc", "Steals a small amount of a mob's movement speed and gives it to the player for a short time.");

        builder.add("enchantment.yavpm.longstrider", "Longstrider");
        builder.add("enchantment.yavpm.longstrider.desc", "Increases the player's stride, letting them climb higher steps and increasing their speed.");

        builder.add(YavpmBlocks.KIMBERLITE, "Kimberlite");
        builder.add(YavpmItems.RAW_DIAMOND, "Raw Diamond");
        builder.add(YavpmItems.GRAPHITE, "Graphite");
        builder.add(YavpmBlocks.GRAPHITE_BLOCK, "Block of Graphite");
        builder.add(YavpmBlocks.GRAPHENE_BLOCK, "Block of Graphene");

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

        builder.add(YavpmItems.CHEESE, "Cheese");

        builder.add(YavpmItems.BEAN_TOAST, "Bean Toast");
        builder.add(YavpmItems.COOKED_EGG, "Fried Egg");

        builder.add(YavpmBlocks.MAGIC_BEAN_CROP, "Magic Bean Crops");
        builder.add(YavpmItems.MAGIC_BEAN, "Magic Bean");
        builder.add(YavpmItems.FAKE_BEEF, "Fake Raw Beef");
        builder.add(YavpmItems.COOKED_FAKE_BEEF, "Fake Steak");
        builder.add(YavpmItems.FAKE_MILK_BUCKET, "Fake Milk Bucket");
        builder.add(YavpmItems.TOFU, "Tofu");

        builder.add(YavpmItems.SUSHI, "Sushi");
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

        // builder.add(YavpmBlocks.NETHER_REACTOR_CORE, "Nether Reactor Core");
        builder.add("block.yavpm.nether_reactor_core.success", "Active!");
        builder.add("block.yavpm.nether_reactor_core.bad_structure", "Not the correct pattern!");
        builder.add("block.yavpm.nether_reactor_core.reactor_dead", "Reactor has already been used!");
        builder.add("block.yavpm.nether_reactor_core.already_running", "Reactor is already running!");
        builder.add(YavpmTags.Items.REACTOR_RECHARGERS, "Reactor Rechargers");

        builder.add("subtitles.entity.chicken.egg_break", "Egg breaks");

        builder.add(YavpmBlocks.PRICKLE_LOG, "Prickle Log");
        builder.add(YavpmBlocks.PRICKLE_WOOD, "Prickle Wood");
        builder.add(YavpmBlocks.STRIPPED_PRICKLE_LOG, "Stripped Prickle Log");
        builder.add(YavpmBlocks.STRIPPED_PRICKLE_WOOD, "Stripped Prickle Wood");
        builder.add(YavpmBlocks.PRICKLE_PLANKS, "Prickle Planks");
        builder.add(YavpmBlocks.PRICKLE_STAIRS, "Prickle Stairs");
        builder.add(YavpmBlocks.PRICKLE_SLAB, "Prickle Slab");
        builder.add(YavpmBlocks.PRICKLE_FENCE, "Prickle Fence");
        builder.add(YavpmBlocks.PRICKLE_FENCE_GATE, "Prickle Fence Gate");
        builder.add(YavpmBlocks.PRICKLE_DOOR, "Prickle Door");
        builder.add(YavpmBlocks.PRICKLE_TRAPDOOR, "Prickle Trapdoor");
        builder.add(YavpmBlocks.PRICKLE_PRESSURE_PLATE, "Prickle Pressure Plate");
        builder.add(YavpmBlocks.PRICKLE_BUTTON, "Prickle Button");
        builder.add(YavpmItems.PRICKLE_SIGN, "Prickle Sign");
        builder.add(YavpmItems.PRICKLE_HANGING_SIGN, "Prickle Hanging Sign");
        builder.add(YavpmBlocks.PRICKLE_SHOOT, "Prickle Shoot");
        builder.add(YavpmTags.Items.PRICKLE_LOGS, "Prickle Logs");
        builder.add("subtitles.block.prickle_log.pluck", "Prickle Log plucks");

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

        builder.add(Blocks.NETHER_WART, "Crimson Wart");
        builder.add(Items.NETHER_WART, "Crimson Wart");
        builder.add(Blocks.NETHER_WART_BLOCK, "Crimson Wart Block");
        builder.add(YavpmBlocks.WARPED_WART, "Warped Wart");
        builder.add(YavpmItems.WARPED_WART, "Warped Wart");

        builder.add(YavpmItems.PHANTOM_CHORD, "Phantom Chord");

        builder.add(YavpmItems.CARBONFOWL_SPAWN_EGG, "Carbonfowl Spawn Egg");
        builder.add(YavpmEntities.CARBONFOWL, "Carbonfowl");
        builder.add(YavpmItems.MOONGUS_SPAWN_EGG, "Moongus Spawn Egg");
        builder.add(YavpmEntities.MOONGUS, "Moongus");
        builder.add(YavpmItems.TANUKI_SPAWN_EGG, "Tanuki Spawn Egg");

        builder.add(YavpmEntities.TANUKI, "Tanuki");
        builder.add(YavpmTags.Items.CRIMSON_MOONGUS_FOOD, "Crimson Moongus Food");
        builder.add(YavpmTags.Items.WARPED_MOONGUS_FOOD, "Warped Moongus Food");
        builder.add("subtitles.entity.moongus.eat", "Moongus eats");
        builder.add("subtitles.entity.moongus.milk.crimson", "Moongus gets milked awkwardly");
        builder.add("subtitles.entity.moongus.milk.warped", "Moongus gets milked mundanely");

        builder.add("subtitles.entity.tanuki.ambient", "Tanuki squeaks");
        builder.add("subtitles.entity.tanuki.death", "Tanuki dies");
        builder.add("subtitles.entity.tanuki.eat", "Tanuki eats");
        builder.add("subtitles.entity.tanuki.hurt", "Tanuki hurts");

        builder.add("biome.yavpm.orchard_peaks", "Orchard Peaks");
        builder.add("biome.yavpm.withered_scar", "Withered Scar");
    }
}
