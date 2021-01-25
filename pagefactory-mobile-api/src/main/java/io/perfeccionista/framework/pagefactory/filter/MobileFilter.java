package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MobileFilter<E extends MobileChildElement> {

    @NotNull E getElement();

    @API(status = Status.INTERNAL)
    @NotNull FilterResult getFilterResult();

    MobileFilter<E> should(MobileMultipleIndexedResultMatcher<Integer> matcher);

    @API(status = Status.STABLE)
    MobileFilter<E> setInitialHash(@Nullable String initialHash);

}
