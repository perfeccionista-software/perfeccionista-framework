package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.filter.MobileFilter;
import io.perfeccionista.framework.pagefactory.filter.MobileFilterBuilder;
import org.jetbrains.annotations.NotNull;

public interface MobileElementContainer<F extends MobileFilter<?>, B extends MobileFilterBuilder<?, F>> extends MobileChildElement {

    @NotNull F filter(@NotNull B filterBuilder);

}
