package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLEAR_METHOD;

public interface ClearAvailable extends WebLocatorChainAvailable {

    @ActionMethodType
    @MappedElementAction(CLEAR_METHOD)
    WebChildElement clear();

}
