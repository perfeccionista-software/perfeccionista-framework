package io.perfeccionista.framework.pagefactory.dispatcher.system;

import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceSystemDispatcherMatcher;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

// Устанавливаем приложения, время и тп
public interface MobileDeviceSystemDispatcher {

    boolean isApplicationInstalled(@NotNull String bundleId);

    MobileApplicationState getApplicationState(@NotNull String bundleId);

    MobileDeviceSystemDispatcher installApplication(@NotNull String pathToApplication);

    MobileDeviceSystemDispatcher removeApplication(@NotNull String bundleId);

    MobileDeviceSystemDispatcher activateApplication(@NotNull String bundleId);

    MobileDeviceSystemDispatcher terminateApplication(@NotNull String bundleId);

    MobileDeviceSystemDispatcher sendApplicationToBackground(@NotNull Duration duration);

    MobileDeviceSystemDispatcher should(@NotNull MobileDeviceSystemDispatcherMatcher matcher);


    // TODO: Add perfomance data
    //    List<List<Object>> performanceData = instance.getPerformanceData("appBundleID", "batteryinfo", 10);
    //    0 = "cpuinfo"
    //    1 = "memoryinfo"
    //    2 = "batteryinfo"
    //    3 = "networkinfo"

}
