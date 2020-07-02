package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;

public interface IsEnabledAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(IS_ENABLED_METHOD)
    boolean isEnabled();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_ENABLED_METHOD)
    WebChildElement shouldBeEnabled();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_DISABLED_METHOD)
    WebChildElement shouldBeDisabled();

}
