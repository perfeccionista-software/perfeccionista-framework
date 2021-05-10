package io.perfeccionista.framework.pagefactory.dispatcher.screen;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceScreenDispatcherMatcher;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Rotation3D;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_ROTATE_ON_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_ROTATE_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.screen.AppiumRotationConverter.createAppiumScreenOrientation;
import static io.perfeccionista.framework.pagefactory.dispatcher.screen.AppiumRotationConverter.createDeviceRotation;

public class DefaultAppiumIosScreenDispatcher implements MobileDeviceScreenDispatcher {

    protected final Environment environment;
    protected final AppiumDriver<?> instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumIosScreenDispatcher(Environment environment, AppiumDriver<?> instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public @NotNull MobileScreenBounds getScreenBounds() {
        return null;
    }

    @Override
    public @NotNull ScreenOrientation getScreenOrientation() {
        return null;
    }

    @Override
    public DefaultAppiumIosScreenDispatcher rotateOn(@NotNull Rotation3D rotation) {
        runCheck(environment, getterInvocation(DEVICE_ROTATE_ON_METHOD), () ->
                exceptionMapper.map(() -> instance.rotate(AppiumRotationConverter.createDeviceRotation(rotation))))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public DefaultAppiumIosScreenDispatcher rotateTo(@NotNull ScreenOrientation screenOrientation) {
        runCheck(environment, getterInvocation(DEVICE_ROTATE_TO_METHOD), () ->
                exceptionMapper.map(() -> instance.rotate(AppiumRotationConverter.createAppiumScreenOrientation(screenOrientation))))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public @NotNull MobileDeviceScreenDispatcher startRecording() {
        return null;
    }

    @Override
    public @NotNull MobileDeviceScreenDispatcher stopRecording() {
        return null;
    }

    @Override
    public @NotNull Screenshot getPageScreenshot() {
        return null;
    }

    @Override
    public Optional<Integer> getDensity() {
        return Optional.empty();
    }

    @Override
    public MobileDeviceScreenDispatcher should(@NotNull MobileDeviceScreenDispatcherMatcher matcher) {
        return null;
    }

}
