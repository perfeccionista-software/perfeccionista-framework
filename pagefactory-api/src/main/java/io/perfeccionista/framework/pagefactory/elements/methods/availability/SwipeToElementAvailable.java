package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.filter.Filter;

public interface SwipeToElementAvailable<F extends Filter<?, ?>> extends ElementMethodAvailable {

    void swipeToElement(F filter);

}
