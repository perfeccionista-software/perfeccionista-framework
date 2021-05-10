package io.perfeccionista.framework.matcher.dispatcher;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.MobileDeviceScreenDispatcher;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceScreenDispatcherMatcher extends PerfeccionistaMatcher<MobileDeviceScreenDispatcher> {

    @Override
    void check(@NotNull MobileDeviceScreenDispatcher actual);

}
