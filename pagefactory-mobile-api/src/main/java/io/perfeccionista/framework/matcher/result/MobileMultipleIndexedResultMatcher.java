package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

public interface MobileMultipleIndexedResultMatcher<T> extends PerfeccionistaMatcher<MobileMultipleIndexedResult<T, ? extends MobileChildElement>> {

    @Override
    void check(@NotNull MobileMultipleIndexedResult<T, ? extends MobileChildElement> result);

}
