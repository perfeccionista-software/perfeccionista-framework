package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public interface WebContextLimiter<T extends WebBlock> {

    @NotNull Stream<T> getContexts(@NotNull Stream<WebParentElement> parentElements);

}
