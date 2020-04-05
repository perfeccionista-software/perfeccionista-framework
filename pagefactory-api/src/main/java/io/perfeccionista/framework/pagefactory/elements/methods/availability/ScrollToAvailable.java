package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_METHOD;

public interface ScrollToAvailable extends ElementMethodAvailable {

    @MappedMethod(SCROLL_TO_METHOD)
    ScrollToAvailable scrollTo();

}
