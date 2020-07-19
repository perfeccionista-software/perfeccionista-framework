package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebFilterBuilder<I extends WebChildElement, R extends WebFilter> {

    @NotNull R build(@NotNull I element);

}
