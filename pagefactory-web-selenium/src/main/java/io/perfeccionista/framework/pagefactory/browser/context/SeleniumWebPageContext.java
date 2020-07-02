package io.perfeccionista.framework.pagefactory.browser.context;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.context.WebSearchContextLimiter;

import java.util.stream.Stream;

public class SeleniumWebPageContext implements WebPageContext {

    protected final Environment environment;
    protected final WebBrowserDispatcher dispatcher;
    protected final ExceptionMapper exceptionMapper;

    public SeleniumWebPageContext(Environment environment, WebBrowserDispatcher dispatcher, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.dispatcher = dispatcher;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public <T extends WebParentElement> Stream<T> getSearchContexts(Class<T> contextBlockClass) {
        return null;
    }

    @Override
    public Stream<WebParentElement> getSearchContexts() {
        return null;
    }

    @Override
    public WebPageContext resetSearchContextCache() {
        return null;
    }

    @Override
    public <T extends WebParentElement> WebPageContext setLimiter(WebSearchContextLimiter<T> limiter) {
        return null;
    }

    @Override
    public WebPageContext removeLimiters() {
        return null;
    }

    @Override
    public WebPageContext usePage(String pageName) {
        return null;
    }

    @Override
    public WebPageContext usePage(Class<? extends WebPage> pageClass) {
        return null;
    }

    @Override
    public <T extends WebPage> T getPage(Class<T> pageClass) {
        T pageInstance = environment.getService(WebPageService.class).getByClass(pageClass);
        pageInstance.setWebBrowserDispatcher(dispatcher);
        return pageInstance;
    }

    @Override
    public WebPage getPage(String pageName) {
        return null;
    }

    @Override
    public WebPage getActivePage() {
        return null;
    }
}
