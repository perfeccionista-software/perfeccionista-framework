package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

public interface WebMultipleIndexedResultMatcher<T> extends PerfeccionistaMatcher<WebMultipleIndexedResult<T, ? extends WebChildElement>> {

    @Override
    void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result);

}