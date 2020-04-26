package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_BE_PRESENT_METHOD;

public interface IsPresentAvailable extends Element {

    @MappedElementAction(IS_PRESENT_METHOD)
    boolean isPresent();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_PRESENT_METHOD)
    IsDisplayedAvailable shouldBePresent();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_PRESENT_METHOD)
    IsDisplayedAvailable shouldNotBePresent();

}
