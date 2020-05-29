package io.perfeccionista.framework.pagefactory.browser.tabs;

import io.perfeccionista.framework.value.Value;

public interface TabsDispatcher {

    TabsDispatcher newTab();

    TabsDispatcher newTab(String url);

    TabsDispatcher refresh();

    TabsDispatcher back();

    TabsDispatcher openUrl(String url);

    TabsDispatcher closeActiveTab();

    TabsDispatcher closeTabWithTitle(Value<String> tabTitle);

    TabsDispatcher closeTabWithUrl(Value<String> tabUrl);

    TabsDispatcher switchToTabWithTitle(Value<String> tabTitle);

    TabsDispatcher switchToTabWithUrl(Value<String> tabUrl);

    TabsDispatcher activeTabShouldHaveTitle(Value<String> tabTitle);

    TabsDispatcher activeTabShouldNotHaveTitle(Value<String> tabTitle);

    TabsDispatcher activeTabShouldHaveUrl(Value<String> tabUrl);

    TabsDispatcher activeTabShouldNotHaveUrl(Value<String> tabUrl);

    TabsDispatcher shouldHaveTabWithTitle(Value<String> tabTitle);

    TabsDispatcher shouldNotHaveTabWithTitle(Value<String> tabTitle);

    TabsDispatcher shouldHaveTabWithUrl(Value<String> tabUrl);

    TabsDispatcher shouldNotHaveTabWithUrl(Value<String> tabUrl);

}
