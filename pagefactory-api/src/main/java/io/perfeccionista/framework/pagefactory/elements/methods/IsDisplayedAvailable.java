package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;

public interface IsDisplayedAvailable extends Element {

    @MappedElementAction(IS_DISPLAYED_METHOD)
    boolean isDisplayed();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_DISPLAYED_METHOD)
    IsDisplayedAvailable shouldBeDisplayed();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_DISPLAYED_METHOD)
    IsDisplayedAvailable shouldNotBeDisplayed();

}
