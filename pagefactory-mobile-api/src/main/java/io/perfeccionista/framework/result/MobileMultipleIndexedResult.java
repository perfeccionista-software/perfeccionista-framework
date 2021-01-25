package io.perfeccionista.framework.result;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface MobileMultipleIndexedResult<T, E extends MobileChildElement> {

    @NotNull E getElement();

    Map<Integer, T> getResults();

    int getSize();

    MobileMultipleIndexedResult<T, E> should(MobileMultipleIndexedResultMatcher<T> matcher);

    @NotNull MobileSingleIndexedResult<T, E> singleResult();

}
