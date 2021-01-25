package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstanceNotStarted;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserTimeouts;
import io.perfeccionista.framework.pagefactory.dispatcher.context.DefaultWebPageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.cookies.WebBrowserCookiesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.cookies.SeleniumWebBrowserCookiesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.WebBrowserOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.SeleniumWebBrowserOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.WebBrowserLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.SeleniumWebBrowserLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.SeleniumWebBrowserTabsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.WebDriverType;
import io.perfeccionista.framework.pagefactory.dispatcher.window.SeleniumWebBrowserWindowDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.window.WebBrowserWindowDispatcher;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_DRIVER_INSTANCE_NOT_STARTED;

// TODO: Добавить обработку процесса (старт, отслеживание завершения, логирование)
public abstract class AbstractWebBrowserSeleniumDispatcher<T extends RemoteWebDriver, C extends MutableCapabilities> implements WebBrowserDispatcher {

    protected final Environment environment;
    protected final WebDriverType<T, C> webDriverType;
    protected WebBrowserTimeouts timeouts;
    protected WebExceptionMapper exceptionMapper;
    protected C options;

    protected T instance = null;

    protected WebPageContext context = null;
    protected WebBrowserOperationExecutor executor = null;
    protected WebBrowserCookiesDispatcher cookies = null;
    protected WebBrowserWindowDispatcher window = null;
    protected WebBrowserTabsDispatcher tabs = null;
    protected WebBrowserLogsDispatcher logs = null;

    protected boolean traceSearch = false;

    protected AbstractWebBrowserSeleniumDispatcher(Environment environment, WebDriverType<T, C> webDriverType) {
        this.environment = environment;
        this.webDriverType = webDriverType;
        this.exceptionMapper = new SeleniumExceptionMapper();
        this.options = webDriverType.getDefaultCapabilities();
        this.timeouts = new WebBrowserTimeouts();
    }

    public AbstractWebBrowserSeleniumDispatcher<T, C> withOptions(@NotNull C options) {
        this.options = options;
        return this;
    }

    public AbstractWebBrowserSeleniumDispatcher<T, C> withTimeouts(@NotNull WebBrowserTimeouts timeouts) {
        this.timeouts = timeouts;
        return this;
    }

    public AbstractWebBrowserSeleniumDispatcher<T, C> withExceptionMapper(@NotNull WebExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
        return this;
    }

    public AbstractWebBrowserSeleniumDispatcher<T, C> withTraceSearch(boolean traceSearch) {
        this.traceSearch = traceSearch;
        return this;
    }

    @Override
    public WebBrowserDispatcher close() {
        if (this.instance != null) {
            // TODO: Обернуть в маппер ошибок
            if (this.instance.getSessionId() != null) {
                this.instance.getWindowHandles().forEach(handle -> this.instance.switchTo().window(handle).close());
            }
            try {
                this.instance.quit();
            } catch (WebDriverException e) {
                // Do nothing. Session is already closed.
            }
        }
        this.instance = null;
        this.context = null;
        this.executor = null;
        this.cookies = null;
        this.window = null;
        this.tabs = null;
        this.logs = null;
        // TODO: Если не завершился штатно, то прибить процесс вручную
        return this;
    }

    @Override
    public WebExceptionMapper getExceptionMapper() {
        return exceptionMapper;
    }

    @Override
    public WebPageContext getWebPageContext() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.context).orElseGet(() -> {
            this.context = new DefaultWebPageContext(environment, this, exceptionMapper);
            return this.context;
        });
    }

    @Override
    public WebBrowserOperationExecutor executor() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.executor).orElseGet(() -> {
            if (traceSearch) {
                this.executor = new SeleniumWebBrowserOperationExecutor(environment, instance, exceptionMapper).withJsLogs();
            } else {
                this.executor = new SeleniumWebBrowserOperationExecutor(environment, instance, exceptionMapper);
            }
            return this.executor;
        });
    }

    @Override
    public WebBrowserCookiesDispatcher cookies() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.cookies).orElseGet(() -> {
            this.cookies = new SeleniumWebBrowserCookiesDispatcher(environment, instance, exceptionMapper);
            return this.cookies;
        });
    }

    @Override
    public WebBrowserWindowDispatcher window() {
        checkWebDriverInstance();
        // TODO: Depends on tabs (Пользователь должен открыть вкладку перед тем как )
        return Optional.ofNullable(this.window).orElseGet(() -> {
            // TODO: Не передавать сюда экзекьютор, а создавать при необходимости, проверяя, что вкладка открыта
            this.window = new SeleniumWebBrowserWindowDispatcher(environment, instance, exceptionMapper, executor());
            return this.window;
        });
    }

    @Override
    public WebBrowserTabsDispatcher tabs() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.tabs).orElseGet(() -> {
            this.tabs = new SeleniumWebBrowserTabsDispatcher(environment, instance, exceptionMapper);
            return this.tabs;
        });
    }

    @Override
    public WebBrowserLogsDispatcher logs() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.logs).orElseGet(() -> {
            this.logs = new SeleniumWebBrowserLogsDispatcher(environment, instance, exceptionMapper);
            return this.logs;
        });
    }

    @Override
    public <T> T getInstance(Class<T> browserInstanceClass) {
        checkWebDriverInstance();
        // TODO: Check instanceof
        return (T) this.instance;
    }

    protected void checkWebDriverInstance() {
        if (this.instance == null) {
            throw SeleniumWebDriverInstanceNotStarted.exception(WEB_DRIVER_INSTANCE_NOT_STARTED.getMessage());
        }

    }

    protected void setTimeouts() {
        checkWebDriverInstance();
        this.instance.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        this.instance.manage().timeouts().pageLoadTimeout(this.timeouts.getOperationTimeout(), TimeUnit.MILLISECONDS);
        this.instance.manage().timeouts().setScriptTimeout(this.timeouts.getOperationTimeout(), TimeUnit.MILLISECONDS);
    }

}
