package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

public interface WebIndexesMatcher extends WebMultipleIndexedResultMatcher<Integer> {

    @Override
    void check(@NotNull WebMultipleIndexedResult<Integer, ? extends WebChildElement> result);

}
