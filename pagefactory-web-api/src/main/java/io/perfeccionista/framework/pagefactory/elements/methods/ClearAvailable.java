package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLEAR_METHOD;

public interface ClearAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(CLEAR_METHOD)
    ClearAvailable clear();

}
