package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_ENABLED_METHOD;

public interface IsEnabledAvailable extends Element {

    @MappedElementAction(IS_ENABLED_METHOD)
    boolean isEnabled();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_ENABLED_METHOD)
    IsEnabledAvailable shouldBeEnabled();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_DISABLED_METHOD)
    IsEnabledAvailable shouldBeDisabled();

}
