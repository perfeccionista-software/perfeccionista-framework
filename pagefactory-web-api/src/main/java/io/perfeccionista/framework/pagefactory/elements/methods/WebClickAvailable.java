package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;

public interface WebClickAvailable extends WebChildElement {

    @ActionMethodType
    @WebMappedElementAction(CLICK_METHOD)
    WebClickAvailable click();

}
