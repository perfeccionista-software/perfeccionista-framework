package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.AbstractMobileAppiumParallelTest;
import io.perfeccionista.framework.utils.ThreadUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.matcher.MobileDeviceAssertions.beInstalled;
import static io.perfeccionista.framework.matcher.MobileDeviceAssertions.beLocked;
import static io.perfeccionista.framework.matcher.MobileDeviceAssertions.beUnlocked;
import static io.perfeccionista.framework.matcher.MobileDeviceAssertions.haveLandscapeOrientation;
import static io.perfeccionista.framework.matcher.MobileDeviceAssertions.havePortraitOrientation;
import static io.perfeccionista.framework.pagefactory.dispatcher.screen.ScreenOrientation.LANDSCAPE;
import static io.perfeccionista.framework.pagefactory.dispatcher.screen.ScreenOrientation.PORTRAIT;

@Disabled("Demo application needed")
class DeviceDispatcherTest extends AbstractMobileAppiumParallelTest {
    public static final String APP_BUNDLE_ID = "appBundleID";

    @Test
    void deviceLockTest() {
        startDefaultDeviceDispatcher()
                .should(beUnlocked())
                .lock()
                .should(beLocked())
                .unlock()
                .should(beUnlocked());
    }

    @Test
    void deviceRotateTest() {
        MobileDeviceDispatcher deviceDispatcher = startDefaultDeviceDispatcher();
        deviceDispatcher.screen()
                .should(havePortraitOrientation())
                .rotateTo(LANDSCAPE)
                .should(haveLandscapeOrientation())
                .rotateTo(PORTRAIT)
                .should(havePortraitOrientation());
    }

    @Test
    void deviceShakeTest() {
        startDefaultDeviceDispatcher()
                .shake();
    }

    @Test
    void customTest() {
        MobileDeviceDispatcher deviceDispatcher = startDefaultDeviceDispatcher();

        logMobileAppState(deviceDispatcher);

        // FIXME Demo APK location
        deviceDispatcher.system()
                .installApplication("/myApp.apk");

        logMobileAppState(deviceDispatcher);

        deviceDispatcher.system()
                .should(beInstalled(APP_BUNDLE_ID))
                .sendApplicationToBackground(Duration.ofSeconds(2))
                .activateApplication(APP_BUNDLE_ID);

        logMobileAppState(deviceDispatcher);

        ThreadUtils.sleep(Duration.ofSeconds(3));

        deviceDispatcher.system()
                .terminateApplication(APP_BUNDLE_ID);

        logMobileAppState(deviceDispatcher);

        deviceDispatcher.system()
                .removeApplication(APP_BUNDLE_ID);

        logMobileAppState(deviceDispatcher);
    }

    @Test
    void authorizationTest() {
        MobileDeviceDispatcher deviceDispatcher = startDefaultDeviceDispatcher();
        if (!deviceDispatcher.system().isApplicationInstalled(APP_BUNDLE_ID)) {
            // FIXME Demo APK location
            deviceDispatcher.system()
                    .installApplication("/myApp.apk");
        }
        deviceDispatcher.system()
                .activateApplication(APP_BUNDLE_ID);

//        ThreadUtils.sleep(Duration.ofSeconds(3));


    }

    protected void logMobileAppState(MobileDeviceDispatcher deviceDispatcher) {
        System.out.println("Application state: " + deviceDispatcher.system().getApplicationState(APP_BUNDLE_ID));
    }

}
