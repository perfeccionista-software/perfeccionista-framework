package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableMobileMethods.TAP_METHOD;

public interface TapAvailable extends Element {

    @ActionMethodType
    @MappedElementAction(TAP_METHOD)
    void tap();

}

