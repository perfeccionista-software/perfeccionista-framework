package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.OPEN_METHOD;

public interface OpenAvailable extends WebChildElement {

    @ActionMethodType
    @WebMappedElementAction(OPEN_METHOD)
    OpenAvailable open();

}
