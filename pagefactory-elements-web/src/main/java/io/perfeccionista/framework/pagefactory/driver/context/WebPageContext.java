package io.perfeccionista.framework.pagefactory.driver.context;

import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.context.WebSearchContextLimiter;

import java.util.stream.Stream;

public class WebPageContext {

    // TODO
//    public WebPageContext setHighlighter(Highlighter highlighter) {
//
//    };

    public WebPageContext setSingleSearchLimiter(WebSearchContextLimiter limiter) {

        return this;
    }

    public WebPageContext setSingleSearchLimiter(String searchLimiterName, WebSearchContextLimiter limiter) {

        return this;
    }

    public WebPageContext setMultipleSearchLimiter(WebSearchContextLimiter limiter) {

        return this;
    }

    public WebPageContext setMultipleSearchLimiter(String searchLimiterName, WebSearchContextLimiter limiter) {

        return this;
    }

    public WebPageContext setActiveSearchContext(String searchLimiterName) {

        return this;
    }

    public WebPageContext removeSearchLimiter(String searchLimiterName) {

        return this;
    }

    public WebPageContext removeSearchLimiter() {

        return this;
    }

    public WebSearchContext getSearchContext() {

        return null;
    }

    public Stream<WebSearchContext> getSearchContexts() {

        return null;
    }

    public WebPageContext usePage(String pageName) {

        return this;
    }

    public WebPageContext usePage(Class<? extends WebPage> pageClass) {

        return this;
    }

    public <T extends WebPage> T getPage(Class<T> pageClass) {

        return null;
    }

}
