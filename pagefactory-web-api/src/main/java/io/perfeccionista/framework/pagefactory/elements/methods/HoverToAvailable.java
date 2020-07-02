package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;

public interface HoverToAvailable extends WebLocatorChainAvailable {

    @ActionMethodType
    @MappedElementAction(HOVER_TO_METHOD)
    WebChildElement hoverTo(boolean withOutOfBounds);

}
