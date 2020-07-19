package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_SELECTED_METHOD;

public interface IsSelectedAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(IS_SELECTED_METHOD)
    boolean isSelected();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_SELECTED_METHOD)
    WebChildElement shouldBeSelected();

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_BE_SELECTED_METHOD)
    WebChildElement shouldNotBeSelected();

}
