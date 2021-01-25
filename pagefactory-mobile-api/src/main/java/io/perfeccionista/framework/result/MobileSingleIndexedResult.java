package io.perfeccionista.framework.result;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MobileSingleIndexedResult<T, E extends MobileChildElement> {

    @NotNull E getElement();

    @Nullable T getResult();

    @NotNull T getNotNullResult();

    int getIndex();

    MobileSingleIndexedResult<T, E> should(MobileMultipleIndexedResultMatcher<T> matcher);

}
