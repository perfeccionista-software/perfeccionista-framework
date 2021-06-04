package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.exceptions.MobileApplicationIsInstalled;
import io.perfeccionista.framework.exceptions.MobileApplicationIsNotInstalled;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceSystemDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.system.MobileDeviceSystemDispatcher;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.MOBILE_APPLICATION_INSTALLED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.MOBILE_APPLICATION_NOT_INSTALLED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.MOBILE_APPLICATION_SHOULD_BE_INSTALLED_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.MOBILE_APPLICATION_SHOULD_NOT_BE_INSTALLED_METHOD;

public class MobileApplicationShouldBeInstalled implements MobileDeviceSystemDispatcherMatcher {

    protected final String bundleId;
    protected final boolean positive;

    public MobileApplicationShouldBeInstalled(@NotNull String bundleId, boolean positive) {
        this.bundleId = bundleId;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileDeviceSystemDispatcher mobileDeviceSystemDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(MOBILE_APPLICATION_SHOULD_BE_INSTALLED_METHOD, this)
                : assertInvocation(MOBILE_APPLICATION_SHOULD_NOT_BE_INSTALLED_METHOD, this);

        runCheck(invocationName,
                () -> {
                    boolean actualState = mobileDeviceSystemDispatcher.isApplicationInstalled(bundleId);
                    if (positive) {
                        shouldBeInstalled(actualState);
                    } else {
                        shouldNotBeInstalled(actualState);
                    }
                });
    }

    protected void shouldBeInstalled(boolean actualState) {
        if (!actualState) {
            throw MobileApplicationIsNotInstalled.assertionError(MOBILE_APPLICATION_NOT_INSTALLED.getMessage(bundleId))
                    .setProcessed(true);
        }
    }

    protected void shouldNotBeInstalled(boolean actualState) {
        if (actualState) {
            throw MobileApplicationIsInstalled.assertionError(MOBILE_APPLICATION_INSTALLED.getMessage(bundleId))
                    .setProcessed(true);
        }
    }

}
