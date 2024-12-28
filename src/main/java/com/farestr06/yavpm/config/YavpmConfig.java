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
    @SerialEntry(comment = "When set to true, Void Water can create more infinite sources, like how vanilla Water can. Default is true.")
    public boolean voidWaterSourceConversion = true;
    // endregion
    // region Items
    @SerialEntry(comment = "When an Attack Up Rune is applied to equipment, its attack power is multiplied by this factor. Default is 1.2f,")
    public float runeAttackUpgradeFactor = 1.2f;
    @SerialEntry(comment = "When a Durability Up Rune is applied to equipment, its maximum durability is multiplied by this factor. Default is 1.2f,")
    public float runeDurabilityUpgradeFactor = 1.2f;
    @SerialEntry(comment = "When a Speed Up Rune is applied to equipment, its mining speed is multiplied by this factor. Default is 1.2f,")
    public float runeSpeedUpgradeFactor = 1.2f;
    @SerialEntry(comment = "When enabled, a Key Golem will make noise while selected in the hotbar. If the noises annoy you, this option can disable them. Default is true.")
    public boolean keyGolemCries = true;
    // endregion
    // region Easter Eggs
    @SerialEntry(comment = "A special splash will sometimes appear on Wednesday; the likelihood of this happening is determined by this value. Default is 0.2f.")
    public float snapshotDaySplashChance = 0.2f;
    @SerialEntry(comment = "A special splash will appear on the mod author's birthday; this only happens if this option is enabled. Default is true.")
    public boolean farestsBirthday = true;
    // endregion
}
