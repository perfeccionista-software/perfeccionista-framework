package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import org.jetbrains.annotations.NotNull;

public interface WebElementContainer<F extends WebFilter<?>, B extends WebFilterBuilder<?, F>> extends WebChildElement {

    @NotNull F filter(@NotNull B filterBuilder);

}
