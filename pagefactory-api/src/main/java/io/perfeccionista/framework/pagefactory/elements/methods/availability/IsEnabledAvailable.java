package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_BE_ENABLED_METHOD;

public interface IsEnabledAvailable extends ElementMethodAvailable {

    @MappedMethod(IS_ENABLED_METHOD)
    boolean isEnabled();

    @MappedMethod(SHOULD_BE_ENABLED_METHOD)
    IsEnabledAvailable shouldBeEnabled();

    @MappedMethod(SHOULD_BE_DISABLED_METHOD)
    IsEnabledAvailable shouldBeDisabled();

}
