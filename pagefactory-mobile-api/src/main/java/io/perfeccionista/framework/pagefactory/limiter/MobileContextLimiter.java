package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public interface MobileContextLimiter<T extends MobileBlock> {

    @NotNull Stream<T> getContexts(@NotNull Stream<MobileParentElement> parentElements);

}
