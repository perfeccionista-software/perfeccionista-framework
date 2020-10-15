package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;


import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableMobileMethods.SWIPE_TO_METHOD;

public interface SwipeToAvailable {

    @ActionMethodType
//    @WebMappedElementAction(SWIPE_TO_METHOD)
    void swipeTo();

}
