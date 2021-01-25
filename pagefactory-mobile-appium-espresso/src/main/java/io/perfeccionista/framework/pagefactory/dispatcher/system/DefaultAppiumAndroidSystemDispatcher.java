package io.perfeccionista.framework.pagefactory.dispatcher.system;

import io.appium.java_client.android.appmanagement.AndroidInstallApplicationOptions;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceSystemDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.ACTIVATE_MOBILE_APPLICATION_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.GET_MOBILE_APPLICATION_STATE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.INSTALL_MOBILE_APPLICATION_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.IS_MOBILE_APPLICATION_INSTALLED_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.REMOVE_MOBILE_APPLICATION_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.SEND_MOBILE_APPLICATION_TO_BACKGROUND_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.TERMINATE_MOBILE_APPLICATION_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.system.AppiumApplicationStateConverter.createMobileApplicationState;
import static io.perfeccionista.framework.value.Values.stringProcess;

public class DefaultAppiumAndroidSystemDispatcher implements MobileDeviceSystemDispatcher {

    protected final Environment environment;
    protected final AndroidEspressoDriver instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumAndroidSystemDispatcher(Environment environment, AndroidEspressoDriver instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public MobileApplicationState getApplicationState(@NotNull String bundleId) {
        return runCheck(environment, getterInvocation(GET_MOBILE_APPLICATION_STATE_METHOD), () ->
                exceptionMapper.map(() -> createMobileApplicationState(instance.queryAppState(stringProcess(bundleId))))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }

    @Override
    public boolean isApplicationInstalled(@NotNull String bundleId) {
        return runCheck(environment, getterInvocation(IS_MOBILE_APPLICATION_INSTALLED_METHOD), () ->
                exceptionMapper.map(() -> instance.isAppInstalled(stringProcess(bundleId)))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }

    @Override
    public DefaultAppiumAndroidSystemDispatcher installApplication(@NotNull String pathToApplication) {
        runCheck(environment, actionInvocation(INSTALL_MOBILE_APPLICATION_METHOD), () ->
                exceptionMapper.map(() -> instance.installApp(stringProcess(pathToApplication), new AndroidInstallApplicationOptions().withAllowTestPackagesEnabled()))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public DefaultAppiumAndroidSystemDispatcher removeApplication(@NotNull String bundleId) {
        runCheck(environment, actionInvocation(REMOVE_MOBILE_APPLICATION_METHOD), () ->
                exceptionMapper.map(() -> instance.removeApp(stringProcess(bundleId)))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public DefaultAppiumAndroidSystemDispatcher activateApplication(@NotNull String bundleId) {
        runCheck(environment, actionInvocation(ACTIVATE_MOBILE_APPLICATION_METHOD), () ->
                exceptionMapper.map(() -> instance.activateApp(stringProcess(bundleId)))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public DefaultAppiumAndroidSystemDispatcher terminateApplication(@NotNull String bundleId) {
        runCheck(environment, actionInvocation(TERMINATE_MOBILE_APPLICATION_METHOD), () ->
                exceptionMapper.map(() -> instance.terminateApp(stringProcess(bundleId)))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public DefaultAppiumAndroidSystemDispatcher sendApplicationToBackground(@NotNull Duration duration) {
        runCheck(environment, actionInvocation(SEND_MOBILE_APPLICATION_TO_BACKGROUND_METHOD, duration), () ->
                exceptionMapper.map(() -> instance.runAppInBackground(duration))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public DefaultAppiumAndroidSystemDispatcher should(@NotNull MobileDeviceSystemDispatcherMatcher matcher) {
        matcher.check(this);
        return this;
    }

}
