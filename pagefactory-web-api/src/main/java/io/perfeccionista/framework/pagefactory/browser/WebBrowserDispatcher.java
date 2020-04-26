package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.js.JsRepository;
import io.perfeccionista.framework.pagefactory.operations.DriverOperationExecutor;
import io.perfeccionista.framework.value.Value;

//TODO: Сюда нужно будет передавать экземпляр сервиса WebPageService
public interface WebBrowserDispatcher extends DriverInstance {

    WebPageContext getPageContext();

    // Browser
    WebBrowserDispatcher launch();

    WebBrowserDispatcher close();

    WebBrowserDispatcher setWindowSize(int width, int height);

    // Tabs
    WebBrowserDispatcher newTab();

    WebBrowserDispatcher newTab(String url);

    WebBrowserDispatcher refresh();

    WebBrowserDispatcher back();

    WebBrowserDispatcher openUrl(String url);

    WebBrowserDispatcher closeActiveTab();

    WebBrowserDispatcher closeTabWithTitle(Value<String> tabTitle);

    WebBrowserDispatcher closeTabWithUrl(Value<String> tabUrl);

    WebBrowserDispatcher switchToTabWithTitle(Value<String> tabTitle);

    WebBrowserDispatcher switchToTabWithUrl(Value<String> tabUrl);

    WebBrowserDispatcher activeTabShouldHaveTitle(Value<String> tabTitle);

    WebBrowserDispatcher activeTabShouldNotHaveTitle(Value<String> tabTitle);

    WebBrowserDispatcher activeTabShouldHaveUrl(Value<String> tabUrl);

    WebBrowserDispatcher activeTabShouldNotHaveUrl(Value<String> tabUrl);

    WebBrowserDispatcher shouldHaveTabWithTitle(Value<String> tabTitle);

    WebBrowserDispatcher shouldNotHaveTabWithTitle(Value<String> tabTitle);

    WebBrowserDispatcher shouldHaveTabWithUrl(Value<String> tabUrl);

    WebBrowserDispatcher shouldNotHaveTabWithUrl(Value<String> tabUrl);

    // Engine
    DriverOperationExecutor getDriverOperationExecutor();

    JsRepository getJsRepository();

}
