package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SWIPE_TO_METHOD;

public interface SwipeToAvailable extends ElementMethodAvailable {

    @MappedMethod(SWIPE_TO_METHOD)
    void swipeTo();

}
