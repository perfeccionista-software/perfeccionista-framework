package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.MobileDeviceOrientationNotMatch;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceScreenDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.MobileDeviceScreenDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.ScreenOrientation;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.MOBILE_DEVICE_ORIENTATION_NOT_MATCH;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.MOBILE_DEVICE_SHOULD_HAVE_SCREEN_ORIENTATION_METHOD;

public class MobileDeviceShouldHaveScreenOrientation implements MobileDeviceScreenDispatcherMatcher {

    protected final ScreenOrientation expectedScreenOrientation;

    public MobileDeviceShouldHaveScreenOrientation(@NotNull ScreenOrientation expectedScreenOrientation) {
        this.expectedScreenOrientation = expectedScreenOrientation;
    }

    @Override
    public void check(@NotNull MobileDeviceScreenDispatcher mobileDevice) {
        InvocationName invocationName = assertInvocation(MOBILE_DEVICE_SHOULD_HAVE_SCREEN_ORIENTATION_METHOD, this);

        runCheck(Environment.getCurrent(), invocationName,
                () -> {
                    ScreenOrientation actualOrientation = mobileDevice.getScreenOrientation();
                    if (expectedScreenOrientation != actualOrientation) {
                        throw MobileDeviceOrientationNotMatch.assertionError(MOBILE_DEVICE_ORIENTATION_NOT_MATCH
                                .getMessage(actualOrientation, expectedScreenOrientation));
                    }
                });
    }

}
