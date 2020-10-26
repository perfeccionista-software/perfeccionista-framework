package io.perfeccionista.framework.pagefactory.context.base;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebPageNotInitialized;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.PAGE_NOT_INITIALIZED;

public class DefaultWebPageContext implements WebPageContext {

    protected final Environment environment;
    protected final WebBrowserDispatcher dispatcher;
    protected final ExceptionMapper exceptionMapper;

    protected final Deque<WebContextLimiter<?>> contextLimiters = new ArrayDeque<>();

    protected WebPage activeWebPage = null;

    public DefaultWebPageContext(@NotNull Environment environment,
                                 @NotNull WebBrowserDispatcher dispatcher,
                                 @NotNull ExceptionMapper exceptionMapper) {
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
        return pageInstance;
    }

    @Override
    public @NotNull WebPage getPage(@NotNull String pageName) {
        WebPage pageInstance = environment.getService(WebPageService.class).getPageInstanceByName(pageName);
        pageInstance.setWebBrowserDispatcher(dispatcher);
        pageInstance.setEnvironment(environment);
        activeWebPage = pageInstance;
        return pageInstance;
    }

    @Override
    public @NotNull WebPage getActivePage() {
        if (Objects.nonNull(activeWebPage)) {
            return activeWebPage;
        }
        throw WebPageNotInitialized.exception(PAGE_NOT_INITIALIZED.getMessage());
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
