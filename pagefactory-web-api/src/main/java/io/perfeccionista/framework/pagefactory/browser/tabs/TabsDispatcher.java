package io.perfeccionista.framework.pagefactory.browser.tabs;

import io.perfeccionista.framework.matcher.browser.TabsDispatcherMatcher;
import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TabsDispatcher {

    int getTabCount();

    @NotNull String getActiveTabTitle();

    @NotNull String getActiveTabUrl();

    @NotNull List<String> getAllTabTitles();

    @NotNull List<String> getAllTabUrls();

    TabsDispatcher newTab();

    TabsDispatcher newTab(@NotNull String url);

    TabsDispatcher refresh();

    TabsDispatcher back();

    TabsDispatcher openUrl(@NotNull String url);

    TabsDispatcher closeActiveTab();

    TabsDispatcher closeTabWithTitle(@NotNull Value<String> tabTitle);

    TabsDispatcher closeTabWithUrl(@NotNull Value<String> tabUrl);

    TabsDispatcher switchToTabWithTitle(@NotNull Value<String> tabTitle);

    TabsDispatcher switchToTabWithUrl(@NotNull Value<String> tabUrl);

    TabsDispatcher should(@NotNull TabsDispatcherMatcher matcher);

    @NotNull String getActiveTabPageSource();

    @NotNull String getDescription();

}
