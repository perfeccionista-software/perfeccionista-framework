package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_BE_SELECTED_METHOD;

public interface IsSelectedAvailable extends Element {

    @MappedElementAction(IS_SELECTED_METHOD)
    boolean isSelected();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_SELECTED_METHOD)
    IsSelectedAvailable shouldBeSelected();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_SELECTED_METHOD)
    IsSelectedAvailable shouldNotBeSelected();

}
