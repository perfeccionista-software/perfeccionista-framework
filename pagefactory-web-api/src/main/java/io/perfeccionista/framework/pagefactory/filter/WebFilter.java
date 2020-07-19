package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebFilter<I extends WebChildElement, R extends FilterResult> {

    @NotNull R filter(@NotNull I element);

}
