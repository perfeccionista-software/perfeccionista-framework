package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class WebSearchLimiterRegistry {

    protected final WebParentElement parentPage;
    protected Deque<WebSearchContextLimiter<? extends WebParentElement>> contextLimiters = new ArrayDeque<>();

    public WebSearchLimiterRegistry(WebParentElement parentPage) {
        this.parentPage = parentPage;
    }

    public Optional<WebSearchContextLimiter<? extends WebParentElement>> getLast() {
        return Optional.ofNullable(contextLimiters.peekLast());
    }

    public WebSearchLimiterRegistry add(WebSearchContextLimiter<? extends WebParentElement> searchContextLimiter) {
        searchContextLimiter.setParentContext(getLast().orElse(null));
        contextLimiters.addLast(searchContextLimiter);
        return this;
    }

    public WebSearchLimiterRegistry removeAll() {
        contextLimiters.clear();
        return this;
    }

}
