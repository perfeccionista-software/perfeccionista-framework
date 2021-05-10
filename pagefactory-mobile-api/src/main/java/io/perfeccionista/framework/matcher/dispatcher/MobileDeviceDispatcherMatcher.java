package io.perfeccionista.framework.matcher.dispatcher;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.device.MobileDevice;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceDispatcherMatcher extends PerfeccionistaMatcher<MobileDeviceDispatcher> {

    @Override
    void check(@NotNull MobileDeviceDispatcher actual);

}
