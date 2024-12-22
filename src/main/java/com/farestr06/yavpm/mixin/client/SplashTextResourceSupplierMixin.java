package com.farestr06.yavpm.mixin.client;

import com.farestr06.yavpm.config.YavpmConfig;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Calendar;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Unique
    private static final SplashTextRenderer HAPPY_BIRTHDAY__FAREST_ = new SplashTextRenderer("Happy birthday, FarestR06!");
    @Unique
    private static final SplashTextRenderer IT_IS_SNAPSHOT_DAY__MY_DUDES_ = new SplashTextRenderer("It is snapshot day, my dudes!");

    @Inject(method = "get", at = @At(value = "INVOKE", target = "Ljava/util/Calendar;setTime(Ljava/util/Date;)V", shift = At.Shift.AFTER), cancellable = true)
    private void injected(CallbackInfoReturnable<SplashTextRenderer> cir, @Local Calendar calendar) {
        if (
                calendar.get(Calendar.MONTH) == Calendar.AUGUST && calendar.get(Calendar.DATE) == 4
                && YavpmConfig.HANDLER.instance().farestsBirthday
        ) {
            cir.setReturnValue(HAPPY_BIRTHDAY__FAREST_);
        }
        if (
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
                && Random.create().nextFloat() < YavpmConfig.HANDLER.instance().snapshotDaySplashChance
        ) {
            cir.setReturnValue(IT_IS_SNAPSHOT_DAY__MY_DUDES_);
        }
    }
}
