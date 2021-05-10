package io.perfeccionista.framework.matcher.dispatcher;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.system.MobileDeviceSystemDispatcher;
import org.jetbrains.annotations.NotNull;

public interface MobileDeviceSystemDispatcherMatcher extends PerfeccionistaMatcher<MobileDeviceSystemDispatcher> {

    @Override
    void check(@NotNull MobileDeviceSystemDispatcher actual);

}
