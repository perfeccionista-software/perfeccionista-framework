package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.plugin.ActionMethodType;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DOUBLE_TAP_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.LONG_TAP_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SINGLE_TAP_METHOD;

public interface MobileTapAvailable extends MobileChildElement {

    @ActionMethodType
    @MobileMappedElementAction(SINGLE_TAP_METHOD)
    MobileTapAvailable tap();

    @ActionMethodType
    @MobileMappedElementAction(LONG_TAP_METHOD)
    MobileTapAvailable longTap();

    @ActionMethodType
    @MobileMappedElementAction(DOUBLE_TAP_METHOD)
    MobileTapAvailable doubleTap();

}

