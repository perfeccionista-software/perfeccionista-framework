package io.perfeccionista.framework.pagefactory.browser.context;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.context.WebSearchContextLimiter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.stream.Stream;

public class SeleniumWebPageContext implements WebPageContext {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final ExceptionMapper exceptionMapper;

    public SeleniumWebPageContext(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
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
        return null;
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
