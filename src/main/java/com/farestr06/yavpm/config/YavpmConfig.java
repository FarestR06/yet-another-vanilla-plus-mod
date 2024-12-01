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

    @SerialEntry(comment = "Damage is multiplied by this for each level of Void Touched when applied")
    public float voidTouchedDamageMultiplier = 1.5f;

    @SerialEntry(comment = "When set to false, the dragon fireball will behave like it does in vanilla.")
    public boolean voidTouchedDragonFireball = true;

    @SerialEntry(comment = "When set to true, Void Water can create more infinite sources, much like normal Water can.")
    public boolean voidWaterSourceConversion = false;
}
