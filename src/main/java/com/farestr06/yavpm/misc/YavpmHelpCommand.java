package com.farestr06.yavpm.misc;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public final class YavpmHelpCommand {
    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering help command for YAVPM!");

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("yavpmhelp")
                .executes(ctx -> {
                    FabricClientCommandSource src = ctx.getSource();
                    src.sendFeedback(Text.literal("Welcome to \"Help for Yet Another Vanilla Plus Mod\"!").formatted(Formatting.BOLD, Formatting.AQUA));
                    src.sendFeedback(Text.literal("Using this utility, you can learn everything you'll need to know about this mod.").formatted(Formatting.AQUA));
                    src.sendFeedback(Text.literal("You can learn about the following:").formatted(Formatting.DARK_AQUA));
                    src.sendFeedback(Text.literal("- Blocks\n- Items\n- Mobs\n- Effects\n- World Gen").formatted(Formatting.ITALIC, Formatting.DARK_AQUA));
                    return 1;
                }).then(
                        literal("item")
                                .executes(YavpmHelpCommand::itemInfo)
                                .then(literal("bitter_berries").executes(YavpmHelpCommand::bitterBerries))
                                .then(literal("magic_bean").executes(YavpmHelpCommand::magicBean))
                                .then(literal("fake_beef").executes(YavpmHelpCommand::fakeBeef))
                                .then(literal("baby_key").executes(YavpmHelpCommand::babyKey))
                                .then(literal("gauntlet").executes(YavpmHelpCommand::gauntlet))
                ).then(
                        literal("mob")
                                .executes(YavpmHelpCommand::mobInfo)
                                .then(literal("moongus").executes(YavpmHelpCommand::moongus))
                                .then(literal("carbonfowl").executes(YavpmHelpCommand::carbonfowl))
                                .then(literal("tanuki").executes(YavpmHelpCommand::tanuki))
                                .then(literal("void_phantom").executes(YavpmHelpCommand::voidPhantom))
                ).then(
                        literal("effect")
                                .then(literal("haste").executes(YavpmHelpCommand::haste))
                                .then(literal("nausea").executes(YavpmHelpCommand::nausea))
                                .then(literal("silence").executes(YavpmHelpCommand::silence))
                                .then(literal("choking").executes(YavpmHelpCommand::choking))
                                .then(literal("void_touched").executes(YavpmHelpCommand::voidTouched))
                )
        ));
    }

    private static int itemInfo(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("YAVPM Help (Items)").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("YAVPM has a wide variety of items. From crops to equipment, there's no shortage of stuff to find in this mod.").formatted(Formatting.AQUA));
        src.sendFeedback(Text.literal("There's far too much to list, but here's some highlights:").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("- Bitter Berries\n- Magic Bean\n- Copper Horn\n- Densitite Ingot\n- Key Golem (\"yavpm:baby_key\" internally)").formatted(Formatting.ITALIC, Formatting.DARK_AQUA));
        return 1;
    }
    private static int bitterBerries(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Bitter Berries").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("Type: Food, Crop, Ingredient").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Food: 2 hunger, 0.1 saturation").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Bitter Berries are a type of crop obtained from Wandering Traders and Sniffers. When they're brewed into a Weird Potion, you get a Potion of Haste.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int magicBean(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Magic Bean").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("Type: Food, Crop, Ingredient").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Food: 2 hunger, 0.3 saturation").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("The Magic Bean is a versatile crop. It's a bit hard to find, but you'll be happy when you do.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("It can be used to craft fake animal products, which is nice for those vegan players.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int fakeBeef(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Fake Beef").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("Type: Food").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Food: 3 hunger, 0.3 saturation (Raw) | 8 hunger, 0.8 saturation (Cooked)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Fake Beef is a meatless steak crafted from Magic Beans. It doesn't restore much hunger on its own, but smelting or smoking it will greatly improve its restorative powers.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("It tastes surprisingly similar to real meat; as a result, it restores the same amount of hunger, and it can be fed to wolves like any other meat.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int babyKey(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Key Golem").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("Type: Tool").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Useable Blocks: Lockable Containers (i.e. Chest, Dropper)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("The Key Golem is a mob that appeared in Minecraft Dungeons. In that game, it was used to unlock gold and diamond doors.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("In this mod, the Key Golem is used to lock containers. Obviously, someone could just destroy the container, but that wouldn't work with a Shulker Box, since they hold onto their data when broken.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("It can be crafted with two Gold Ingots, two Gold Nuggets, and one Carved Pumpkin/Jack o'Lantern.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int gauntlet(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Gauntlet").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("Type: Weapon").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Damage: 6").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("The Gauntlet is a somewhat unusual weapon. It's exceedingly rare, similar to the Trident and Mace. Unlike most other weapons, the Gauntlet doesn't have much of a cooldown, meaning you can deal damage quickly.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("To craft a Gauntlet, you'll need gauntlet fragments and netherite scrap. The fragments can be found in the Nether, either inside Bastion Remnant chests or from bartering with piglins.").formatted(Formatting.GRAY));
        return 1;
    }

    private static int mobInfo(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("YAVPM Help (Mobs)").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("YAVPM has five mobs. Two are passive, two are hostile, and one is neutral.").formatted(Formatting.AQUA));
        src.sendFeedback(Text.literal("These are the mobs:").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("- Carbonfowl\n- Moongus\n- Sunburn\n- Tanuki\n- Void Phantom").formatted(Formatting.ITALIC, Formatting.DARK_AQUA));
        return 1;
    }
    private static int moongus(CommandContext<FabricClientCommandSource> ctx) {
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
    }
    private static int carbonfowl(CommandContext<FabricClientCommandSource> ctx) {
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
    }
    private static int tanuki(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Tanuki").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("Type: Passive Animal").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Food: Berries").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("HP: 14").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Habitat: Mountain Biomes (i.e. Windswept Hills, Cherry Grove)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("The Tanuki is a mountain-dwelling mob that looks suspiciously like a raccoon.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("In Japanese folklore, the Tanuki is a master of disguise and shapeshifting. As such, this mob is able to turn into fake logs and ores, assuming that mob griefing is enabled and they're neither a baby nor name-tagged.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("Better yet, those fake blocks drop a small amount of loot. The time it takes for a Tanuki to transform can be changed in the mod's config screen.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int voidPhantom(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Void Phantom").formatted(Formatting.BOLD, Formatting.AQUA));
        src.sendFeedback(Text.literal("Type: Hostile Monster").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("HP: 20 | ATK: 7 to 9.5").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Habitat: The End").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("The Void Phantom is a stronger variant of the Phantom from the End.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("It deals more damage, and it varies in size. Aside from that, it's a normal phantom: it flies, it's weak to sunlight and Enderbane, and it's annoying.").formatted(Formatting.GRAY));
        return 1;
    }

    private static int haste(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Haste").setStyle(Style.EMPTY.withFormatting(Formatting.BOLD).withColor(14270531)));
        src.sendFeedback(Text.literal("Type: Beneficial").formatted(Formatting.BLUE));
        src.sendFeedback(Text.literal("Crafting: Weird Potion + Bitter Berries").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Modifiers: Long, Strong").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Duration: 2 minutes (Normal), 4 minutes (Long), 1 minute (Strong)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Amplifier: Haste I (Normal, Long), Haste III (Strong)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Haste is a vanilla status effect that is brewable in this mod. When applied, your mining speed is increased.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("Normally, you'd need a Beacon to have this effect. However, obtaining Bitter Berries allows you to have this effect in a potion.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int nausea(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Nausea (Potion of Intoxication)").setStyle(Style.EMPTY.withFormatting(Formatting.BOLD).withColor(5578058)));
        src.sendFeedback(Text.literal("Type: Harmful").formatted(Formatting.RED));
        src.sendFeedback(Text.literal("Crafting: Weird Potion + Sweet Berries").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Modifiers: Long").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Duration: 15 seconds (Normal), 45 seconds (Long)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Nausea is a vanilla status effect that is brewable in this mod. When applied, you become dizzy, and your vision becomes distorted.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("Normally, Nausea is inflicted by eating Pufferfish or Closed Eyeblossom Suspicious Stew. However, obtaining Sweet Berries allows you to have this effect in a potion.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("Trivia from FarestR06: I chose Sweet Berries as the potion ingredient after good ol' wine, which is made from grapes and makes you nauseous in high amounts. Also, don't drink wine if you're under 21 or if you plan on driving.").formatted(Formatting.GRAY, Formatting.ITALIC));
        return 1;
    }
    private static int silence(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Silence").setStyle(Style.EMPTY.withFormatting(Formatting.BOLD).withColor(0x00ffff)));
        src.sendFeedback(Text.literal("Type: Beneficial").formatted(Formatting.BLUE));
        src.sendFeedback(Text.literal("Crafting: Weird Potion + Wool").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Modifiers: Long").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Duration: 25 seconds (Normal), 55 seconds (Long)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("When applied, the Silence effect will prevent you from emitting vibrations.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("It'll be very helpful if you decide to explore the Deep Dark, but keep in mind that it does not last very long at all.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int choking(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Choking").setStyle(Style.EMPTY.withFormatting(Formatting.BOLD).withColor(0x7a583d)));
        src.sendFeedback(Text.literal("Type: Harmful").formatted(Formatting.RED));
        src.sendFeedback(Text.literal("Crafting: Water Breathing Potion + Fermented Spider Eye").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Modifiers: Long").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Duration: 4.5 seconds (Normal), 9 seconds (Long)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("When applied, the Choking effect will drown you, whether you're underwater or not.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("The dealt damage bypasses armor and invulnerability, and it does not knock you back.").formatted(Formatting.GRAY));
        return 1;
    }
    private static int voidTouched(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Text.literal("Void Touched").setStyle(Style.EMPTY.withFormatting(Formatting.BOLD).withColor(0xe079fa)));
        src.sendFeedback(Text.literal("Type: Harmful").formatted(Formatting.RED));
        src.sendFeedback(Text.literal("Crafting: Smelt Dragon's Breath in a Furnace").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Modifiers: Long, Strong").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Duration: 40 seconds (Normal), 80 seconds (Long), 20 seconds (Strong)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("Amplifier: Void Touched I (Normal, Long), Void Touched II (Strong)").formatted(Formatting.DARK_AQUA));
        src.sendFeedback(Text.literal("When the Void Touched effect is applied, any damage you take will be multiplied. The multiplier can be changed in the mod's config in Mod Menu.").formatted(Formatting.GRAY));
        src.sendFeedback(Text.literal("The effect can be applied by the Ender Dragon's fireball attack when she is flying, but not by her breath when she is perching.").formatted(Formatting.GRAY));
        return 1;
    }

}
