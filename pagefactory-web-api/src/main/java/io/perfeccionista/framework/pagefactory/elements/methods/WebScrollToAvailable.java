package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;

public interface WebScrollToAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(SCROLL_TO_METHOD)
    WebScrollToAvailable scrollTo();

}
