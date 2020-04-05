package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;
import io.perfeccionista.framework.pagefactory.filter.Filter;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SWIPE_TO_ELEMENT_METHOD;

public interface SwipeToElementAvailable<F extends Filter<?, ?>> extends ElementMethodAvailable {

    @MappedMethod(SWIPE_TO_ELEMENT_METHOD)
    void swipeToElement(F filter);

}
