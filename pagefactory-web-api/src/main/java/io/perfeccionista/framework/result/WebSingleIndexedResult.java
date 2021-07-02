package io.perfeccionista.framework.result;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebSingleIndexedResult<R, T extends WebChildElement> {

    @NotNull T getElement();

    @Nullable R getResult();

    @NotNull R getNotNullResult();

    int getIndex();

    WebSingleIndexedResult<R, T> should(WebMultipleIndexedResultMatcher<R> matcher);

}
