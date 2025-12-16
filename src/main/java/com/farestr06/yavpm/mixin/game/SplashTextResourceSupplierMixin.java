package com.farestr06.yavpm.mixin.game;

import com.farestr06.yavpm.config.YavpmConfig;
import com.llamalad7.mixinextras.sugar.Local;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.chrono.HijrahChronology;
import java.time.temporal.ChronoField;
import java.util.Calendar;

@Mixin(SplashManager.class)
public class SplashTextResourceSupplierMixin {
    @Unique
    private static final SplashRenderer _MURICA__ = new SplashRenderer("`MURICA!!"); // 4th of July
    @Unique
    private static final SplashRenderer HAPPY_BIRTHDAY__FAREST_ = new SplashRenderer("Happy birthday, FarestR06!"); // Farest's Birthday
    @Unique
    private static final SplashRenderer IT_IS_SNAPSHOT_DAY__MY_DUDES_ = new SplashRenderer("It is snapshot day, my dudes!"); // Snapshot Day (Tuesday)
    @Unique
    private static final SplashRenderer RAMADAN_MUBARAK_ = new SplashRenderer("Ramadan mubarak!"); // Ramadan

    @Redirect(method = "getSplash", at = @At(value = "INVOKE", target = "Ljava/util/Calendar;get(I)I", ordinal = 0))
    private int redirected(Calendar instance, int field) {
        ConfigClassHandler<YavpmConfig> handler = YavpmConfig.HANDLER;
        if (handler.instance().displayIslamicHolidaySplashes || handler.instance().displayChristianHolidaySplashes) { // Christians and Muslims observe Christmas, so we'll check if their splashes are enabled.
            return instance.get(Calendar.DATE);
        }
        return 69420; // Disable Christmas splash for those who don't observe it
    }

    @Inject(method = "getSplash", at = @At(value = "INVOKE", target = "Ljava/util/Calendar;setTime(Ljava/util/Date;)V", shift = At.Shift.AFTER), cancellable = true)
    private void injected(CallbackInfoReturnable<SplashRenderer> cir, @Local Calendar calendar) {
        float randFloat = RandomSource.create().nextFloat();
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
