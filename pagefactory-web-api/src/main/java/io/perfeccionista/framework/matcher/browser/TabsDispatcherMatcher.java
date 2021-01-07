package io.perfeccionista.framework.matcher.browser;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import org.jetbrains.annotations.NotNull;

public interface TabsDispatcherMatcher extends PerfeccionistaMatcher<TabsDispatcher> {

    @Override
    void check(@NotNull TabsDispatcher actual);

}
