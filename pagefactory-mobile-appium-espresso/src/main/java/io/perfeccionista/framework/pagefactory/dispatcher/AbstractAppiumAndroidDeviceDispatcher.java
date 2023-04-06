package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.AppiumMobileDeviceInstanceNotStarted;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.browser.DefaultAppiumMobileBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.browser.MobileBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.context.DefaultMobilePageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.context.MobilePageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.MobileDeviceTimeouts;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import io.perfeccionista.framework.pagefactory.dispatcher.keyboard.DefaultAppiumAndroidKeyboardDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.keyboard.MobileDeviceKeyboardDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.DefaultAppiumAndroidLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.DefaultAppiumAndroidPreferencesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.GsmCallAction;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.MobileDevicePreferencesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.DefaultAppiumAndroidOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.MobileDeviceOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.MobileDeviceLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.DefaultAppiumAndroidScreenDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.MobileDeviceScreenDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.storage.DefaultAppiumAndroidStorageDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.storage.MobileDeviceStorageDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.system.DefaultAppiumAndroidSystemDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.system.MobileDeviceSystemDispatcher;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static io.perfeccionista.framework.exceptions.messages.PagefactoryMobileAppiumMessages.APPIUM_DEVICE_INSTANCE_NOT_STARTED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.ANDROID;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_CALL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_IS_LOCKED_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_LOCK_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_PERFORM_TOUCH_ID_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_SEND_SMS_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_SHAKE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceActionNames.DEVICE_UNLOCK_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.preferences.GsmCallActionConverter.createAppiumGsmCallAction;

public abstract class AbstractAppiumAndroidDeviceDispatcher implements MobileDeviceDispatcher {

    protected final Environment environment;
    protected final DesiredCapabilities options;
    protected MobileDeviceTimeouts timeouts;
    protected MobileExceptionMapper exceptionMapper;

    protected AndroidEspressoDriver instance = null;

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

    protected AbstractAppiumAndroidDeviceDispatcher(@NotNull Environment environment, @NotNull DesiredCapabilities options) {
        this.environment = environment;
        this.options = validate(options);
        this.exceptionMapper = new AppiumExceptionMapper();
        this.timeouts = new MobileDeviceTimeouts();
    }

    public AbstractAppiumAndroidDeviceDispatcher withTimeouts(@NotNull MobileDeviceTimeouts timeouts) {
        this.timeouts = timeouts;
        return this;
    }

    public AbstractAppiumAndroidDeviceDispatcher withExceptionMapper(@NotNull MobileExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
        return this;
    }

