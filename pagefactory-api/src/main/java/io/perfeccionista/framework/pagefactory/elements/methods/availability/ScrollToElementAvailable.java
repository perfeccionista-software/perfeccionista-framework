package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.filter.Filter;

public interface ScrollToElementAvailable<F extends Filter<?, ?>> extends ElementMethodAvailable {

    void scrollToElement(F filter);

}
