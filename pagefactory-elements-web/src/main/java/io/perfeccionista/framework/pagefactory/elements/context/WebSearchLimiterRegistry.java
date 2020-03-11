package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebParentElement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class WebSearchLimiterRegistry {

    protected final WebParentElement parentPage;
    protected Deque<WebSearchContextLimiter> contextLimiters = new ArrayDeque<>();

    public WebSearchLimiterRegistry(WebParentElement parentPage) {
        this.parentPage = parentPage;
    }

    public Optional<WebSearchContextLimiter> getLast() {
        return Optional.ofNullable(contextLimiters.getLast());
    }

    public WebSearchLimiterRegistry add(WebSearchContextLimiter searchContextLimiter) {
        contextLimiters.addLast(searchContextLimiter);
        return this;
    }

    public WebSearchLimiterRegistry removeLast() {
        contextLimiters.removeLast();
        return this;
    }

    public WebSearchLimiterRegistry removeAll() {
        contextLimiters.clear();
        return this;
    }

    public WebParentElement getContext() {
        if (contextLimiters.size() == 0) {
            return parentPage;
        }
        return contextLimiters.getLast().getContext();
    }

    public <T extends WebParentElement> T getContext(Class<T> contextClass) {
        return (T) getContext();
    }

}
