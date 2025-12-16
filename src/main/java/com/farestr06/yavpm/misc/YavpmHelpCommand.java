package com.farestr06.yavpm.misc;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public final class YavpmHelpCommand {
    public static void init() {
        YetAnotherVanillaPlusMod.LOGGER.info("Registering help command for YAVPM!");

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("yavpmhelp")
                .executes(ctx -> {
                    FabricClientCommandSource src = ctx.getSource();
                    src.sendFeedback(Component.literal("Welcome to \"Help for Yet Another Vanilla Plus Mod\"!").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
                    src.sendFeedback(Component.literal("Using this utility, you can learn everything you'll need to know about this mod.").withStyle(ChatFormatting.AQUA));
                    src.sendFeedback(Component.literal("You can learn about the following:").withStyle(ChatFormatting.DARK_AQUA));
                    src.sendFeedback(Component.literal("- Blocks\n- Items\n- Mobs\n- Effects\n- World Gen").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_AQUA));
                    return 1;
                }).then(
                        literal("block")
                                .then(literal("burner").executes(YavpmHelpCommand::burner))
                ).then(
                        literal("item")
                                .executes(YavpmHelpCommand::itemInfo)
                                .then(literal("bitter_berries").executes(YavpmHelpCommand::bitterBerries))
                                .then(literal("soups_and_stews").executes(YavpmHelpCommand::soupsAndStews))
                                .then(literal("moly").executes(YavpmHelpCommand::moly))
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
                                .then(literal("wither").executes(YavpmHelpCommand::wither))
                                .then(literal("haste").executes(YavpmHelpCommand::haste))
                                .then(literal("nausea").executes(YavpmHelpCommand::nausea))
                                .then(literal("silence").executes(YavpmHelpCommand::silence))
                                .then(literal("choking").executes(YavpmHelpCommand::choking))
                                .then(literal("void_touched").executes(YavpmHelpCommand::voidTouched))
                )
        ));
    }

    private static int soupsAndStews(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Soups and Stews").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Food").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Food: Hunger varies, 0.6 saturation").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("This mod adds three food bowls: Sea Soup, Chicken Soup, and Fancy Mushroom Stew.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Fancy Mushroom Stew is a punched up mushroom stew. It uses full red and brown mushroom blocks rather than little mushrooms, and it uses a truffle dug up by a Sniffer.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Sea Soup is loosely based on Japanese miso soup. It's made from tropical fish, rice, dried kelp and a magic bean.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Chicken Soup is, well, soup made from chicken. It's made from a cooked chicken, a carrot, a brown mushroom and some rice.").withStyle(ChatFormatting.GRAY));
        return 1;
    }

    private static int burner(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Burner").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Redstone").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Hardness: 0.5 | Resistance: 0.5").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Burner is a redstone component that burns any mob standing on it when it's powered. It can be thought of as a toggleable Magma Block.").withStyle(ChatFormatting.GRAY));
        return 1;
    }

    private static int itemInfo(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("YAVPM Help (Items)").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("YAVPM has a wide variety of items. From crops to equipment, there's no shortage of stuff to find in this mod.").withStyle(ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("There's far too much to list, but here's some highlights:").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("- Bitter Berries\n- Magic Bean\n- Copper Horn\n- Densitite Ingot\n- Key Golem (\"yavpm:baby_key\" internally)").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_AQUA));
        return 1;
    }
    private static int bitterBerries(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Bitter Berries").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Food, Crop, Ingredient").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Food: 2 hunger, 0.1 saturation").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Bitter Berries are a type of crop obtained from Wandering Traders and Sniffers. When they're brewed into a Weird Potion, you get a Potion of Haste.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int moly(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Magic Herb (yavpm:moly)").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Food").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Food: Hunger and Health fully restored, effects cleared").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Magic Herb is a rare herb that can be found as treasure or bought from Wandering Traders.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("It's hard to come by, and it takes a bit longer to eat than other foods, but its potent restorative powers more than make up for it.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int magicBean(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Magic Bean").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Food, Crop, Ingredient").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Food: 2 hunger, 0.3 saturation").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Magic Bean is a versatile crop. It's a bit hard to find, but you'll be happy when you do.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("It can be used to craft fake animal products, which is nice if you happen to be vegan.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int fakeBeef(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Fake Beef").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Food").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Food: 3 hunger, 0.3 saturation (Raw) | 8 hunger, 0.8 saturation (Cooked)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Fake Beef is a meatless steak crafted from Magic Beans. It doesn't restore much hunger on its own, but smelting or smoking it will greatly improve its restorative powers.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("It tastes surprisingly similar to real meat; as a result, it restores the same amount of hunger, and it can be fed to wolves like any other meat.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int babyKey(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Key Golem (yavpm:baby_key)").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Tool").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Useable Blocks: Lockable Containers (i.e. Chest, Dropper)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Key Golem is a mob that appeared in Minecraft Dungeons. In that game, it was used to unlock gold and diamond doors.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("In this mod, the Key Golem is used to lock containers. Obviously, someone could just destroy the container, but that wouldn't work with a Shulker Box, since they hold onto their data when broken.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("It can be crafted with two Gold Ingots, two Gold Nuggets, and one Carved Pumpkin/Jack o'Lantern.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int gauntlet(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Gauntlet").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Weapon").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Damage: 6").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Gauntlet is a somewhat unusual weapon. It's exceedingly rare, similar to the Trident and Mace. Unlike most other weapons, the Gauntlet doesn't have much of a cooldown, meaning you can deal damage quickly.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("To craft a Gauntlet, you'll need gauntlet fragments and netherite scrap. The fragments can be found in the Nether, either inside Bastion Remnant chests or from bartering with piglins.").withStyle(ChatFormatting.GRAY));
        return 1;
    }

    private static int mobInfo(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("YAVPM Help (Mobs)").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("YAVPM has five mobs. Two are passive, two are hostile, and one is neutral.").withStyle(ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("These are the mobs:").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("- Carbonfowl\n- Moongus\n- Sunburn\n- Tanuki\n- Void Phantom").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_AQUA));
        return 1;
    }
    private static int moongus(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Moongus").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Passive Animal").withStyle(ChatFormatting.GREEN));
        src.sendFeedback(Component.literal("Food: Wheat").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("HP: 10").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Habitat: Crimson and Warped Forest").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Moongus is a cow that has been infested by wart; in other words, it is a Nether variant of the Mooshroom.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Moongi can be milked for potions, much like how Mooshrooms can be milked for mushroom stew. Crimson Moongi produce Awkward Potions, while Warped produce Weird Potions.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("The milked potions can be modified by feeding the Moongus potion ingredients, like how the rare Brown Mooshroom can be fed a flower to change its stew.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Lastly, this mob can be milked for, well, Milk, just like typical Cows and Mooshrooms.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int carbonfowl(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Carbonfowl").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Neutral Animal").withStyle(ChatFormatting.YELLOW));
        src.sendFeedback(Component.literal("Food: Glow Berries, Moss/Azalea, Sculk, common monster drops").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("HP: 8 | ATK: 2.5 | DEF: 7.5").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Habitat: Cave Biomes (i.e. Lush Caves, Deep Dark)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Carbonfowl is a rare, chicken-like mob that lives deep underground.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Carbonfowl will attack in groups when provoked, unlike Chickens, which run away like a bunch of chickens. (Get it?)").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Instead of laying eggs, Carbonfowl will lay chunks of Graphite. They're not very useful on their own, but if you have a lot, you can craft them into diamonds.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("That means if you have a lot of Carbonfowl, you can have an endless supply of diamonds!.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int tanuki(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Tanuki").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Passive Animal").withStyle(ChatFormatting.GREEN));
        src.sendFeedback(Component.literal("Food: Berries").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("HP: 14").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Habitat: Mountain Biomes (i.e. Windswept Hills, Cherry Grove)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Tanuki is a mountain-dwelling mob that looks suspiciously like a raccoon.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("In Japanese folklore, the Tanuki is a master of disguise and shapeshifting. As such, this mob is able to turn into fake logs and ores, assuming that mob griefing is enabled and they're neither a baby nor name-tagged.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Better yet, those fake blocks drop a small amount of loot. The time it takes for a Tanuki to transform can be changed in the mod's config screen.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int voidPhantom(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Void Phantom").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        src.sendFeedback(Component.literal("Type: Hostile Monster").withStyle(ChatFormatting.RED));
        src.sendFeedback(Component.literal("HP: 20 | ATK: 7 to 9.5").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Habitat: The End").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("The Void Phantom is a stronger variant of the Phantom from the End.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("It deals more damage, and it varies in size. Aside from that, it's a normal phantom: it flies, it's weak to sunlight and Enderbane, and it's annoying.").withStyle(ChatFormatting.GRAY));
        return 1;
    }

    private static int wither(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Wither (Potion of Decay)").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD).withColor(7561558)));
        src.sendFeedback(Component.literal("Type: Harmful").withStyle(ChatFormatting.RED));
        src.sendFeedback(Component.literal("Crafting: Weird Potion + Wither Rose").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Modifiers: Long, Strong").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Duration: 30 seconds (Normal), 1 minute (Long), 15 seconds (Strong)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Amplifier: Wither I (Normal, Long), Wither II (Strong)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Wither is a vanilla status effect that is brewable in this mod. When applied, you'll take damage over time. Unlike Poison, the Wither effect will reduce your health to zero and kill you.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Normally, this effect is inflicted by Wither Skeletons and the Wither. However, obtaining Wither Roses allows you to have this effect in a potion.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int haste(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Haste").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD).withColor(14270531)));
        src.sendFeedback(Component.literal("Type: Beneficial").withStyle(ChatFormatting.BLUE));
        src.sendFeedback(Component.literal("Crafting: Weird Potion + Bitter Berries").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Modifiers: Long, Strong").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Duration: 2 minutes (Normal), 4 minutes (Long), 1 minute (Strong)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Amplifier: Haste I (Normal, Long), Haste III (Strong)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Haste is a vanilla status effect that is brewable in this mod. When applied, your mining speed is increased.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Normally, you'd need a Beacon to have this effect. However, obtaining Bitter Berries allows you to have this effect in a potion.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int nausea(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Nausea (Potion of Intoxication)").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD).withColor(5578058)));
        src.sendFeedback(Component.literal("Type: Harmful").withStyle(ChatFormatting.RED));
        src.sendFeedback(Component.literal("Crafting: Weird Potion + Sweet Berries").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Modifiers: Long").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Duration: 15 seconds (Normal), 45 seconds (Long)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Nausea is a vanilla status effect that is brewable in this mod. When applied, you become dizzy, and your vision becomes distorted.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Normally, Nausea is inflicted by eating Pufferfish or Closed Eyeblossom Suspicious Stew. However, obtaining Sweet Berries allows you to have this effect in a potion.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("Trivia from FarestR06: I chose Sweet Berries as the potion ingredient after good ol' wine, which is made from grapes and makes you nauseous in high amounts. Also, don't drink wine if you're under 21 or if you plan on driving.").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        return 1;
    }
    private static int silence(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Silence").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD).withColor(0x00ffff)));
        src.sendFeedback(Component.literal("Type: Beneficial").withStyle(ChatFormatting.BLUE));
        src.sendFeedback(Component.literal("Crafting: Weird Potion + Wool").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Modifiers: Long").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Duration: 25 seconds (Normal), 55 seconds (Long)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("When applied, the Silence effect will prevent you from emitting vibrations.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("It'll be very helpful if you decide to explore the Deep Dark, but keep in mind that it does not last very long at all.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int choking(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Choking").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD).withColor(0x7a583d)));
        src.sendFeedback(Component.literal("Type: Harmful").withStyle(ChatFormatting.RED));
        src.sendFeedback(Component.literal("Crafting: Water Breathing Potion + Fermented Spider Eye").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Modifiers: Long").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Duration: 4.5 seconds (Normal), 9 seconds (Long)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("When applied, the Choking effect will drown you, whether you're underwater or not.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("The dealt damage bypasses armor and invulnerability, and will not knock you back.").withStyle(ChatFormatting.GRAY));
        return 1;
    }
    private static int voidTouched(CommandContext<FabricClientCommandSource> ctx) {
        FabricClientCommandSource src = ctx.getSource();
        src.sendFeedback(Component.literal("Void Touched").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD).withColor(0xe079fa)));
        src.sendFeedback(Component.literal("Type: Harmful").withStyle(ChatFormatting.RED));
        src.sendFeedback(Component.literal("Crafting: Smelt Dragon's Breath in a Furnace, Feed Dragon's Breath to a Warped Moongus").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Modifiers: Long, Strong").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Duration: 40 seconds (Normal), 80 seconds (Long), 20 seconds (Strong)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("Amplifier: Void Touched I (Normal, Long), Void Touched II (Strong)").withStyle(ChatFormatting.DARK_AQUA));
        src.sendFeedback(Component.literal("When the Void Touched effect is applied, any damage you take will be multiplied. The multiplier can be changed in the mod's config in Mod Menu.").withStyle(ChatFormatting.GRAY));
        src.sendFeedback(Component.literal("The effect can be inflicted by the Ender Dragon's fireball attack when she is flying, but not by her breath when she is perching.").withStyle(ChatFormatting.GRAY));
        return 1;
    }

}
