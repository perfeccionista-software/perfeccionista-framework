package io.perfeccionista.framework.pagefactory.dispatcher;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.AppiumMobileDeviceInstanceNotStarted;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.exceptions.messages.PagefactoryMobileAppiumMessages;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.browser.MobileBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.context.DefaultMobilePageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.context.MobilePageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.MobileDeviceTimeouts;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.AppiumIosOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.MobileDeviceOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.keyboard.DefaultAppiumIosKeyboardDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.keyboard.MobileDeviceKeyboardDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.DefaultAppiumIosLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.MobileDeviceLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.DefaultAppiumIosPreferencesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.GsmCallAction;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.MobileDevicePreferencesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.DefaultAppiumIosScreenDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.MobileDeviceScreenDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.storage.DefaultAppiumIosStorageDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.storage.MobileDeviceStorageDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.system.DefaultAppiumIosSystemDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.system.MobileDeviceSystemDispatcher;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.IOS;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_CALL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_IS_LOCKED_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_LOCK_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_PERFORM_TOUCH_ID_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_ROTATE_ON_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_ROTATE_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_SEND_SMS_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_SHAKE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_UNLOCK_METHOD;

public abstract class AbstractAppiumIosDeviceDispatcher implements MobileDeviceDispatcher {

    protected final Environment environment;
    protected final DesiredCapabilities options;
    protected MobileDeviceTimeouts timeouts;
    protected MobileExceptionMapper exceptionMapper;

    protected IOSDriver<IOSElement> instance = null;

    protected MobileBrowserDispatcher browser = null;
    protected MobileDeviceKeyboardDispatcher keyboard = null;
    protected MobileDeviceLogsDispatcher logs = null;
    protected MobileDevicePreferencesDispatcher preferences = null;
    protected MobileDeviceScreenDispatcher screen = null;
    protected MobileDeviceStorageDispatcher storage = null;
    protected MobileDeviceSystemDispatcher system = null;

    protected MobileDeviceOperationExecutor executor = null;
    protected MobilePageContext context = null;

    protected boolean traceSearch = false;

    protected AbstractAppiumIosDeviceDispatcher(@NotNull Environment environment, @NotNull DesiredCapabilities options) {
        this.environment = environment;
        this.options = validate(options);
        this.exceptionMapper = new AppiumExceptionMapper();
        this.timeouts = new MobileDeviceTimeouts();
    }

    public AbstractAppiumIosDeviceDispatcher withTimeouts(@NotNull MobileDeviceTimeouts timeouts) {
        this.timeouts = timeouts;
        return this;
    }

    public AbstractAppiumIosDeviceDispatcher withExceptionMapper(@NotNull MobileExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
        return this;
    }

