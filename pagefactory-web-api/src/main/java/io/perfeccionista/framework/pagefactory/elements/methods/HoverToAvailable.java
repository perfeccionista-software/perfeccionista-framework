package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.HOVER_TO_METHOD;

public interface HoverToAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(HOVER_TO_METHOD)
    WebChildElement hoverTo(boolean withOutOfBounds);

}
