package io.perfeccionista.framework.pagefactory.browser.dispatcher;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstanceNotStarted;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebDriverTimeouts;
import io.perfeccionista.framework.pagefactory.browser.context.SeleniumWebPageContext;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.browser.cookies.CookiesDispatcher;
import io.perfeccionista.framework.pagefactory.browser.cookies.SeleniumCookiesDispatcher;
import io.perfeccionista.framework.pagefactory.browser.executor.OperationExecutor;
import io.perfeccionista.framework.pagefactory.browser.executor.SeleniumOperationExecutor;
import io.perfeccionista.framework.pagefactory.browser.logs.LogsDispatcher;
import io.perfeccionista.framework.pagefactory.browser.logs.SeleniumLogsDispatcher;
import io.perfeccionista.framework.pagefactory.browser.tabs.SeleniumTabsDispatcher;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.WebDriverType;
import io.perfeccionista.framework.pagefactory.browser.window.SeleniumWindowDispatcher;
import io.perfeccionista.framework.pagefactory.browser.window.WindowDispatcher;
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
    protected WebDriverTimeouts timeouts;
    protected ExceptionMapper exceptionMapper;
    protected C options;

    protected T instance = null;

    protected WebPageContext context = null;
    protected OperationExecutor executor = null;
    protected CookiesDispatcher cookies = null;
    protected WindowDispatcher window = null;
    protected TabsDispatcher tabs = null;
    protected LogsDispatcher logs = null;

    protected boolean traceSearch = false;

    public AbstractWebBrowserSeleniumDispatcher(Environment environment, WebDriverType<T, C> webDriverType) {
        this.environment = environment;
        this.webDriverType = webDriverType;
        this.exceptionMapper = new SeleniumExceptionMapper();
        this.options = webDriverType.getDefaultCapabilities();
        this.timeouts = new WebDriverTimeouts();
    }

    public AbstractWebBrowserSeleniumDispatcher<T, C> withOptions(@NotNull C options) {
        this.options = options;
        return this;
    }

    public AbstractWebBrowserSeleniumDispatcher<T, C> withTimeouts(@NotNull WebDriverTimeouts timeouts) {
        this.timeouts = timeouts;
        return this;
    }

    public AbstractWebBrowserSeleniumDispatcher<T, C> withExceptionMapper(@NotNull ExceptionMapper exceptionMapper) {
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
        // TODO: Если не завершился штатно, то прибить процесс вручную
        return this;
    }

    @Override
    public ExceptionMapper getExceptionMapper() {
        return exceptionMapper;
    }

    @Override
    public WebPageContext getPageContext() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.context).orElseGet(() -> {
            this.context = new SeleniumWebPageContext(environment, this, exceptionMapper);
            return this.context;
        });
    }

    @Override
    public OperationExecutor executor() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.executor).orElseGet(() -> {
            if (traceSearch) {
                this.executor = new SeleniumOperationExecutor(environment, instance, exceptionMapper).withJsLogs();
            } else {
                this.executor = new SeleniumOperationExecutor(environment, instance, exceptionMapper);
            }
            return this.executor;
        });
    }

    @Override
    public CookiesDispatcher cookies() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.cookies).orElseGet(() -> {
            this.cookies = new SeleniumCookiesDispatcher(environment, instance, exceptionMapper);
            return this.cookies;
        });
    }

    @Override
    public WindowDispatcher window() {
        checkWebDriverInstance();
        // TODO: Depends on tabs (Пользователь должен открыть вкладку перед тем как )
        return Optional.ofNullable(this.window).orElseGet(() -> {
            // TODO: Не передавать сюда экзекьютор, а создавать при необходимости, проверяя, что вкладка открыта
            this.window = new SeleniumWindowDispatcher(environment, instance, exceptionMapper, executor());
            return this.window;
        });
    }

    @Override
    public TabsDispatcher tabs() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.tabs).orElseGet(() -> {
            this.tabs = new SeleniumTabsDispatcher(environment, instance, exceptionMapper);
            return this.tabs;
        });
    }

    @Override
    public LogsDispatcher logs() {
        checkWebDriverInstance();
        return Optional.ofNullable(this.logs).orElseGet(() -> {
            this.logs = new SeleniumLogsDispatcher(environment, instance, exceptionMapper);
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
        this.instance.manage().timeouts().implicitlyWait(this.timeouts.getImplicitlyTimeout(), TimeUnit.MILLISECONDS);
        this.instance.manage().timeouts().pageLoadTimeout(this.timeouts.getPageLoadTimeout(), TimeUnit.MILLISECONDS);
        this.instance.manage().timeouts().setScriptTimeout(this.timeouts.getScriptTimeout(), TimeUnit.MILLISECONDS);
    }

}
