package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;

public interface IsDisplayedAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(IS_DISPLAYED_METHOD)
    boolean isDisplayed();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_DISPLAYED_METHOD)
    WebChildElement shouldBeDisplayed();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_DISPLAYED_METHOD)
    WebChildElement shouldNotBeDisplayed();

}
