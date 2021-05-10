package io.perfeccionista.framework.pagefactory.dispatcher.context;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.PageNotInitialized;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.limiter.WebContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ACTIVE_PAGE_NOT_INITIALIZED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_ACTIVE_TAB_PAGE_SOURCE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.context.ContextStrategy.IMMUTABLE;

public class DefaultWebPageContext implements WebPageContext {

    protected final Environment environment;
    protected final WebBrowserDispatcher dispatcher;
    protected final WebExceptionMapper exceptionMapper;

    protected final Deque<WebContextLimiter<?>> contextLimiters = new ArrayDeque<>();

    protected WebPage activeWebPage = null;

    public DefaultWebPageContext(@NotNull Environment environment,
                                 @NotNull WebBrowserDispatcher dispatcher,
                                 @NotNull WebExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.dispatcher = dispatcher;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public <T extends WebParentElement> WebPageContext execute(@NotNull Consumer<T> contextConsumer) {
        getContexts(contextLimiters).map(webParentElement -> (T) webParentElement)
                .forEachOrdered(contextConsumer);
        return this;
    }

    @Override
    public <T extends WebParentElement> WebPageContext execute(@NotNull Consumer<T> contextConsumer,
                                                               @NotNull WebContextLimiter<?>... limiterSequence) {
        Deque<WebContextLimiter<?>> processedContextLimiters = new ArrayDeque<>(contextLimiters);
        for (WebContextLimiter<?> limiter : limiterSequence) {
            processedContextLimiters.addLast(limiter);
        }
        getContexts(processedContextLimiters).map(webParentElement -> (T) webParentElement)
                .forEachOrdered(contextConsumer);
        return this;
    }

    @Override
    public WebPageContext addContextLimiter(@NotNull WebContextLimiter<?> limiter) {
        contextLimiters.addLast(limiter);
        return this;
    }

    @Override
    public WebPageContext removeLastContextLimiter() {
        contextLimiters.removeLast();
        return this;
    }

    @Override
    public WebPageContext removeContextLimiters() {
        contextLimiters.clear();
        return this;
    }

    @Override
    public <T extends WebPage> @NotNull T getPage(@NotNull Class<T> pageClass) {
        T pageInstance = environment.getService(WebPageService.class).getPageInstanceByClass(pageClass);
        pageInstance.setWebBrowserDispatcher(dispatcher);
        pageInstance.setEnvironment(environment);
        activeWebPage = pageInstance;
        pageInstance.validatePageOpen();
        return pageInstance;
    }

    @Override
    public @NotNull WebPage getPage(@NotNull String pageName) {
        WebPage pageInstance = environment.getService(WebPageService.class).getPageInstanceByName(pageName);
        pageInstance.setWebBrowserDispatcher(dispatcher);
        pageInstance.setEnvironment(environment);
        activeWebPage = pageInstance;
        pageInstance.validatePageOpen();
        return pageInstance;
    }

    @Override
    public @NotNull WebPage getActivePage() {
        if (Objects.nonNull(activeWebPage)) {
            activeWebPage.validatePageOpen();
            return activeWebPage;
        }
        throw PageNotInitialized.exception(ACTIVE_PAGE_NOT_INITIALIZED.getMessage());
    }

    @Override
    public WebPageContext usePage(@NotNull String pageName) {
        getPage(pageName);
        return this;
    }

    @Override
    public WebPageContext usePage(@NotNull Class<? extends WebPage> pageClass) {
        getPage(pageClass);
        return this;
    }

    @Override
    public @NotNull String getPageSource() {
        return runCheck(environment, getterInvocation(BROWSER_GET_ACTIVE_TAB_PAGE_SOURCE_METHOD), () ->
                exceptionMapper.map(() -> {
                    RemoteWebDriver instance = dispatcher.getInstance(RemoteWebDriver.class);
                    return instance.getPageSource();
                }))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult();
    }

    protected @NotNull Stream<WebParentElement> getContexts(Deque<WebContextLimiter<?>> processedContextLimiters) {
        Stream<WebParentElement> activeContexts = Stream.of(getActivePage());
        if (processedContextLimiters.isEmpty()) {
            return activeContexts;
        }
        for (WebContextLimiter<?> processedContextLimiter : processedContextLimiters) {
            activeContexts = (Stream<WebParentElement>) processedContextLimiter.getContexts(activeContexts);
        }
        return activeContexts;
    }

}
