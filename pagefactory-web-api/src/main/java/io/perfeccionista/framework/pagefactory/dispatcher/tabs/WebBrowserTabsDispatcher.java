package io.perfeccionista.framework.pagefactory.dispatcher.tabs;

import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface WebBrowserTabsDispatcher {

    int getTabCount();

    @NotNull String getActiveTabTitle();

    @NotNull String getActiveTabUrl();

    @NotNull List<String> getAllTabTitles();

    @NotNull List<String> getAllTabUrls();

    WebBrowserTabsDispatcher newTab();

    WebBrowserTabsDispatcher newTab(@NotNull String url);

    WebBrowserTabsDispatcher refresh();

    WebBrowserTabsDispatcher back();

    WebBrowserTabsDispatcher openUrl(@NotNull String url);

    WebBrowserTabsDispatcher closeActiveTab();

    WebBrowserTabsDispatcher closeTabWithTitle(@NotNull Value<String> tabTitle);

    WebBrowserTabsDispatcher closeTabWithUrl(@NotNull Value<String> tabUrl);

    WebBrowserTabsDispatcher switchToTabWithTitle(@NotNull Value<String> tabTitle);

    WebBrowserTabsDispatcher switchToTabWithUrl(@NotNull Value<String> tabUrl);

    WebBrowserTabsDispatcher should(@NotNull WebBrowserTabsDispatcherMatcher matcher);

    @NotNull String getDescription();

}
