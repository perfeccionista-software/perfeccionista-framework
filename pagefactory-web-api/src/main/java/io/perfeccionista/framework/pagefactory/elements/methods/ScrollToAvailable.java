package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;

public interface ScrollToAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(SCROLL_TO_METHOD)
    ScrollToAvailable scrollTo();

}