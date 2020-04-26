package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.HOVER_TO_METHOD;

public interface HoverToAvailable extends Element {

    @ActionMethodType
    @MappedElementAction(HOVER_TO_METHOD)
    HoverToAvailable hoverTo(boolean withOutOfBounds);

}
