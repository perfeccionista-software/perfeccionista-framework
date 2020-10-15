package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import org.jetbrains.annotations.NotNull;

public interface ElementContainer<F extends WebFilter<?>, B extends WebFilterBuilder<?, F>> extends WebChildElementBase {

    @NotNull F filter(@NotNull B filterBuilder);

}
