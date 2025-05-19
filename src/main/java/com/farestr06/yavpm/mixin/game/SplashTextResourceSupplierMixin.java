package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.config.YavpmConfig;
import com.llamalad7.mixinextras.sugar.Local;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.chrono.HijrahChronology;
import java.time.temporal.ChronoField;
import java.util.Calendar;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Unique
    private static final SplashTextRenderer _MURICA__ = new SplashTextRenderer("`MURICA!!");
    @Unique
    private static final SplashTextRenderer HAPPY_BIRTHDAY__FAREST_ = new SplashTextRenderer("Happy birthday, FarestR06!");
    @Unique
    private static final SplashTextRenderer IT_IS_SNAPSHOT_DAY__MY_DUDES_ = new SplashTextRenderer("It is snapshot day, my dudes!");
    @Unique
    private static final SplashTextRenderer RAMADAN_MUBARAK_ = new SplashTextRenderer("Ramadan mubarak!");

    @Redirect(method = "get", at = @At(value = "INVOKE", target = "Ljava/util/Calendar;get(I)I", ordinal = 0))
    private int redirected(Calendar instance, int field) {
        ConfigClassHandler<YavpmConfig> handler = YavpmConfig.HANDLER;
        if (handler.instance().displayIslamicHolidaySplashes || handler.instance().displayChristianHolidaySplashes) { // Christians and Muslims observe Christmas, so we'll check if their splashes are enabled.
            return instance.get(Calendar.DATE);
        }
        return 69420; // Disable Christmas splash for those who don't observe it
    }

    @Inject(method = "get", at = @At(value = "INVOKE", target = "Ljava/util/Calendar;setTime(Ljava/util/Date;)V", shift = At.Shift.AFTER), cancellable = true)
    private void injected(CallbackInfoReturnable<SplashTextRenderer> cir, @Local Calendar calendar) {
        float randFloat = Random.create().nextFloat();
        ConfigClassHandler<YavpmConfig> handler = YavpmConfig.HANDLER;
        if (
                calendar.get(Calendar.MONTH) == Calendar.AUGUST && calendar.get(Calendar.DATE) == 4
                && handler.instance().farestsBirthday
        ) {
            cir.setReturnValue(HAPPY_BIRTHDAY__FAREST_);
        }
        if (
                calendar.get(Calendar.MONTH) == Calendar.JULY && calendar.get(Calendar.DATE) == 4
        ) {
            cir.setReturnValue(_MURICA__);
        }
        if (
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
                && randFloat < handler.instance().snapshotDaySplashChance
        ) {
            cir.setReturnValue(IT_IS_SNAPSHOT_DAY__MY_DUDES_);
        }
        if (
                HijrahChronology.INSTANCE.dateNow().get(ChronoField.MONTH_OF_YEAR) == 9 // Is it currently Ramadan?
                && handler.instance().displayIslamicHolidaySplashes // Making sure that the player is Muslim
                && randFloat < handler.instance().chanceForLongLastingSplashes
        ) {
            cir.setReturnValue(RAMADAN_MUBARAK_);
        }
    }
}
