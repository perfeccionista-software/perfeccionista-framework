package io.perfeccionista.framework.pagefactory.dispatcher.screen;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.ResponseFormatIsNotValid;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceScreenDispatcherMatcher;
import io.perfeccionista.framework.measurements.Rotation3D;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.RESPONSE_FORMAT_NOT_VALID;
import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_ROTATE_ON_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_ROTATE_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.GET_SCREEN_ORIENTATION_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.GET_SCREEN_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.screen.AppiumRotationConverter.createAppiumScreenOrientation;
import static io.perfeccionista.framework.pagefactory.dispatcher.screen.AppiumRotationConverter.createDeviceRotation;
import static io.perfeccionista.framework.pagefactory.dispatcher.screen.AppiumRotationConverter.createPerfeccionistaScreenOrientation;

public class DefaultAppiumAndroidScreenDispatcher implements MobileDeviceScreenDispatcher {
//    protected static final Pattern SCREEN_SIZE_PATTERN = Pattern.compile("^Physical size: (?<WIDTH>\\d+)x(?<HEIGHT>\\d+)$");
    protected static final Pattern SCREEN_SIZE_PATTERN = Pattern.compile("^(?<WIDTH>\\d+)x(?<HEIGHT>\\d+)$");
    protected static final String separator = File.separator;

    protected final Environment environment;
    protected final AndroidEspressoDriver instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumAndroidScreenDispatcher(Environment environment, AndroidEspressoDriver instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public @NotNull MobileScreenBounds getScreenBounds() {
        return runCheck(environment, getterInvocation(GET_SCREEN_BOUNDS_METHOD), () -> exceptionMapper
                .map(() -> {
                    MobileScreenBounds mobileScreenBounds;
                    String deviceScreenSize = (String) instance.getSessionDetail("deviceScreenSize");
//                    String commandOutputResult = executeCommandAndGetOutput("$ANDROID_HOME" + separator + "platform-tools" + separator + "adb shell wm size");
                    Matcher screenSizeMatcher = SCREEN_SIZE_PATTERN.matcher(deviceScreenSize);
                    if (screenSizeMatcher.find()) {
                        double width = Double.parseDouble(screenSizeMatcher.group("WIDTH"));
                        double height = Double.parseDouble(screenSizeMatcher.group("HEIGHT"));
                        mobileScreenBounds = MobileScreenBounds.of(width, height);
                        Map<String, Map<String, Object>> systemBars = instance.getSystemBars();
                        Double topBarHeight = Double.valueOf((Long) systemBars.get("statusBar").get("height"));
                        Double bottomBarHeight = Double.valueOf((Long) systemBars.get("navigationBar").get("height"));
                        mobileScreenBounds.setTopBarHeight(topBarHeight)
                                .setBottomBarHeight(bottomBarHeight);
                        return mobileScreenBounds;
                    }
                    throw ResponseFormatIsNotValid.exception(RESPONSE_FORMAT_NOT_VALID.getMessage())
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Response", deviceScreenSize))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("RegExp", SCREEN_SIZE_PATTERN.pattern()));
                })
                .ifException(exception -> {
                    throw exception;
                })
                .getResult());
    }

//    @Override
//    public @NotNull Dimensions2D getViewportSize() {
//        return runCheck(environment, getterInvocation(GET_VIEWPORT_SIZE_METHOD), () -> exceptionMapper
//                .map(() -> {
//                    String commandOutputResult = executeCommandAndGetOutput("adb shell wm size");
//                    Matcher screenSizeMatcher = SCREEN_SIZE_PATTERN.matcher(commandOutputResult);
//                    if (screenSizeMatcher.find()) {
//                        double width = Double.parseDouble(screenSizeMatcher.group("WIDTH"));
//                        double height = Double.parseDouble(screenSizeMatcher.group("HEIGHT"));
//
//                        Map<String, Map<String, Object>> systemBars = instance.getSystemBars();
//
//
//
//                        return Dimensions2D.of(width, height);
//                    }
//                    throw ResponseFormatIsNotValid.exception(RESPONSE_FORMAT_NOT_VALID.getMessage())
//                            .addLastAttachmentEntry(StringAttachmentEntry.of("Response", commandOutputResult))
//                            .addLastAttachmentEntry(StringAttachmentEntry.of("RegExp", SCREEN_SIZE_PATTERN.pattern()));
//                })
//                .ifException(exception -> {
//                    throw exception;
//                })
//                .getResult());
//    }

    @Override
    public @NotNull ScreenOrientation getScreenOrientation() {
        return runCheck(environment, getterInvocation(GET_SCREEN_ORIENTATION_METHOD), () ->
                exceptionMapper.map(() -> createPerfeccionistaScreenOrientation(instance.getOrientation()))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }


    @Override
    public DefaultAppiumAndroidScreenDispatcher rotateOn(@NotNull Rotation3D rotation) {
        runCheck(environment, actionInvocation(DEVICE_ROTATE_ON_METHOD), () ->
                exceptionMapper.map(() -> instance.rotate(createDeviceRotation(rotation))))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public DefaultAppiumAndroidScreenDispatcher rotateTo(@NotNull ScreenOrientation screenOrientation) {
        runCheck(environment, actionInvocation(DEVICE_ROTATE_TO_METHOD), () ->
                exceptionMapper.map(() -> instance.rotate(createAppiumScreenOrientation(screenOrientation))))
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
