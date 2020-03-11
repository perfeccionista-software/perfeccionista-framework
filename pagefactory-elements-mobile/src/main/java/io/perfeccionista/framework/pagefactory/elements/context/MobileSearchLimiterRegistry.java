package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.MobileParentElement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class MobileSearchLimiterRegistry {

    protected final MobileParentElement parentPage;
    protected Deque<MobileSearchContextLimiter> contextLimiters = new ArrayDeque<>();

    public MobileSearchLimiterRegistry(MobileParentElement parentPage) {
        this.parentPage = parentPage;
    }

    public Optional<MobileSearchContextLimiter> getLast() {
        return Optional.ofNullable(contextLimiters.getLast());
    }

    public MobileSearchLimiterRegistry add(MobileSearchContextLimiter searchContextLimiter) {
        contextLimiters.addLast(searchContextLimiter);
        return this;
    }

    public MobileSearchLimiterRegistry removeLast() {
        contextLimiters.removeLast();
        return this;
    }

    public MobileSearchLimiterRegistry removeAll() {
        contextLimiters.clear();
        return this;
    }

    public MobileParentElement getContext() {
        if (contextLimiters.size() == 0) {
            return parentPage;
        }
        return contextLimiters.getLast().getContext();
    }

    public <T extends MobileParentElement> T getContext(Class<T> contextClass) {
        return (T) getContext();
    }

}
