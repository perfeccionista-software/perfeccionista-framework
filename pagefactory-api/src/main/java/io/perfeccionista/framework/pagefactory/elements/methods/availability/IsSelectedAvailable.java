package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_NOT_BE_SELECTED_METHOD;

public interface IsSelectedAvailable extends ElementMethodAvailable {

    @MappedMethod(IS_SELECTED_METHOD)
    boolean isSelected();

    @MappedMethod(SHOULD_BE_SELECTED_METHOD)
    IsSelectedAvailable shouldBeSelected();

    @MappedMethod(SHOULD_NOT_BE_SELECTED_METHOD)
    IsSelectedAvailable shouldNotBeSelected();

}
