package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;

public interface MobileScrollToAvailable extends MobileChildElementBase {

    @ActionMethodType
    @MobileMappedElementAction(SCROLL_TO_METHOD)
    MobileScrollToAvailable scrollTo();

}
