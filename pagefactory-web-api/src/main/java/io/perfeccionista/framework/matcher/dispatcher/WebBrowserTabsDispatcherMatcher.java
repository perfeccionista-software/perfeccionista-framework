package io.perfeccionista.framework.matcher.dispatcher;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserTabsDispatcherMatcher extends PerfeccionistaMatcher<WebBrowserTabsDispatcher> {

    @Override
    void check(@NotNull WebBrowserTabsDispatcher actual);

}
