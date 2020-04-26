package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableMobileMethods.SWIPE_TO_METHOD;

public interface SwipeToAvailable extends Element {

    @ActionMethodType
    @MappedElementAction(SWIPE_TO_METHOD)
    void swipeTo();

}
