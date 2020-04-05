package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;
import io.perfeccionista.framework.pagefactory.filter.Filter;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;

public interface ScrollToElementAvailable<F extends Filter<?, ?>> extends ElementMethodAvailable {

    @MappedMethod(SCROLL_TO_ELEMENT_METHOD)
    ScrollToElementAvailable<F> scrollToElement(F filter);

}
