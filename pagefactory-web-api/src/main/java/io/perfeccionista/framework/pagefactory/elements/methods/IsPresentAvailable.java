package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;

public interface IsPresentAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(IS_PRESENT_METHOD)
    boolean isPresent();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_PRESENT_METHOD)
    WebChildElement shouldBePresent();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_PRESENT_METHOD)
    WebChildElement shouldNotBePresent();

}
