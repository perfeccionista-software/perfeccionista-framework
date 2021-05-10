package io.perfeccionista.framework.pagefactory.dispatcher.system;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceSystemDispatcherMatcher;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class DefaultAppiumIosSystemDispatcher implements MobileDeviceSystemDispatcher {

    protected final Environment environment;
    protected final AppiumDriver<?> instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumIosSystemDispatcher(Environment environment, AppiumDriver<?> instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public boolean isApplicationInstalled(@NotNull String bundleId) {
        return false;
    }

    @Override
    public MobileApplicationState getApplicationState(@NotNull String bundleId) {
        return null;
    }

    @Override
    public MobileDeviceSystemDispatcher installApplication(@NotNull String pathToApplication) {
        return null;
    }

    @Override
    public MobileDeviceSystemDispatcher removeApplication(@NotNull String bundleId) {
        return null;
    }

    @Override
    public MobileDeviceSystemDispatcher activateApplication(@NotNull String bundleId) {
        return null;
    }

    @Override
    public MobileDeviceSystemDispatcher terminateApplication(@NotNull String bundleId) {
        return null;
    }

    @Override
    public MobileDeviceSystemDispatcher sendApplicationToBackground(@NotNull Duration duration) {
        return null;
    }

    @Override
    public MobileDeviceSystemDispatcher should(@NotNull MobileDeviceSystemDispatcherMatcher matcher) {
        return null;
    }

}
