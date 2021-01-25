package io.perfeccionista.framework.matcher;

import io.perfeccionista.framework.matcher.dispatcher.implementations.MobileApplicationShouldBeInstalled;
import io.perfeccionista.framework.matcher.dispatcher.implementations.MobileDeviceDispatcherShouldBeLocked;
import io.perfeccionista.framework.matcher.dispatcher.implementations.MobileDeviceShouldHaveScreenOrientation;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.ScreenOrientation;
import org.jetbrains.annotations.NotNull;

public class MobileDeviceAssertions {

    private MobileDeviceAssertions() {
    }

    // MobileDeviceShouldBeLocked

    public static MobileDeviceDispatcherShouldBeLocked beLocked() {
        return new MobileDeviceDispatcherShouldBeLocked(true);
    }

    public static MobileDeviceDispatcherShouldBeLocked beUnlocked() {
        return new MobileDeviceDispatcherShouldBeLocked(false);
    }

    // MobileDeviceShouldHaveOrientation

    public static MobileDeviceShouldHaveScreenOrientation havePortraitOrientation() {
        return new MobileDeviceShouldHaveScreenOrientation(ScreenOrientation.PORTRAIT);
    }

    public static MobileDeviceShouldHaveScreenOrientation haveLandscapeOrientation() {
        return new MobileDeviceShouldHaveScreenOrientation(ScreenOrientation.LANDSCAPE);
    }







    // MobileDeviceSystemDispatcher

    public static MobileApplicationShouldBeInstalled beInstalled(@NotNull String bundleId) {
        return new MobileApplicationShouldBeInstalled(bundleId, true);
    }

    public static MobileApplicationShouldBeInstalled beUnlocked(@NotNull String bundleId) {
        return new MobileApplicationShouldBeInstalled(bundleId, false);
    }

}
