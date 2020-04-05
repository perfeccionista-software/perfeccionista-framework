package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_NOT_BE_DISPLAYED_METHOD;

public interface IsDisplayedAvailable extends ElementMethodAvailable {

    @MappedMethod(IS_DISPLAYED_METHOD)
    boolean isDisplayed();

    @MappedMethod(SHOULD_BE_DISPLAYED_METHOD)
    IsDisplayedAvailable shouldBeDisplayed();

    @MappedMethod(SHOULD_NOT_BE_DISPLAYED_METHOD)
    IsDisplayedAvailable shouldNotBeDisplayed();

}
