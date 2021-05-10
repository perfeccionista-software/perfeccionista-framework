package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.MobileDeviceIsLocked;
import io.perfeccionista.framework.exceptions.MobileDeviceIsUnlocked;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.MOBILE_DEVICE_LOCKED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.MOBILE_DEVICE_UNLOCKED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.MOBILE_DEVICE_SHOULD_BE_LOCKED_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.MOBILE_DEVICE_SHOULD_BE_UNLOCKED_METHOD;

public class MobileDeviceDispatcherShouldBeLocked implements MobileDeviceDispatcherMatcher {

    protected final boolean positive;

    public MobileDeviceDispatcherShouldBeLocked(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileDeviceDispatcher mobileDevice) {
        InvocationName invocationName = positive
                ? assertInvocation(MOBILE_DEVICE_SHOULD_BE_LOCKED_METHOD, this)
                : assertInvocation(MOBILE_DEVICE_SHOULD_BE_UNLOCKED_METHOD, this);

        runCheck(Environment.getCurrent(), invocationName,
                () -> {
                    boolean actualLockedState = mobileDevice.isLocked();
                    if (positive) {
                        shouldBeLocked(actualLockedState);
                    } else {
                        shouldBeUnlocked(actualLockedState);
                    }
                });
    }

    protected void shouldBeLocked(boolean actualLockedState) {
        if (!actualLockedState) {
            throw MobileDeviceIsUnlocked.assertionError(MOBILE_DEVICE_UNLOCKED.getMessage())
                    .setProcessed(true);
        }
    }

    protected void shouldBeUnlocked(boolean actualLockedState) {
        if (actualLockedState) {
            throw MobileDeviceIsLocked.assertionError(MOBILE_DEVICE_LOCKED.getMessage())
                    .setProcessed(true);
        }
    }

}
