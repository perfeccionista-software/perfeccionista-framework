package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebFilter<E extends WebChildElement> {

    @NotNull E getElement();

    @API(status = Status.INTERNAL)
    @NotNull FilterResult getFilterResult();

    WebFilter<E> should(WebMultipleIndexedResultMatcher<Integer> matcher);

    @API(status = Status.STABLE)
    WebFilter<E> setInitialHash(@Nullable String initialHash);

}
