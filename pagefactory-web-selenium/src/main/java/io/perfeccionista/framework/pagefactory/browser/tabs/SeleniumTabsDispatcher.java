package io.perfeccionista.framework.pagefactory.browser.tabs;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.value.Value;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTabsDispatcher implements TabsDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final ExceptionMapper exceptionMapper;

    public SeleniumTabsDispatcher(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public TabsDispatcher newTab() {
        return null;
    }

    @Override
    public TabsDispatcher newTab(String url) {
        return null;
    }

    @Override
    public TabsDispatcher refresh() {
        return null;
    }

    @Override
    public TabsDispatcher back() {
        return null;
    }

    @Override
    public TabsDispatcher openUrl(String url) {
        // TODO: Обернуть тут ошибку: Некорректный URL с выводом некорректного значения, вместо штатной IncorrectArgument
        instance.get(url);
        return this;
    }

    @Override
    public TabsDispatcher closeActiveTab() {
        return null;
    }

    @Override
    public TabsDispatcher closeTabWithTitle(Value<String> tabTitle) {
        return null;
    }

    @Override
    public TabsDispatcher closeTabWithUrl(Value<String> tabUrl) {
        return null;
    }

    @Override
    public TabsDispatcher switchToTabWithTitle(Value<String> tabTitle) {
        return null;
    }

    @Override
    public TabsDispatcher switchToTabWithUrl(Value<String> tabUrl) {
        return null;
    }

    @Override
    public TabsDispatcher activeTabShouldHaveTitle(Value<String> tabTitle) {
        return null;
    }

    @Override
    public TabsDispatcher activeTabShouldNotHaveTitle(Value<String> tabTitle) {
        return null;
    }

    @Override
    public TabsDispatcher activeTabShouldHaveUrl(Value<String> tabUrl) {
        return null;
    }

    @Override
    public TabsDispatcher activeTabShouldNotHaveUrl(Value<String> tabUrl) {
        return null;
    }

    @Override
    public TabsDispatcher shouldHaveTabWithTitle(Value<String> tabTitle) {
        return null;
    }

    @Override
    public TabsDispatcher shouldNotHaveTabWithTitle(Value<String> tabTitle) {
        return null;
    }

    @Override
    public TabsDispatcher shouldHaveTabWithUrl(Value<String> tabUrl) {
        return null;
    }

    @Override
    public TabsDispatcher shouldNotHaveTabWithUrl(Value<String> tabUrl) {
        return null;
    }

}
