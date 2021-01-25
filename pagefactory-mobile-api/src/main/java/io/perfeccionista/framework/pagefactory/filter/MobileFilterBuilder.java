package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

public interface MobileFilterBuilder<I extends MobileChildElement, R extends MobileFilter<I>> {

    @NotNull R build(@NotNull I element);

}

