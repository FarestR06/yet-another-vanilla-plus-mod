package com.farestr06.yavpm.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmConfig {
    public static final ConfigClassHandler<YavpmConfig> HANDLER = ConfigClassHandler.createBuilder(YavpmConfig.class)
            .id(makeId("config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("yavpm.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(true)
                    .build()
            ).build();

    // region Entities/Mob Effects
    @SerialEntry(comment = "When true, breeding chickens will create an egg item that always hatches, rather than directly spawning a baby. Default is true.")
    public boolean chickenBreedingCreatesEggs = true;
    // region Tanuki Mob
    @SerialEntry(comment = "Tanukis will attempt to transform after at least this many ticks. Default is 2000.")
    public int tanukiBaseTransformDelay = 2000;
    @SerialEntry(comment = "In addition to the base delay, Tanukis will also wait up to this many ticks. Default is 4000.")
    public int tanukiRandomTransformDelay = 4000;
    @SerialEntry(comment = "When a Tanuki tries to transform, the chance of it succeeding is determined by this value. Default is 0.3f.")
    public float tanukiTransformChance = 0.3f;
    // endregion
    // region Void Touched Mob Effect
    @SerialEntry(comment = "Damage is multiplied by this for each level of Void Touched when applied. Default is 1.5f.")
    public float voidTouchedDamageMultiplier = 1.5f;
    @SerialEntry(comment = "When set to false, the dragon fireball will behave like it does in vanilla. Default is true.")
    public boolean voidTouchedDragonFireball = true;
    // endregion
    // endregion

    // region Blocks/Fluids
    @SerialEntry(comment = "Glowing Obsidian blocks will emit the specified light level. Default is 12.")
    public int glowingObsidianLuminance = 12;
    @SerialEntry(comment = "Soul Glowing Obsidian blocks will emit the specified light level. Default is 9.")
    public int soulGlowingObsidianLuminance = 9;
    // endregion

    // region Items
    @SerialEntry
    public boolean potionStacking = true;
    @SerialEntry(comment = "If true, Key Golems will cry out when held. Default is true.")
    public boolean babyKeyCries = true;
    @SerialEntry(comment = "If true, potions spawned by Ominous Spawners will need to be brewed with Warped Wart. Moongi are unaffected by this option. Default is true.")
    public boolean weirdTrialChamberPotions = true;
    @SerialEntry(comment = "Some treasures that are uncraftable in vanilla, like tridents or name tags, are craftable when this option is enabled. Default is true.")
    public boolean rareEquipmentCraftingRecipes = true;
    // endregion

    @SerialEntry
    public boolean yavpmHelpExperiment = FabricLoader.getInstance().isDevelopmentEnvironment();
    @SerialEntry
    public boolean recyclerExperiment = FabricLoader.getInstance().isDevelopmentEnvironment();
    @SerialEntry
    public boolean nulliumExperiment = FabricLoader.getInstance().isDevelopmentEnvironment();

    // region Compatibility
    @SerialEntry
    public boolean dropperToRecycler = false;
    @SerialEntry
    public boolean doubleSlabs = false;
    @SerialEntry
    public boolean moreTrapdoors = false;
    @SerialEntry
    public boolean moreBark = false;
    @SerialEntry
    public boolean moreStairs = false;
    @SerialEntry
    public boolean backToBlocks = false;
    @SerialEntry
    public boolean unpackWarpedWart = false;
    // endregion

    // region Misc
    @SerialEntry(comment = "Special splashes that last a long time will only appear with the specified probability. Default is 30%.")
    public float chanceForLongLastingSplashes = 0.3f;
    @SerialEntry(comment = "If true, splashes commemorating Christian holidays (i.e. Easter, Christmas) will be displayed. Default is true, for inclusivity.")
    public boolean displayChristianHolidaySplashes = true;
    @SerialEntry(comment = "If true, splashes commemorating Islamic holidays will be displayed. Default is true, for inclusivity.")
    public boolean displayIslamicHolidaySplashes = true;
    // endregion

    // region Easter Eggs
    @SerialEntry(comment = "A special splash will sometimes appear on Wednesday; the likelihood of this happening is determined by this value. Default is 0.3f.")
    public float snapshotDaySplashChance = 0.3f;
    @SerialEntry(comment = "A special splash will appear on the mod author's birthday; this only happens if this option is enabled. Default is true.")
    public boolean farestsBirthday = true;
    // endregion

    public void experimentSetup() {
        if (!recyclerExperiment) {
            dropperToRecycler = false;
        }
    }
}
