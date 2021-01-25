package io.perfeccionista.framework.matcher.dispatcher;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.preferences.MobileDevicePreferencesDispatcher;

public interface MobileDevicePreferencesDispatcherMatcher extends PerfeccionistaMatcher<MobileDevicePreferencesDispatcher> {

    @Override
    void check(MobileDevicePreferencesDispatcher actual);
}