    public AbstractAppiumIosDeviceDispatcher withTraceSearch(boolean traceSearch) {
        this.traceSearch = traceSearch;
        return this;
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher close() {
        // TODO: Нужно проверить логику вручную
        if (this.instance != null) {
            try {
                this.instance.quit();
            } catch (WebDriverException e) {
                // Do nothing. Session is already closed.
            }
        }
        this.instance = null;
        this.context = null;
        this.executor = null;
        this.logs = null;
        // TODO: Если не завершился штатно, то прибить процесс вручную
        return this;
    }



    @Override
    public boolean isLocked() {
        return runCheck(environment, getterInvocation(DEVICE_IS_LOCKED_METHOD), () ->
                exceptionMapper.map((instance::isDeviceLocked))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher lock() {
        runCheck(environment, getterInvocation(DEVICE_LOCK_METHOD), () ->
                exceptionMapper.map((@NotNull Runnable) instance::lockDevice))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher unlock() {
        runCheck(environment, getterInvocation(DEVICE_UNLOCK_METHOD), () ->
                exceptionMapper.map(instance::unlockDevice))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher performTouchId(boolean success) {
        runCheck(environment, getterInvocation(DEVICE_PERFORM_TOUCH_ID_METHOD), () ->
                exceptionMapper.map(() -> instance.performTouchID(success)))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher shake() {
        runCheck(environment, getterInvocation(DEVICE_SHAKE_METHOD), () ->
                exceptionMapper.map(instance::shake))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher sendSms(@NotNull String phoneNumber, @NotNull String message) {
        runCheck(environment, getterInvocation(DEVICE_SEND_SMS_METHOD), () ->
                exceptionMapper.map(() -> {
                    throw new UnsupportedOperationException("Not implemented yet");
                }))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher call(@NotNull String phoneNumber, @NotNull GsmCallAction callAction) {
        runCheck(environment, getterInvocation(DEVICE_CALL_METHOD), () ->
                exceptionMapper.map(() -> {
                    throw new UnsupportedOperationException("Not implemented yet");
                }))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumIosDeviceDispatcher should(@NotNull MobileDeviceDispatcherMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileBrowserDispatcher browser() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.browser).orElseGet(() -> {
//            this.browser = new DefaultAppiumMobileBrowserDispatcher(environment, instance, exceptionMapper);
            return this.browser;
        });
    }

    @Override
    public MobileDeviceKeyboardDispatcher keyboard() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.keyboard).orElseGet(() -> {
            this.keyboard = new DefaultAppiumIosKeyboardDispatcher(environment, instance, exceptionMapper);
            return this.keyboard;
        });
    }

    @Override
    public MobileDeviceLogsDispatcher logs() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.logs).orElseGet(() -> {
            this.logs = new DefaultAppiumIosLogsDispatcher(environment, instance, exceptionMapper);
            return this.logs;
        });
    }

    @Override
    public MobileDevicePreferencesDispatcher preferences() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.preferences).orElseGet(() -> {
            this.preferences = new DefaultAppiumIosPreferencesDispatcher(environment, instance, exceptionMapper);
            return this.preferences;
        });
    }

    @Override
    public MobileDeviceScreenDispatcher screen() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.screen).orElseGet(() -> {
            this.screen = new DefaultAppiumIosScreenDispatcher(environment, instance, exceptionMapper);
            return this.screen;
        });
    }

    @Override
    public MobileDeviceStorageDispatcher storage() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.storage).orElseGet(() -> {
            this.storage = new DefaultAppiumIosStorageDispatcher(environment, instance, exceptionMapper);
            return this.storage;
        });
    }

    @Override
    public MobileDeviceSystemDispatcher system() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.system).orElseGet(() -> {
            this.system = new DefaultAppiumIosSystemDispatcher(environment, instance, exceptionMapper);
            return this.system;
        });
    }


    @Override
    public MobileDeviceOperationExecutor executor() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.executor).orElseGet(() -> {
            if (traceSearch) {
                this.executor = new AppiumIosOperationExecutor(environment, instance, exceptionMapper).withTraceSearch();
            } else {
                this.executor = new AppiumIosOperationExecutor(environment, instance, exceptionMapper);
            }
            return this.executor;
        });
    }

    @Override
    public MobilePageContext getMobilePageContext() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.context).orElseGet(() -> {
            this.context = new DefaultMobilePageContext(environment, this, exceptionMapper);
            return this.context;
        });
    }

    @Override
    public MobileExceptionMapper getExceptionMapper() {
        return exceptionMapper;
    }



    @Override
    public <T> T getInstance(Class<T> browserInstanceClass) {
        checkWebDriverInstance();
        // TODO: Check instanceof
        return (T) this.instance;
    }

    @Override
    public @NotNull DeviceType getDeviceType() {
        return IOS;
    }

    protected void checkWebDriverInstance() {
        if (this.instance == null) {
            throw AppiumMobileDeviceInstanceNotStarted.exception(PagefactoryMobileAppiumMessages.APPIUM_DEVICE_INSTANCE_NOT_STARTED.getMessage());
        }

    }

    protected DesiredCapabilities validate(DesiredCapabilities options) {
        // проверки некорректной установки свойств


        return options;
    }

    protected void setTimeoutCapabilities() {
        this.options.setCapability("newCommandTimeout", timeouts.getSessionTimeout()/1000);

        this.options.setCapability("launchTimeout", timeouts.getOperationTimeout());
        this.options.setCapability("screenshotWaitTimeout", timeouts.getOperationTimeout()/1000);
    }

    protected void setTimeouts() {
        checkWebDriverInstance();
        this.instance.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

}
