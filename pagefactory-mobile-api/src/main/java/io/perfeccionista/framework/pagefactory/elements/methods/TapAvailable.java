package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableMobileMethods.TAP_METHOD;

public interface TapAvailable {

    @ActionMethodType
//    @WebMappedElementAction(TAP_METHOD)
    void tap();

}

