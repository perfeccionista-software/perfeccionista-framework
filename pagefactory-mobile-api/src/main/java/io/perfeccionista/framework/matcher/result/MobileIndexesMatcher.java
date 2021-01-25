package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

public interface MobileIndexesMatcher extends MobileMultipleIndexedResultMatcher<Integer> {

    @Override
    void check(@NotNull MobileMultipleIndexedResult<Integer, ? extends MobileChildElement> result);

}
