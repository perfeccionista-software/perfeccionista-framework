package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.browser.MobileBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.context.MobilePageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.keyboard.MobileDeviceKeyboardDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.GsmCallAction;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.MobileDevicePreferencesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.MobileDeviceOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.MobileDeviceLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.MobileDeviceScreenDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.storage.MobileDeviceStorageDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.system.MobileDeviceSystemDispatcher;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceDispatcher {

    @API(status = Status.STABLE)
    MobileDeviceDispatcher launch();

    @API(status = Status.STABLE)
    MobileDeviceDispatcher close();

    boolean isLocked();

    MobileDeviceDispatcher lock();

    MobileDeviceDispatcher unlock();

    MobileDeviceDispatcher performTouchId(boolean success);

    MobileDeviceDispatcher shake();

    MobileDeviceDispatcher sendSms(@NotNull String phoneNumber, @NotNull String message);

    MobileDeviceDispatcher call(@NotNull String phoneNumber, @NotNull GsmCallAction callAction);

    MobileDeviceDispatcher should(@NotNull MobileDeviceDispatcherMatcher matcher);


    MobileBrowserDispatcher browser();

    MobileDeviceKeyboardDispatcher keyboard();

    @API(status = Status.STABLE)
    MobileDeviceLogsDispatcher logs();

    MobileDevicePreferencesDispatcher preferences();

    @API(status = Status.STABLE)
    MobileDeviceScreenDispatcher screen();

    MobileDeviceStorageDispatcher storage();

    MobileDeviceSystemDispatcher system();


    @API(status = Status.STABLE)
    MobileDeviceOperationExecutor executor();

    @API(status = Status.STABLE)
    MobilePageContext getMobilePageContext();

    @API(status = Status.EXPERIMENTAL)
    MobileExceptionMapper getExceptionMapper();

    @API(status = Status.INTERNAL)
    <T> T getInstance(Class<T> deviceInstanceClass);

    @API(status = Status.STABLE)
    @NotNull DeviceType getDeviceType();

}
