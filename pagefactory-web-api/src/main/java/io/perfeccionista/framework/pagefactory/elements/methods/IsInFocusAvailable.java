package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;

public interface IsInFocusAvailable extends Element {

    @MappedElementAction(IS_IN_FOCUS_METHOD)
    boolean isInFocus();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_IN_FOCUS_METHOD)
    IsInFocusAvailable shouldBeInFocus();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_IN_FOCUS_METHOD)
    IsInFocusAvailable shouldNotBeInFocus();

}
