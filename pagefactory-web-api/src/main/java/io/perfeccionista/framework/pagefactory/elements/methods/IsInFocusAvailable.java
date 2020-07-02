package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;

public interface IsInFocusAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(IS_IN_FOCUS_METHOD)
    boolean isInFocus();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_IN_FOCUS_METHOD)
    WebChildElement shouldBeInFocus();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_IN_FOCUS_METHOD)
    WebChildElement shouldNotBeInFocus();

}