    public AbstractAppiumAndroidDeviceDispatcher withTraceSearch(boolean traceSearch) {
        this.traceSearch = traceSearch;
        return this;
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher close() {
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
        return repeatInvocation(getterInvocation(DEVICE_IS_LOCKED_METHOD), () ->
                exceptionMapper.map((instance::isDeviceLocked))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher lock() {
        repeatInvocation(getterInvocation(DEVICE_LOCK_METHOD), () ->
                exceptionMapper.map((@NotNull Runnable) instance::lockDevice))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher unlock() {
        repeatInvocation(getterInvocation(DEVICE_UNLOCK_METHOD), () ->
                exceptionMapper.map(instance::unlockDevice))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher performTouchId(boolean success) {
        repeatInvocation(getterInvocation(DEVICE_PERFORM_TOUCH_ID_METHOD), () ->
                exceptionMapper.map(() -> {
                    throw new UnsupportedOperationException("Not implemented yet");
//                    if (success) {
//                        instance.fingerPrint(0);
//                    } else {
//                        instance.fingerPrint(11);
//                    }
                }))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher shake() {
        repeatInvocation(getterInvocation(DEVICE_SHAKE_METHOD), () ->
                exceptionMapper.map(() -> {
                    // do nothing for Android
                }))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher sendSms(@NotNull String phoneNumber, @NotNull String message) {
        repeatInvocation(getterInvocation(DEVICE_SEND_SMS_METHOD), () ->
                exceptionMapper.map(() -> instance.sendSMS(phoneNumber, message)))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher call(@NotNull String phoneNumber, @NotNull GsmCallAction callAction) {
        repeatInvocation(getterInvocation(DEVICE_CALL_METHOD), () ->
                exceptionMapper.map(() -> instance.makeGsmCall(phoneNumber, createAppiumGsmCallAction(callAction))))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

    @Override
    public AbstractAppiumAndroidDeviceDispatcher should(@NotNull MobileDeviceDispatcherMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileBrowserDispatcher browser() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.browser).orElseGet(() -> {
            this.browser = new DefaultAppiumMobileBrowserDispatcher(environment, instance, exceptionMapper);
            return this.browser;
        });
    }

    @Override
    public MobileDeviceKeyboardDispatcher keyboard() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.keyboard).orElseGet(() -> {
            this.keyboard = new DefaultAppiumAndroidKeyboardDispatcher(environment, instance, exceptionMapper);
            return this.keyboard;
        });
    }

    @Override
    public MobileDeviceLogsDispatcher logs() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.logs).orElseGet(() -> {
            this.logs = new DefaultAppiumAndroidLogsDispatcher(environment, instance, exceptionMapper);
            return this.logs;
        });
    }

    @Override
    public MobileDevicePreferencesDispatcher preferences() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.preferences).orElseGet(() -> {
            this.preferences = new DefaultAppiumAndroidPreferencesDispatcher(environment, instance, exceptionMapper);
            return this.preferences;
        });
    }

    @Override
    public MobileDeviceScreenDispatcher screen() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.screen).orElseGet(() -> {
            this.screen = new DefaultAppiumAndroidScreenDispatcher(environment, instance, exceptionMapper);
            return this.screen;
        });
    }

    @Override
    public MobileDeviceStorageDispatcher storage() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.storage).orElseGet(() -> {
            this.storage = new DefaultAppiumAndroidStorageDispatcher(environment, instance, exceptionMapper);
            return this.storage;
        });
    }

    @Override
    public MobileDeviceSystemDispatcher system() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.system).orElseGet(() -> {
            this.system = new DefaultAppiumAndroidSystemDispatcher(environment, instance, exceptionMapper);
            return this.system;
        });
    }


    @Override
    public MobileDeviceOperationExecutor executor() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.executor).orElseGet(() -> {
            if (traceSearch) {
                this.executor = new DefaultAppiumAndroidOperationExecutor(environment, instance, exceptionMapper).withTraceSearch();
            } else {
                this.executor = new DefaultAppiumAndroidOperationExecutor(environment, instance, exceptionMapper);
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
        return ANDROID;
    }

    protected void checkWebDriverInstance() {
        if (this.instance == null) {
            throw AppiumMobileDeviceInstanceNotStarted.exception(APPIUM_DEVICE_INSTANCE_NOT_STARTED.getMessage());
        }

    }

    protected DesiredCapabilities validate(DesiredCapabilities options) {
        // проверки некорректной установки свойств


        return options;
    }

    protected void setTimeoutCapabilities() {
        this.options.setCapability("newCommandTimeout", timeouts.getSessionTimeout()/1000);
        this.options.setCapability("appWaitDuration", timeouts.getOperationTimeout());
        this.options.setCapability("adbExecTimeout", timeouts.getOperationTimeout());
        this.options.setCapability("deviceReadyTimeout", timeouts.getOperationTimeout());
        this.options.setCapability("androidDeviceReadyTimeout", timeouts.getOperationTimeout());
        this.options.setCapability("androidInstallTimeout", timeouts.getOperationTimeout());
        this.options.setCapability("autoWebviewTimeout", timeouts.getOperationTimeout());
    }

    protected void setTimeouts() {
        checkWebDriverInstance();
        this.instance.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

}
