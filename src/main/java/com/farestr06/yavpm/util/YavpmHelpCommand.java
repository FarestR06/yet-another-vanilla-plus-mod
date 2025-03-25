package com.farestr06.yavpm.util;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public final class YavpmHelpCommand {
    public static void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("yavpmhelp")
                .executes(ctx -> {
                    FabricClientCommandSource src = ctx.getSource();
                    src.sendFeedback(Text.literal("Welcome to \"Help for Yet Another Vanilla Plus Mod\"!").formatted(Formatting.BOLD, Formatting.AQUA));
                    src.sendFeedback(Text.literal("Using this utility, you can learn everything you'll need to know about this mod.").formatted(Formatting.AQUA));
                    src.sendFeedback(Text.literal("You can learn about the following:").formatted(Formatting.DARK_AQUA));
                    src.sendFeedback(Text.literal("- Blocks\n- Items\n- Mobs\n- Effects\n- World Gen").formatted(Formatting.ITALIC, Formatting.DARK_AQUA));
                    return 1;
                })
                .then(
                        literal("mob")
                                .executes(ctx ->{
                                    FabricClientCommandSource src = ctx.getSource();
                                    src.sendFeedback(Text.literal("YAVPM Help (Mobs)").formatted(Formatting.BOLD, Formatting.AQUA));
                                    src.sendFeedback(Text.literal("YAVPM has five mobs. Two are passive, two are not, and one is neutral").formatted(Formatting.AQUA));
                                    src.sendFeedback(Text.literal("These are the mobs:").formatted(Formatting.DARK_AQUA));
                                    src.sendFeedback(Text.literal("- Carbonfowl\n- Moongus\n- Sunburn\n- Tanuki\n- Void Phantom").formatted(Formatting.ITALIC, Formatting.DARK_AQUA));
                                    return 1;
                                })
                                .then(
                                        literal("moongus").executes(ctx -> {
                                            FabricClientCommandSource src = ctx.getSource();
                                            src.sendFeedback(Text.literal("Moongus").formatted(Formatting.BOLD, Formatting.AQUA));
                                            src.sendFeedback(Text.literal("Type: Passive Animal").formatted(Formatting.DARK_AQUA));
                                            src.sendFeedback(Text.literal("Food: Wheat").formatted(Formatting.DARK_AQUA));
                                            src.sendFeedback(Text.literal("HP: 10").formatted(Formatting.DARK_AQUA));
                                            src.sendFeedback(Text.literal("Habitat: Crimson and Warped Forest").formatted(Formatting.DARK_AQUA));
                                            src.sendFeedback(Text.literal("The Moongus is a cow that has been infested by wart; in other words, it is a Nether variant of the Mooshroom.").formatted(Formatting.GRAY));
                                            src.sendFeedback(Text.literal("Moongi can be milked for potions, much like how Mooshrooms can be milked for mushroom stew. Crimson Moongi produce Awkward Potions, while Warped produce Weird Potions.").formatted(Formatting.GRAY));
                                            src.sendFeedback(Text.literal("The milked potions can be modified by feeding the Moongus potion ingredients, like how the rare Brown Mooshroom can be fed a flower to change its stew.").formatted(Formatting.GRAY));
                                            return 1;
                                        })
                                ).then(
                                    literal("carbonfowl").executes(ctx -> {
                                        FabricClientCommandSource src = ctx.getSource();
                                        src.sendFeedback(Text.literal("Carbonfowl").formatted(Formatting.BOLD, Formatting.AQUA));
                                        src.sendFeedback(Text.literal("Type: Neutral Animal").formatted(Formatting.DARK_AQUA));
                                        src.sendFeedback(Text.literal("Food: Glow Berries, Moss/Azalea, Sculk, common monster drops").formatted(Formatting.DARK_AQUA));
                                        src.sendFeedback(Text.literal("HP: 8 | ATK: 2.5 | DEF: 7.5").formatted(Formatting.DARK_AQUA));
                                        src.sendFeedback(Text.literal("Habitat: Cave Biomes (i.e. Lush Caves, Deep Dark)").formatted(Formatting.DARK_AQUA));
                                        src.sendFeedback(Text.literal("The Carbonfowl is a rare, chicken-like mob that lives deep underground.").formatted(Formatting.GRAY));
                                        src.sendFeedback(Text.literal("Carbonfowl will attack in groups when provoked, unlike Chickens, which run away like a bunch of chickens. (Get it?)").formatted(Formatting.GRAY));
                                        src.sendFeedback(Text.literal("Instead of laying eggs, Carbonfowl will lay chunks of Graphite. They're not very useful on their own, but if you have a lot, you can craft them into diamonds.").formatted(Formatting.GRAY));
                                        src.sendFeedback(Text.literal("That means if you have a lot of Carbonfowl, you can have an endless supply of diamonds!.").formatted(Formatting.GRAY));
                                        return 1;
                                    })
                                )
                )
                )
        );
    }
}
