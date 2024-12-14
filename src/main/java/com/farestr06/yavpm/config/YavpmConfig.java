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

    @SerialEntry(comment = "Tanukis will attempt to transform after at least this many ticks. Default is 2000.")
    public int tanukiBaseTransformDelay = 2000;
    @SerialEntry(comment = "In addition to the base delay, Tanukis will also wait up to this many ticks. Default is 4000.")
    public int tanukiRandomTransformDelay = 2000;
    @SerialEntry(comment = "When a Tanuki tries to transform, the chance of it succeeding is determined by this value. Default is 0.33f.")
    public float tanukiTransformChance = 0.33f;
    @SerialEntry(comment = "Damage is multiplied by this for each level of Void Touched when applied. Default is 1.5f.")
    public float voidTouchedDamageMultiplier = 1.5f;
    @SerialEntry(comment = "When set to false, the dragon fireball will behave like it does in vanilla. Default is true.")
    public boolean voidTouchedDragonFireball = true;

    // region Blocks
    @SerialEntry(comment = "Glowing Obsidian blocks emit the specified light level. Default is 15.")
    public int glowingObsidianLuminance = 15;
    @SerialEntry(comment = "Soul Glowing Obsidian blocks will emit the specified light level. Default is 11.")
    public int soulGlowingObsidianLuminance = 11;

    @SerialEntry(comment = "When set to true, Void Water can create more infinite sources, like how vanilla Water can. Default is false.")
    public boolean voidWaterSourceConversion = false;
    // endregion
    // region Items
    @SerialEntry(comment = "When an Attack Up Rune is applied to equipment, its attack power is multiplied by this factor. Default is 1.2f,")
    public float runeAttackUpgradeFactor = 1.2f;
    @SerialEntry(comment = "When a Durability Up Rune is applied to equipment, its maximum durability is multiplied by this factor. Default is 1.2f,")
    public float runeDurabilityUpgradeFactor = 1.2f;
    @SerialEntry(comment = "When a Speed Up Rune is applied to equipment, its mining speed is multiplied by this factor. Default is 1.2f,")
    public float runeSpeedUpgradeFactor = 1.2f;
    // endregion
}
