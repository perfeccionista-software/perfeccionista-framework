package io.perfeccionista.framework.result;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebSingleIndexedResult<T, E extends WebChildElement> {

    @NotNull E getElement();

    @Nullable T getValue();

    int getIndex();

    WebSingleIndexedResult<T, E> should(WebMultipleIndexedResultMatcher<T> matcher);

}